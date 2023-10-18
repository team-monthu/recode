package com.monthu.recode.global.infra.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.monthu.recode.global.exception.S3UploadException;
import com.monthu.recode.global.property.S3Property;
import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class S3Uploader {

  private final AmazonS3Client amazonS3Client;
  private String bucket;

  public S3Uploader(AmazonS3Client amazonS3Client, S3Property s3Property) {
    this.amazonS3Client = amazonS3Client;
    this.bucket = s3Property.getBucket();
  }

  public void upload(String fileName, MultipartFile multipartFile) {
    if (multipartFile == null || multipartFile.isEmpty()) {
      return;
    }

    ObjectMetadata objectMetadata = new ObjectMetadata();
    objectMetadata.setContentLength(multipartFile.getSize());
    objectMetadata.setContentType(multipartFile.getContentType());

    try {
      amazonS3Client.putObject(
          new PutObjectRequest(bucket, fileName, multipartFile.getInputStream(),
              objectMetadata)
              .withCannedAcl(CannedAccessControlList.PublicRead));
    } catch (IOException e) {
      throw new S3UploadException("이미지 업로드에 실패하였습니다.");
    }
  }

}