package com.dpx.cds.WebFrameWork.utils;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utility {

	public static String getCurrentDateTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_hhmmss");
		return now.format(formatter);

	}

	public static void createFolder(String directoryPath) {

		File path = new File(directoryPath);

		if (!path.exists())
			path.mkdirs();

	}

}
