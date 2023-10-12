package com.monthu.recode.domain.auth.dto.valid;

import java.util.Arrays;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class AllowedContentTypeValidator implements
    ConstraintValidator<AllowedContentType, MultipartFile> {

  private String[] allowedTypes;
  private String[] allowedExtensions;

  @Override
  public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
    if (multipartFile == null || multipartFile.isEmpty()) {
      return true;
    }
    return isValidExtension(multipartFile) && isValidContentType(multipartFile);
  }

  @Override
  public void initialize(AllowedContentType constraintAnnotation) {
    allowedExtensions = constraintAnnotation.allowedExtensions();
    allowedTypes = constraintAnnotation.allowedTypes();
  }

  private boolean isValidExtension(MultipartFile multipartFile) {
    String originalFilename = multipartFile.getOriginalFilename();
    String fileExtension = StringUtils.getFilenameExtension(originalFilename)
                                      .toLowerCase();
    return Arrays.asList(allowedExtensions)
                 .contains(fileExtension);
  }

  private boolean isValidContentType(MultipartFile multipartFile) {
    String contentType = multipartFile.getContentType();
    return Arrays.asList(allowedTypes)
                 .contains(contentType);
  }
}