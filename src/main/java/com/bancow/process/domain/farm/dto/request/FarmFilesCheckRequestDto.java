package com.bancow.process.domain.farm.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FarmFilesCheckRequestDto {

    @NotNull
    private Boolean livestockFarmingBusinessRegistration;

    @NotNull
    private Boolean facilitiesStructure;

    @NotNull
    private Boolean annualFodderCostSpecification;

    @NotNull
    private Boolean annualInspectionReport;

    @NotNull
    private Boolean businessLicense;

    @NotNull
    private Long pageNum;

}
