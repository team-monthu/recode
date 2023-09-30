package com.monthu.recode.domain.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OIDCPublicKey {

  private String kid;
  private String alg;
  private String use;
  private String n;
  private String e;
}
