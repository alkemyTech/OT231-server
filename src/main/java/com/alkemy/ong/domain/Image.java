package com.alkemy.ong.domain;

import java.io.InputStream;

public interface Image {

  InputStream getFile();

  String getFileName();

  String getContentType();

}
