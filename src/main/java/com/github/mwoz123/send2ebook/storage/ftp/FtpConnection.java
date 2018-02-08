package com.github.mwoz123.send2ebook.storage.ftp;

import com.github.mwoz123.send2ebook.storage.Connection;

public class FtpConnection implements Connection {

	private String user, password, host;
	private String folder = "/";
	private int port = 21;

	public FtpConnection(String host, String user, String password) {
		this.host = host;
		this.user = user;
		this.password = password;
	}

	@Override
	public String getUser() {
		return user;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getHost() {
		return host;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
