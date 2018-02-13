package com.github.mwoz123.send2ebook.util;

import org.apache.commons.io.FilenameUtils;

public class TitleExtractor {

	private static final String SLASH = "/";
	private static final String REQUEST_PARAMS_START = "?"; 

	
	public static String extractTitleFromUrl(String path) {
		//TODO: refactor to get url from html + cleanup characters
		int lastSlash = path.lastIndexOf(SLASH);
		if (path.length() == lastSlash + 1) {
			return extractTitleFromUrl(path.substring(0, lastSlash));
		}
		return path.substring(lastSlash + 1);
	}
	
	
	public static String getExtension(String url) {
		String extension = FilenameUtils.getExtension(url);
		
		if (extension.contains(REQUEST_PARAMS_START)) {
			extension =  extension.substring(0, extension.indexOf(REQUEST_PARAMS_START));
		}
		return extension;
		
	}
}
