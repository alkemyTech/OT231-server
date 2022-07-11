package com.alkemy.ong.application.util;

import java.io.InputStream;

public interface IUploadImage {

  public String upload(InputStream image, String contentType, String fileName);

}
