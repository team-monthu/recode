package com.monthu.recode.global.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.monthu.recode.global.property.S3Property;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

  private String accessKey;
  private String secretKey;
  private String region;

  public S3Config(S3Property s3Property) {
    this.accessKey = s3Property.getAccessKey();
    this.secretKey = s3Property.getSecretKey();
    this.region = s3Property.getRegion();
  }

  @Bean
  public AmazonS3Client amazonS3Client() {
    BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
    return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                                                 .withRegion(region)
                                                 .withCredentials(
                                                     new AWSStaticCredentialsProvider(
                                                         awsCredentials))
                                                 .build();
  }
}