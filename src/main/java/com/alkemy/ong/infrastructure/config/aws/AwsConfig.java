package com.alkemy.ong.infrastructure.config.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "aws")
@Configuration
@Data
public class AwsConfig {

  private String bucketName;
  private String publicKey;
  private String secretKey;
  private String region;

  @Bean
  public AmazonS3 createClientConnection() {
    AWSCredentials credentials = new BasicAWSCredentials(
            publicKey,
            secretKey
    );
    return AmazonS3ClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(Regions.fromName(region))
            .build();
  }

  public String getBucketName() {
    return this.bucketName;
  }

}
