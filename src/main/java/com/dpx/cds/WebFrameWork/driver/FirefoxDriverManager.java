package com.dpx.cds.WebFrameWork.driver;

import java.io.File;

import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;

public class FirefoxDriverManager implements DriverManager {

	@Override
	public WebDriver createDriver() {

		return new FirefoxDriver(getGeckoDriverService(), getFirefoxOptions());

	}

	private static GeckoDriverService getGeckoDriverService() {
		String logDirectoryPath = ThreadContext.get("logDirectoryPath");

		if (logDirectoryPath == null || logDirectoryPath.isBlank())
			throw new IllegalArgumentException("directory path is not set in ThreadContext");
		String logFilePath = logDirectoryPath + File.separator + "FirefoxDriver.log";

		File logFile = new File(logFilePath);

		return new GeckoDriverService.Builder().withLogFile(logFile).build();

	}

	private static FirefoxOptions getFirefoxOptions() {

		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("--disable-notifications");
		return options;
	}

}
