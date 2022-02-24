package com.bancow.process.service;

import com.bancow.process.domain.farm.domain.Farm;
import com.bancow.process.domain.farm.dto.request.*;
import com.bancow.process.domain.farm.repository.FarmRepository;
import com.bancow.process.domain.farm.service.FarmService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class FarmServiceTest {

    @Autowired
    private FarmService farmService;

    @Autowired
    private FarmRepository farmRepository;

    @AfterEach
    public void cleanup() {
        farmRepository.deleteAll();
    }

    @Test
    public void updateFarmAgreementTest(){
        Farm saveFarm = farmRepository.save(Farm
                .builder()
                .phoneNumber("01012345678")
                .password("1234")
                .build());

        FarmAgreementRequestDto testDto = FarmAgreementRequestDto.builder()
                .serviceTerms1(true)
                .serviceTerms2(true)
                .serviceTerms3(false)
                .pageNum(1L)
                .build();

        farmService.updateFarmAgreement(saveFarm.getId(), testDto);

        System.out.println(farmRepository.findAll());
    }

    @Test
    public void updateFarmOwnerInfoTest(){
        Farm saveFarm = farmRepository.save(Farm
                .builder()
                .phoneNumber("01012345678")
                .password("1234")
                .build());

        FarmOwnerInfoRequestDto testDto = FarmOwnerInfoRequestDto.builder()
                .name("농장주 이름")
                .email("email@naver.com")
                .pageNum(1L)
                .build();

        farmService.updateFarmOwnerInfo(saveFarm.getId(), testDto);

        System.out.println(farmRepository.findAll());
    }

    @Test
    public void updateFarmInfoTest(){
        Farm saveFarm = farmRepository.save(Farm
                .builder()
                .phoneNumber("01012345678")
                .password("1234")
                .build());

        FarmInfoRequestDto testDto = FarmInfoRequestDto.builder()
                .farmName("농장이름")
                .farmAddress("농장주소")
                .farmPostCode("123-456")
                .fodder("사료")
                .pageNum(31L)
                .build();

        farmService.updateFarmInfo(saveFarm.getId(), testDto);

        System.out.println(farmRepository.findAll());

    }

    @Test
    public void updateFarmInfoCheckTest(){
        Farm saveFarm = farmRepository.save(Farm
                .builder()
                .phoneNumber("01012345678")
                .password("1234")
                .build());

        FarmInfoCheckRequestDto testDto = FarmInfoCheckRequestDto.builder()
                .identification(true)
                .ownFarm(true)
                .breedingType("비육")
                .population("100마리 이상")
                .cctv(true)
                .pageNum(4L)
                .build();

        farmService.updateFarmInfoCheck(saveFarm.getId(), testDto);

        System.out.println(farmRepository.findAll());

    }

    @Test
    public void updateFarmFilesCheckTest(){
        Farm saveFarm = farmRepository.save(Farm
                .builder()
                .phoneNumber("01012345678")
                .password("1234")
                .build());

        FarmFilesCheckRequestDto testDto = FarmFilesCheckRequestDto.builder()
                .livestockFarmingBusinessRegistration(true)
                .facilitiesStructure(true)
                .annualFodderCostSpecification(true)
                .annualInspectionReport(true)
                .businessLicense(true)
                .pageNum(5L)
                .build();

        farmService.updateFarmFilesCheck(saveFarm.getId(), testDto);

        System.out.println(farmRepository.findAll());

    }
}
