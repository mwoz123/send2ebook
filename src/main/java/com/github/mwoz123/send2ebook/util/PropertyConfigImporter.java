package com.github.mwoz123.send2ebook.util;

import java.io.IOException;
import java.util.Properties;

import com.github.mwoz123.send2ebook.storage.Connection;
import com.github.mwoz123.send2ebook.storage.ftp.FtpConnection;

public class PropertyConfigImporter {

	public static Connection getConnection(String propertyFilePath) throws IOException {

		Properties properties = PropertyLoader.loadFromFile(propertyFilePath);
		
		String host = properties.getProperty("host");
		String user = properties.getProperty("user");
		String passwd = properties.getProperty("password");

		FtpConnection connection = new FtpConnection(host, user, passwd);

		if (properties.containsKey("folder"))
			connection.setFolder(properties.getProperty("folder"));
		
		if (properties.containsKey("port"))
			connection.setPort(Integer.valueOf(properties.getProperty("port")));
		
		return connection;
	}

}
