package com.alkemy.ong.infrastructure.util;

import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alkemy.ong.application.util.IUploadImage;
import com.alkemy.ong.infrastructure.config.aws.AwsConfig;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class UploadImageDelegate implements IUploadImage {

  @Autowired
  private AwsConfig awsConfig;

  public String upload(InputStream image, String contentType, String fileName) {
    AmazonS3 s3Client = awsConfig.createClientConnection();
    s3Client.putObject(createPutObjectRequest(image, contentType, fileName));
    return s3Client.getUrl(awsConfig.getBucketName(), fileName).toExternalForm();
  }

  private PutObjectRequest createPutObjectRequest(InputStream input, String contentType,
      String key) {
    ObjectMetadata objectMetadata = new ObjectMetadata();
    objectMetadata.setContentType(contentType);

    return new PutObjectRequest(awsConfig.getBucketName(), key, input, objectMetadata)
        .withCannedAcl(CannedAccessControlList.PublicRead);
  }

}
