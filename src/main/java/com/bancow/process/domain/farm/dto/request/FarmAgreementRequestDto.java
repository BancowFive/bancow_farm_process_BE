package com.bancow.process.domain.farm.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FarmAgreementRequestDto {

    @NotNull
    private Boolean serviceTerms1;

    @NotNull
    private Boolean serviceTerms2;

    @NotNull
    private Boolean serviceTerms3;

    @NotNull
    private Long pageNum;
}
