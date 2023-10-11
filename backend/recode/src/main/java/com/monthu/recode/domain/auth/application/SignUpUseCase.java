package com.monthu.recode.domain.auth.application;

import com.monthu.recode.domain.auth.application.jwt.JwtProvider;
import com.monthu.recode.domain.auth.application.oidc.IDTokenValidatorHandler;
import com.monthu.recode.domain.auth.application.oidc.OIDCMember;
import com.monthu.recode.domain.auth.dto.SignUpRequestDto;
import com.monthu.recode.domain.auth.dto.SignUpResponseDto;
import com.monthu.recode.domain.member.application.repository.MemberRepository;
import com.monthu.recode.domain.member.domain.Member;
import com.monthu.recode.domain.techStack.application.repository.TechStackRepository;
import com.monthu.recode.domain.techStack.domain.TechStack;
import com.monthu.recode.global.exception.AlreadyExistedMemberException;
import com.monthu.recode.global.infra.s3.S3Uploader;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class SignUpUseCase {

  private final String MEMBER_PROFILE_DIRECTORY = "member/profile/";
  private final String DEFAULT_IMAGE_URL = "https://recode-bucket.s3.ap-northeast-2.amazonaws.com/member/profile/defaultProfileImage.png";

  private final IDTokenValidatorHandler idTokenValidatorHandler;
  private final MemberRepository memberRepository;
  private final TechStackRepository techStackRepository;
  private final JwtProvider jwtProvider;
  private final S3Uploader s3Uploader;

  public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {
    OIDCMember oidcMember = idTokenValidatorHandler.getOidcMemberByProviderAndIDToken(
        signUpRequestDto.getOauthProvider(), signUpRequestDto.getIdToken());
    checkExistedMember(oidcMember);

    String profileImageFileName = getImageUrl(signUpRequestDto);
    s3Uploader.upload(profileImageFileName, signUpRequestDto.getProfileImage());

    List<TechStack> stacks = techStackRepository.findByIdIn(signUpRequestDto.getStackIds());
    increaseTaggedCount(stacks);

    Member member = createMember(oidcMember, signUpRequestDto, profileImageFileName, stacks);
    memberRepository.save(member);
    String accessToken = jwtProvider.generateAccessToken(member);
    String refreshToken = jwtProvider.generateRefreshToken();
    member.setRefreshToken(refreshToken);

    return SignUpResponseDto.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshToken)
                            .nickname(member.getNickname())
                            .profileImageUrl(member.getProfileImageUrl())
                            .build();
  }

  private Member createMember(OIDCMember oidcMember, SignUpRequestDto signUpRequestDto,
      String profileImageFileName, List<TechStack> stacks) {
    return Member.builder()
                 .oauthProvider(signUpRequestDto.getOauthProvider())
                 .oauthId(oidcMember.getOauthId())
                 .nickname(signUpRequestDto.getNickname())
                 .email(signUpRequestDto.getEmail())
                 .profileImageUrl(profileImageFileName)
                 .company(signUpRequestDto.getCompany())
                 .bio(signUpRequestDto.getBio())
                 .stacks(stacks)
                 .build();
  }

  private String getImageUrl(SignUpRequestDto signUpRequestDto) {
    if (!signUpRequestDto.getProfileImageUrl()
                         .isEmpty()) {
      return signUpRequestDto.getProfileImageUrl();
    }
    if (signUpRequestDto.getProfileImageUrl() == null
        && (signUpRequestDto.getProfileImage() == null || signUpRequestDto.getProfileImage()
                                                                          .isEmpty())) {
      return DEFAULT_IMAGE_URL;
    }
    return makeFileName(signUpRequestDto.getProfileImage());
  }

  private String makeFileName(MultipartFile profileImage) {
    String extension = StringUtils.getFilenameExtension(profileImage.getOriginalFilename());
    return MEMBER_PROFILE_DIRECTORY + UUID.randomUUID() + "." + extension;
  }

  private void checkExistedMember(OIDCMember oidcMember) {
    if (memberRepository.existsByOauthProviderAndOauthId(oidcMember.getOauthProvider(),
        oidcMember.getOauthId())) {
      throw new AlreadyExistedMemberException("이미 존재하는 회원입니다.");
    }
  }

  private void increaseTaggedCount(List<TechStack> stacks) {
    stacks.forEach(TechStack::tagged);
  }
}
