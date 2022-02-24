package com.bancow.process.domain.farm.dto.response;

import com.bancow.process.domain.farmImage.dto.response.FarmImageResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Step1ResponseDto {

    private Long id;
    private Long pageNum;

    private String farmName;
    private String name;
    private String email;
    private String farmAddress;
    private String farmPostCode;
    private String fodder;
    private Boolean identification;
    private Boolean ownFarm;
    private String breedingType;
    private String population;
    private Boolean cctv;
    private Boolean livestockFarmingBusinessRegistration;
    private Boolean facilitiesStructure;
    private Boolean annualFodderCostSpecification;
    private Boolean annualInspectionReport;
    private Boolean businessLicense;
    private List<FarmImageResponseDto> farmImageUrl;

    public Step1ResponseDto(Long id, Long pageNum, String farmName, String name, String email, String farmAddress, String farmPostCode, String fodder,
                            Boolean identification, Boolean ownFarm, String breedingType,
                            String population, Boolean cctv ,Boolean livestockFarmingBusinessRegistration,
                            Boolean facilitiesStructure, Boolean annualFodderCostSpecification,
                            Boolean annualInspectionReport, Boolean businessLicense,
                            List<FarmImageResponseDto> farmImageUrl) {
        this.id = id;
        this.pageNum = pageNum;
        this.farmName = farmName;
        this.name = name;
        this.email = email;
        this.farmAddress = farmAddress;
        this.farmPostCode = farmPostCode;
        this.fodder = fodder;
        this.identification = identification;
        this.ownFarm = ownFarm;
        this.breedingType = breedingType;
        this.population = population;
        this.cctv = cctv;
        this.livestockFarmingBusinessRegistration = livestockFarmingBusinessRegistration;
        this.facilitiesStructure = facilitiesStructure;
        this.annualFodderCostSpecification = annualFodderCostSpecification;
        this.annualInspectionReport = annualInspectionReport;
        this.businessLicense = businessLicense;
        this.farmImageUrl = farmImageUrl;
    }

}
