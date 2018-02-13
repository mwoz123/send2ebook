package com.github.mwoz123.send2ebook.input;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import com.github.mwoz123.send2ebook.model.EbookData;
import com.github.mwoz123.send2ebook.util.TitleExtractor;


public class UrlInputProcessor implements InputProcessor<String> {

	private static final Logger LOGGER = LogManager.getLogger(UrlInputProcessor.class);

	private final static Whitelist CLEAN_UP_LEVEL = Whitelist.basicWithImages();

	private final static String HTML_EXTENSION = ".html";
	private final static String IMG_SRC_ATTR = "src";

	public EbookData transformInput(String urlString, boolean processTextOnly) throws IOException {

		Document oryginalDoc = Jsoup.connect(urlString).get();
		Document cleanedUpDoc = this.cleanDocument(oryginalDoc);

		String titleFromUrl = TitleExtractor.extractTitleFromUrl(urlString);
		
		EbookData ebookData = new EbookData();
		ebookData.setTitle(titleFromUrl);

		if (! processTextOnly) { 
			cleanedUpDoc = this.addImages(cleanedUpDoc, ebookData);
			//TODO add CSS styles?
		}
		String cleanedUpDocHtml = cleanedUpDoc.html();
		ebookData.addElement(titleFromUrl + HTML_EXTENSION,
				cleanedUpDocHtml.getBytes());
		
		return ebookData;
	}

	private Document cleanDocument(Document oryginalDoc) {
		String cleanedUpBody = Jsoup.clean(oryginalDoc.html(), CLEAN_UP_LEVEL);

		Document cleanedUpDoc = Jsoup.parseBodyFragment(cleanedUpBody);

		cleanedUpDoc.charset(oryginalDoc.charset());
		cleanedUpDoc.setBaseUri(oryginalDoc.baseUri());

		cleanedUpDoc.outputSettings()
				.syntax(Document.OutputSettings.Syntax.xml);
		return cleanedUpDoc;
	}

	private Document addImages(Document cleanedUpDoc, EbookData ebookData) {
		LOGGER.debug("Processing images");
		Elements images = cleanedUpDoc.getElementsByTag("img");
		
		
		for (int i = 0; i < images.size(); i++) {
			Element img = images.get(i);
			// TODO refactor to threads
			
			String imgSrc = img.attr(IMG_SRC_ATTR);
			LOGGER.debug("Processing image: {}", imgSrc);
			//TODO add check if allready processed if yes store, new name in HashSet
			try {
				URL url = new URL(imgSrc);
				byte[] imageBytes = IOUtils.toByteArray(url.openStream());

				String extension = TitleExtractor.getExtension(imgSrc);
				String imageFilename = new StringBuffer(Integer.toString(i)).append(".")
						.append(extension).toString();

				ebookData.addElement(imageFilename, imageBytes);
				
				img.attr(IMG_SRC_ATTR, imageFilename);

			} catch (IOException ex) {
				LOGGER.error("Couldn't save image ", ex);
			}
		}
		return cleanedUpDoc;
	}

}
