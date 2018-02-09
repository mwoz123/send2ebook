package com.github.mwoz123.send2ebook.creator;

import java.io.IOException;

import com.github.mwoz123.send2ebook.model.EbookData;

public interface Creator<T> {
	
	T createOutputEbook(EbookData ebookData) throws IOException;

}
