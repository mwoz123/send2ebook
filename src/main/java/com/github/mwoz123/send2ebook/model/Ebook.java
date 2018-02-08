package com.github.mwoz123.send2ebook.model;

import java.io.ByteArrayInputStream;


public interface Ebook {

	public String getTitle();
	public String getFileExtension();
	public ByteArrayInputStream getEbookStream() ;
}
