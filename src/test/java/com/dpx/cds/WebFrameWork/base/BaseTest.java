package com.dpx.cds.WebFrameWork.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.dpx.cds.WebFrameWork.config.ConfigManager;
import com.dpx.cds.WebFrameWork.context.ExecutionContext;
import com.dpx.cds.WebFrameWork.driver.DriverFactory;
import com.dpx.cds.WebFrameWork.utils.Utility;

public class BaseTest {

	@BeforeSuite
	public void setupSuite() {
		String rootLogDirectoryPath = System.getProperty("user.dir") + File.separator + "logs" + File.separator
				+ Utility.getCurrentDateTime();
		System.setProperty("rootLogDirectoryPath", rootLogDirectoryPath);

	}

	@Parameters({ "environment", "browser" })
	@BeforeMethod
	public void setUp(Method method, @Optional("DEV") String env, @Optional("CHROME") String browser) {
		ExecutionContext.setEnv(env);
		ExecutionContext.setBrowser(browser);
		String className = method.getDeclaringClass().getSimpleName();
		String logDirectoryPath = System.getProperty("rootLogDirectoryPath") + File.separator
				+ ExecutionContext.getEnv() + File.separator + ExecutionContext.getBrowser() + File.separator
				+ Thread.currentThread().getId() + File.separator + className + File.separator + method.getName();
		Utility.createFolder(logDirectoryPath);
		ThreadContext.put("logDirectoryPath", logDirectoryPath);
		DriverFactory.initDriver();
		WebDriver driver = DriverFactory.getDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		String url = null;
		try {
			url = ConfigManager.fetchProperties().getProperty("appUrl");
		} catch (IOException e) {
			throw new RuntimeException("unable to fetch url ", e);

		}
		driver.get(url);

	}

	@AfterMethod
	public void tearDown() {
		try {
			WebDriver driver = DriverFactory.getDriver();
			driver.quit();
		} catch (Exception e) {
			System.out.println("Driver quit failed: " + e.getMessage());
		}

		ThreadContext.remove("logDirectoryPath");
		DriverFactory.removeDriver();
		ThreadContext.clearAll();
	}

}
