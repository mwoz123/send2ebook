package com.github.mwoz123.send2ebook.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

	public static Properties loadFromFile(String filepath) throws IOException {
		Properties prop = new Properties();
		InputStream input = new FileInputStream(filepath);

		prop.load(input);

		return prop;

	}
}
