package com.bancow.process.domain.farm.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FarmInfoRequestDto {

    @NotBlank
    private String farmName;

    @NotBlank
    private String farmAddress;

    @NotBlank
    private String farmPostCode;

    @NotBlank
    private String fodder;

    @NotNull
    private Long pageNum;

}
