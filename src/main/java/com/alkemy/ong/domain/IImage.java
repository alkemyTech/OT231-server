package com.alkemy.ong.domain;

import java.io.InputStream;

public interface IImage {

  InputStream getFile();

  String getFileName();

  String getContentType();

}
