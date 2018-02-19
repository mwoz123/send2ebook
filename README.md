# send2ebook

## Decription
Send2Ebook lets you send articles found on PC/[Android](https://github.com/koreader/android-send2ebook) phone to your Ebook reader.

This is PC version (commandline, java lib).

**Android version is located [here](https://github.com/koreader/android-send2ebook)**

## How it works
PC/Android phone -> gets url -> download -> converts to epub -> stores to server(ftp) 

->

Ebook reader downloads it, and it's ready to read;) 

I only know [Koreader](https://github.com/koreader/koreader) has dedicated plugin (Send2Ebook (reciever)), but you can use your favourite app to download documents from ftp;)

## 3 simple steps
interfaces: [InputProcessor](https://github.com/mwoz123/send2ebook/blob/master/src/main/java/com/github/mwoz123/send2ebook/input/InputProcessor.java) -> [Creator](https://github.com/mwoz123/send2ebook/blob/master/src/main/java/com/github/mwoz123/send2ebook/creator/Creator.java) -> [Storage](https://github.com/mwoz123/send2ebook/blob/master/src/main/java/com/github/mwoz123/send2ebook/storage/Storage.java)

example implementation:

[UrlInputProcessor](https://github.com/mwoz123/send2ebook/blob/master/src/main/java/com/github/mwoz123/send2ebook/input/UrlInputProcessor.java) gets url, downloads data -> [EpubCreator](https://github.com/mwoz123/send2ebook/blob/master/src/main/java/com/github/mwoz123/send2ebook/creator/EpubCreator.java) converts data to Epub format -> [FtpStorage](https://github.com/mwoz123/send2ebook/blob/master/src/main/java/com/github/mwoz123/send2ebook/storage/ftp/FtpStorage.java) save epub file to ftp server

## Requirements:

- java (jre) >= 7.
- ftp account

You don't have ftp account? No worries. There're thousands [free on Internet](https://www.google.com/search?q=best+free+ftp+hosting)

## Configuration

Currently only property [file configuration](https://github.com/mwoz123/send2ebook/blob/master/commandline/FtpConnection.properties) is supported 

## Download


[Download](https://github.com/mwoz123/send2ebook/tree/master/commandline)

## Usage

unix:

`send2ebook.sh www.a_url.com`

windows:

`send2ebook.bat www.a_url.com`

Can send many articles at once:

`send2ebook.sh www.a_url.com www.another_url.eu www.other_url.com www.somethingelse.eu`




Feel free to extend it.
