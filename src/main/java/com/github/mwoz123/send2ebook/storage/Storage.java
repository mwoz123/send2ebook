package com.github.mwoz123.send2ebook.storage;

import java.io.IOException;

import com.github.mwoz123.send2ebook.model.Ebook;

public interface Storage<T> {

	public void connect(T connection) throws IOException;

	public void storeFile(Ebook ebook) throws IOException;

	public void disconnect() ;
}
