package com.github.mwoz123.send2ebook.model.epub;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.github.mwoz123.send2ebook.model.Ebook;

public class EpubEbook implements Ebook {

	private static final String EXTENSION = ".epub";

	private String title;
	private ByteArrayInputStream ebookStream;


	public EpubEbook(String title, ByteArrayOutputStream ebookStream) {
		this.title =title;
		this.ebookStream = new ByteArrayInputStream(ebookStream.toByteArray());;
	}

	@Override
	public ByteArrayInputStream getEbookStream() {
		return ebookStream;
	}

	@Override
	public String getFileExtension() {
		return EXTENSION;
	}

	public String getTitle() {
		return title;
	}

}
