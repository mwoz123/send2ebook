package com.github.mwoz123.send2ebook.input;

import java.io.IOException;

import com.github.mwoz123.send2ebook.model.EbookData;

public interface InputProcessor<T> {

	EbookData transformInput(T t, boolean processTextOnly) throws IOException;
	
	
}
