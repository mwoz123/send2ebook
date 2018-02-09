package com.github.mwoz123.send2ebook.converter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map.Entry;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;

import com.github.mwoz123.send2ebook.model.EbookData;
import com.github.mwoz123.send2ebook.model.epub.EpubEbook;

public class EpubCreator implements Converter<EpubEbook>{
	
	private final static String HTML_EXTENSION = ".html";

	public EpubEbook createOutputEbook(EbookData ebookData)
			throws IOException {

		Book book = new Book();
		String title = ebookData.getTitle();
		book.getMetadata().addTitle(title);
		
		this.addAllElemntsToBook(ebookData, book);

		return this.createEbook(title, book);
	}
	
	private void addAllElemntsToBook(EbookData ebookData, Book book) {
		for (Entry<String, byte[]> entry : ebookData.getEbookElements().entrySet()) {
			String elementName = entry.getKey();
			book.addSection(elementName, new Resource(entry.getValue(), elementName + HTML_EXTENSION));	
		}
	}

	private EpubEbook createEbook(String title, Book book) throws IOException {
		EpubWriter epubWriter = new EpubWriter();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		epubWriter.write(book, outputStream);
		
		return new EpubEbook(title, outputStream);
	}

	

}
