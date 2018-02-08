package com.github.mwoz123.send2ebook.converter;

import java.io.IOException;

import com.github.mwoz123.send2ebook.model.EbookData;

public interface Converter<T> {
	
	T createOutputEbook(EbookData ebookData) throws IOException;

}
