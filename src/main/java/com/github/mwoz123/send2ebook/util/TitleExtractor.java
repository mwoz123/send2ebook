package com.github.mwoz123.send2ebook.util;

public class TitleExtractor {

	private static final String SLASH = "/";

	public static String extractTitleFromUrl(String path) {
		int lastSlash = path.lastIndexOf(SLASH);
		if (path.length() == lastSlash + 1) {
			return extractTitleFromUrl(path.substring(0, lastSlash));
		}
		return path.substring(lastSlash + 1);
	}
}
