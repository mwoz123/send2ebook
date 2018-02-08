package com.github.mwoz123.send2ebook;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.mwoz123.send2ebook.converter.Converter;
import com.github.mwoz123.send2ebook.converter.EpubCreator;
import com.github.mwoz123.send2ebook.input.InputProcessor;
import com.github.mwoz123.send2ebook.input.UrlInputProcessor;
import com.github.mwoz123.send2ebook.model.Ebook;
import com.github.mwoz123.send2ebook.model.EbookData;
import com.github.mwoz123.send2ebook.model.epub.EpubEbook;
import com.github.mwoz123.send2ebook.storage.Connection;
import com.github.mwoz123.send2ebook.storage.Storage;
import com.github.mwoz123.send2ebook.storage.ftp.FtpStorage;
import com.github.mwoz123.send2ebook.util.PropertyConfigImporter;

public class Send2Ebook {

	public static Logger logger = LogManager.getLogger(Send2Ebook.class);

	public static void main(String[] args) throws IOException {

		if (args != null && args.length > 0) {

			InputProcessor<String> inputProcessor = new UrlInputProcessor();
			Converter<EpubEbook> converter = new EpubCreator();
			Storage storage = FtpStorage.getInstance();

			for (String url : args) {
				logger.info("Starting download and clean up");

				EbookData ebookData = inputProcessor.transformInput(url);

				logger.debug("Creating Epub");
				Ebook ebook = converter.createOutputEbook(ebookData);

				Connection connection = getConnectionFromFile();

				logger.debug("Connecting to storage server");
				storage.connect(connection);

				logger.debug("Saving file to server");
				storage.storeFile(ebook);

				logger.info("Succesfully finished");
			}
			storage.disconnect();
		} else {
			logger.error("Missing input urls");
		}
	}

	private static Connection getConnectionFromFile() throws IOException {
		String connectionFilePath = System.getProperty("connection.file.path");

		try {
			return PropertyConfigImporter.getConnection(connectionFilePath);
		} catch (IOException e) {
			logger.error("Could not file connection property file.\n"
					+ "Use commandline argument: \n"
					+ "'-Dconnection.file.path=/path_to_your_connection_file.properties'\n"
					+ "to specify path to your config property file");
			throw e;
		}

	}

}
