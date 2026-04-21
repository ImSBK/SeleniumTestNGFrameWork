package com.dpx.cds.WebFrameWork.context;

import org.openqa.selenium.remote.Browser;

import com.dpx.cds.WebFrameWork.constants.Environments;

public class ExecutionContext {

	public static ThreadLocal<String> tEnv = new ThreadLocal<>();
	public static ThreadLocal<String> tBrowser = new ThreadLocal<>();

	public static void setEnv(String testNgEnv) {
		String currentEnv = null;
		String cliEnv = null;
		if ((cliEnv = System.getProperty("env")) != null && !cliEnv.isBlank())
			currentEnv = cliEnv;
		else if (testNgEnv != null)
			currentEnv = testNgEnv;
		else
			currentEnv = Environments.DEV.toString();

		tEnv.set(currentEnv);

	}

	public static void setBrowser(String testNgBrowser) {
		String currentBrowser = null;
		String cliBrowser = null;
		if ((cliBrowser = System.getProperty("browser")) != null && !cliBrowser.isBlank())
			currentBrowser = cliBrowser;
		else if (testNgBrowser != null)
			currentBrowser = testNgBrowser;
		else
			currentBrowser = Browser.CHROME.toString();

		tBrowser.set(currentBrowser);

	}

	public static String getEnv() {
		String env = tEnv.get();
		if (env == null) {
			throw new IllegalArgumentException("environment varaible is not properly set");
		}

		return env;

	}

	public static String getBrowser() {
		String browser = tBrowser.get();
		if (browser == null) {
			throw new IllegalArgumentException("browser variable is not properly set");
		}
		return browser;

	}

	public static void removeAll() {
		tEnv.remove();
		tBrowser.remove();

	}

}