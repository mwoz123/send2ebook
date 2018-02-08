package com.github.mwoz123.send2ebook.input;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import com.github.mwoz123.send2ebook.model.EbookData;
import com.github.mwoz123.send2ebook.util.TitleExtractor;

public class UrlInputProcessor implements InputProcessor<String> {

	private final static Whitelist CLEAN_UP_LEVEL = Whitelist.basicWithImages();
	

	public EbookData transformInput(String urlString) throws IOException {

		Document oryginalDoc = Jsoup.connect(urlString).get();

		Document cleanedUpDoc = this.cleanDocument(oryginalDoc);

		String cleanedUpDocHtml = cleanedUpDoc.html();

		String titleFromUrl = TitleExtractor.extractTitleFromUrl(urlString);
		
		EbookData ebookData = new EbookData();
		ebookData.setTitle(titleFromUrl);
		ebookData.addElement(titleFromUrl, cleanedUpDocHtml.getBytes());

		this.addImages(cleanedUpDoc, ebookData);

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

	private void addImages(Document cleanedUpDoc, EbookData ebookData) {
		Elements elementsByTag = cleanedUpDoc.getElementsByTag("img");
		// TODO: this is preparation for adding images to ebook
		// TODO download, convert to byte[] add to ebookData, update img links
		// in clearedUpDoc

	}

}
