package com.github.mwoz123.send2ebook.storage.ftp;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.mwoz123.send2ebook.model.Ebook;
import com.github.mwoz123.send2ebook.storage.Storage;

public class FtpStorage implements Storage<FtpConnection>{

	private final static Logger LOGGER = LogManager.getLogger(FtpStorage.class);
	
	private FTPClient ftpClient;
	
	private static FtpStorage instance ;

	private FtpStorage() {
	}

	public static FtpStorage getInstance() {
		if (instance == null) {
			instance = new FtpStorage();
		}
		return instance;
	}

	@Override
	public void connect(FtpConnection connection) throws IOException {
		if (ftpClient == null)
			ftpClient = new FTPClient();

		if (!ftpClient.isConnected()) {
			ftpClient.connect(connection.getHost());
			int replyCode = ftpClient.getReplyCode();

			if (!FTPReply.isPositiveCompletion(replyCode)) {
				throw new UnknownHostException("No connection with host");
			}
			boolean successLogin = ftpClient.login(connection.getUser(),
					connection.getPassword());
			
			if (!successLogin) {
				throw new ConnectException("Can't log in. Invalid credentials?");
			}
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			String folder = connection.getFolder();

			boolean success = ftpClient.changeWorkingDirectory(folder);
			if (!success) {
				LOGGER.warn("Can't change work path to " + folder);
			}
		}
	}

	public void storeFile(Ebook ebook)
			throws IOException {
		String filename = ebook.getTitle() + ebook.getFileExtension();
		LOGGER.debug("Storing file: {} to FTP", filename );
		ftpClient.storeFile(filename, ebook.getEbookStream());
	}



	public void disconnect() {
		try {
			ftpClient.disconnect();
		} catch (IOException e) {
			LOGGER.warn("exception" ,e);
		}
	}
	
	
	private static void showServerReply(FTPClient ftpClient) {
		String[] replies = ftpClient.getReplyStrings();
		if (replies != null && replies.length > 0) {
			for (String aReply : replies) {
				LOGGER.debug("SERVER: " + aReply);
			}
		}
	}
}
