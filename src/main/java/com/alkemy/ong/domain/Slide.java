package com.alkemy.ong.domain;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Slide implements IImage{

  private String imageUrl;
  private String text;
  private Integer order;
  private String base64File;
  
  @Override
  public InputStream getFile() {
    byte[] decodedBytes = Base64.getDecoder().decode(base64File);
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
