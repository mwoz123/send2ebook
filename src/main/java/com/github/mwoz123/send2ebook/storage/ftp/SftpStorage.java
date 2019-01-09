package com.github.mwoz123.send2ebook.storage.ftp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.mwoz123.send2ebook.storage.ftp.FtpStorage;

public class SftpStorage extends FtpStorage{

	private final static Logger LOGGER = LogManager.getLogger(SftpStorage.class);
	
	private static SftpStorage instance ;

	private SftpStorage() {
	}

	public static SftpStorage getInstance() {
		if (instance == null) {
			instance = new SftpStorage();
		}
		return instance;
	}
}
