package com.github.mwoz123.send2ebook;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.mwoz123.send2ebook.creator.EpubCreator;
import com.github.mwoz123.send2ebook.creator.Creator;
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

	private static final Logger LOGGER = LogManager.getLogger(Send2Ebook.class);
	
	private static final String CONNECTION_FILE_PATH_PROPERTY = "connection.file.path";

	public static void main(String[] args) throws IOException {

		if (args != null && args.length > 0) {

			InputProcessor<String> inputProcessor = new UrlInputProcessor();
			Creator<EpubEbook> creator = new EpubCreator();
			
			Storage storage = FtpStorage.getInstance();
			Connection connection = getConnectionFromPropertyFile();
			
			for (String url : args) {
				LOGGER.info("Starting download: '{}' and clean up ", url);

				boolean processTextOnly = false;
				EbookData ebookData = inputProcessor.transformInput(url, processTextOnly);

				LOGGER.info("Creating Epub");
				Ebook ebook = creator.createOutputEbook(ebookData);

				LOGGER.info("Connecting to storage server: {}", connection.getHost());
				storage.connect(connection);

				LOGGER.info("Saving file '{}' to server", ebook.getTitle());
				storage.storeFile(ebook);

				LOGGER.info("Succesfully finished.");
			}
			storage.disconnect();
		} else {
			LOGGER.error("Missing input urls");
		}
	}

	private static Connection getConnectionFromPropertyFile() throws IOException {
		
		String connectionFilePath = System.getProperty(CONNECTION_FILE_PATH_PROPERTY);

		try {
			return PropertyConfigImporter.getConnection(connectionFilePath);
		} catch (IOException e) {
			LOGGER.error("Could not file connection property file.\n"
					+ "Use commandline argument: \n"
					+ "'-D" +CONNECTION_FILE_PATH_PROPERTY +"=/path_to_your_connection_file.properties'\n"
					+ "to specify path to your config property file");
			throw e;
		}

	}

}
