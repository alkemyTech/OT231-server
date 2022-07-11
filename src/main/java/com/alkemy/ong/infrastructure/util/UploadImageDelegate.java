package com.alkemy.ong.infrastructure.util;

import com.alkemy.ong.application.exception.ThirdPartyException;
import com.alkemy.ong.application.util.IUploadImage;
import com.alkemy.ong.domain.Image;
import com.alkemy.ong.infrastructure.config.aws.AwsConfig;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadImageDelegate implements IUploadImage {

  @Autowired
  private AwsConfig awsConfig;

  @Override
  public String upload(Image image) {
    AmazonS3 s3Client = awsConfig.createClientConnection();
    s3Client.putObject(createPutObjectRequest(image));
    return s3Client.getUrl(awsConfig.getBucketName(), image.getFileName()).toExternalForm();
  }

  private PutObjectRequest createPutObjectRequest(Image image) {
    ObjectMetadata objectMetadata = new ObjectMetadata();
    try {
      objectMetadata.setContentType(image.getContentType());

      return new PutObjectRequest(
            awsConfig.getBucketName(), 
            image.getFileName(), 
            image.getFile(), 
            objectMetadata)
          .withCannedAcl(CannedAccessControlList.PublicRead);
    } catch (Exception e) {
      throw new ThirdPartyException(e.getMessage());
    }
  }

}
