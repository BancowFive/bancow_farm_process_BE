package com.bancow.process.domain.farm.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LoginRequestDto{

    @NotBlank
    @Pattern(regexp = "^01(?:0|1|[6-9])(\\d{3,4})(\\d{4})$",
             message = "올바르지 않은 휴대폰 번호 양식입니다.")
    private String phoneNumber;

    @NotBlank
    @Pattern(regexp = "^(\\d{4})$",
             message = "올바르지 않은 인증번호 양식입니다.")
    private String password;

}
