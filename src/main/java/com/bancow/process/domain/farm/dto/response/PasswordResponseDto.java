package com.bancow.process.domain.farm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PasswordResponseDto {

    private String password;

    public PasswordResponseDto(String password) {
        this.password = password;
    }
}
