package com.dpx.cds.WebFrameWork.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import com.dpx.cds.WebFrameWork.constants.Environments;
import com.dpx.cds.WebFrameWork.context.ExecutionContext;

public class ConfigManager {

	private static ConcurrentHashMap<String, Properties> propCache = new ConcurrentHashMap<>();

	public static Properties fetchProperties() throws IOException {

		Environments env = Environments.valueOf(ExecutionContext.getEnv().toUpperCase());

		if (propCache.get(env.toString()) == null) {
			try {
				loadProperties(env);
			} catch (IOException e) {
				throw new IOException("unable to load properties file", e);

			}
		}
		return propCache.get(env.toString());

	}

	private static synchronized void loadProperties(Environments env) throws IOException {

		String configFilePath = null;

		switch (env) {

		case DEV:
			configFilePath = "devConfig.properties";
			break;

		case QA:
			configFilePath = "qaConfig.properties";
			break;

		default:
			throw new IllegalArgumentException("wrong environment is selected");

		}

		try (InputStream is = ConfigManager.class.getClassLoader().getResourceAsStream(configFilePath);) {
			if (propCache.get(env.toString()) == null) {
				Properties prop = new Properties();
				prop.load(is);
				propCache.put(env.toString(), prop);
			}
		} catch (Exception e) {

			throw new RuntimeException("failed to load properties of env :" + env, e);
		}

	}

}
