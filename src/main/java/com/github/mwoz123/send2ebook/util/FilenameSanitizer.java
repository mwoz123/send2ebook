package com.github.mwoz123.send2ebook.util;

public class FilenameSanitizer {

	private final static String INVALID_CHAR_REGEX = "[:\\\\/*?|<>]",
			SUBSTITUTE = "_";

	public static String replaceInvalidChars(String title) {
		return title.replaceAll(INVALID_CHAR_REGEX, SUBSTITUTE);
	}

}
