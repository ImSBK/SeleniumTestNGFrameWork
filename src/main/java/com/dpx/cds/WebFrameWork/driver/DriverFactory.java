package com.dpx.cds.WebFrameWork.driver;

import org.openqa.selenium.WebDriver;

import com.dpx.cds.WebFrameWork.constants.Browsers;
import com.dpx.cds.WebFrameWork.context.ExecutionContext;

public class DriverFactory {

	public static ThreadLocal<WebDriver> tDriver = new ThreadLocal<>();

	public static void initDriver() {

		DriverManager driver;

		switch (Browsers.valueOf(ExecutionContext.getBrowser().toUpperCase())) {

		case CHROME:
			driver = new ChromeDriverManager();
			break;

		case FIREFOX:
			driver = new FirefoxDriverManager();
			break;

		case EDGE:
			driver = new EdgeDriverManager();
			break;

		default:
			throw new IllegalArgumentException("wrong browser value passed");

		}
		try {
			tDriver.set(driver.createDriver());

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Exception while creating driver", e);
		}

	}

	public static WebDriver getDriver() {
		if (tDriver.get() == null) {
	        throw new IllegalStateException("Driver not initialized");
	    }
		return tDriver.get();
	}

	public static void removeDriver() {
		if (tDriver.get() != null)
			tDriver.remove();
	}

}
