@if "%DEBUG%" == "" @echo off
java -Dconnection.file.path=FtpConnection.properties -jar send2ebook-with-dependecies-0.8.jar %*