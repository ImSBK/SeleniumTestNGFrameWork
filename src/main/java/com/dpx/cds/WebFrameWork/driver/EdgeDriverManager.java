package com.dpx.cds.WebFrameWork.driver;

import java.io.File;

import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EdgeDriverManager implements DriverManager {

	@Override
	public WebDriver createDriver() {
		
		WebDriverManager.edgedriver().setup();

		return new EdgeDriver(getEdgeDriverService(), getEdgeOptions());
	}

	private static EdgeOptions getEdgeOptions() {

		EdgeOptions options = new EdgeOptions();
		options.addArguments("--disable-notifications");

		return options;

	}

	private static EdgeDriverService getEdgeDriverService() {
		String logDirectoryPath = ThreadContext.get("logDirectoryPath");

		if (logDirectoryPath == null || logDirectoryPath.isBlank())
			throw new IllegalArgumentException("logDirectoryPath is not properly in ThreadContext");

		String logFilePath = logDirectoryPath + File.separator + "EdgeDriver.log";

		File logFile = new File(logFilePath);

		return new EdgeDriverService.Builder().withLogFile(null).withVerbose(true).build();

	}

}
