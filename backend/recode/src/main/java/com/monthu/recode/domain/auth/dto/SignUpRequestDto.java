package com.monthu.recode.domain.auth.dto;

import com.monthu.recode.domain.auth.dto.valid.AllowedContentType;
import com.monthu.recode.domain.member.domain.OauthProvider;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {

  @NotBlank
  private String idToken;

  @NotNull
  private OauthProvider oauthProvider;

  @NotBlank
  @Size(max = 30)
  private String email;

  @NotBlank
  @Size(min = 2, max = 15)
  private String nickname;

  @Size(max = 10)
  private String company;

  @AllowedContentType(allowedTypes = {"image/jpg", "image/jpeg", "image/png"},
      allowedExtensions = {"jpg", "jpeg", "png"})
  private MultipartFile profileImage;

  private String profileImageUrl;

  @Size(max = 200)
  private String bio;

  @Size(max = 10)
  private List<@Min(1) Long> stackIds;

}
