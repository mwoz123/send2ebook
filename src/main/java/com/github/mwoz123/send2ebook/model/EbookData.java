package com.github.mwoz123.send2ebook.model;

import java.util.HashMap;
import java.util.Map;

public class EbookData {
	
	
	private Map<String, byte []> ebookElements = new HashMap<>();
	private String title = ""; 
	
	
	public void addElement(String elementName, byte [] element) {
		ebookElements.put(elementName, element);
	}
	
	public Map<String, byte []> getEbookElements() {
		return ebookElements;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
