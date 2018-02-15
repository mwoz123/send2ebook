# send2ebook

## Decription
Send2Ebook lets you send articles found on PC/[Android](https://github.com/koreader/android-send2ebook) phone to your Ebook reader.

## How it works
PC/Android phone -> gets url -> download -> converts to epub -> stores to server(ftp) -> Epub reader downloads (and removes) epub from ftp (currently only [Koreader](https://github.com/koreader/koreader) has plugin but feel free to add it to your Ebook reader program;)

## 3 simple steps
interfaces: InputProcessor -> Creator -> Storage

e.g. implementations:

UrlInputProcessor gets url, downloads data -> EpubCreator converts data to Epub format -> FtpStorage save epub file to ftp server

Feel free to extend it.
