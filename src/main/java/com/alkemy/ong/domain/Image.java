package com.alkemy.ong.domain;

import java.io.InputStream;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Image {

  InputStream file;
  String fileName;
  String contentType;

}
