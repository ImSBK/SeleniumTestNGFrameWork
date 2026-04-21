package com.dpx.cds.WebFrameWork.driver;

import java.io.File;

import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriverManager implements DriverManager {

	@Override
	public WebDriver createDriver() {

		WebDriverManager.chromedriver().setup();

		return new ChromeDriver(getChromeDriverService(), getChromeOptions());
	}

	private static ChromeDriverService getChromeDriverService() {
		String logDirectoryPath = ThreadContext.get("logDirectoryPath");

		if (logDirectoryPath == null || logDirectoryPath.isBlank())
			throw new IllegalArgumentException("logPath is not set properly in ThreadContext");

		String logFilePath = logDirectoryPath + File.separator + "ChromeDriver.log";

		File logFile = new File(logFilePath);

		return new ChromeDriverService.Builder().withLogFile(logFile).withVerbose(true).build();

	}

	private static ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		return options;

	}

}
