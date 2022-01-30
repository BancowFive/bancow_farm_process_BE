package com.bancow.process.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FarmInfoDto {

    @NotBlank
    private String farmName;

    @NotBlank
    private String farmAddress;

    @NotBlank
    private String fodder;

    @NotNull
    private Long pageNum;

}
