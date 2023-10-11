package com.monthu.recode.domain.auth.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OIDCPublicKeyList {

  private List<OIDCPublicKey> keys;
}
