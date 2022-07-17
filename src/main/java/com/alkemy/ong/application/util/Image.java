package com.alkemy.ong.application.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.UUID;
import com.alkemy.ong.domain.IImage;
import com.alkemy.ong.domain.Slide;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Image implements IImage {

  private final Slide slide;

  @Override
  public InputStream getFile() {
    byte[] decodedBytes = Base64.getDecoder().decode(slide.getBase64File());
    return new ByteArrayInputStream(decodedBytes);
  }

  @Override
  public String getFileName() {
    UUID uuid = UUID.randomUUID();
    return uuid.toString();
  }

  @Override
  public String getContentType() {
    return "image/jpeg";
  }

}
