#!/bin/bash
java -Dconnection.file.path=FtpConnection.properties -jar send2ebook-with-dependecies.jar "$@"

