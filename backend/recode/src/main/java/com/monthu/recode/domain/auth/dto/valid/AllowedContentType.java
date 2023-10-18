package com.monthu.recode.domain.auth.dto.valid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE_USE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AllowedContentTypeValidator.class)
public @interface AllowedContentType {

  String message() default "유효한 파일이 아닙니다.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String[] allowedTypes();

  String[] allowedExtensions();

}
