package com.bancow.process.service;

import com.bancow.process.domain.model.ImageType;
import com.bancow.process.domain.farmImage.dto.request.ImageUpdateRequestDto;
import com.bancow.process.domain.farm.domain.Farm;
import com.bancow.process.domain.farmImage.repository.FarmImageRepository;
import com.bancow.process.domain.farm.repository.FarmRepository;
import com.bancow.process.domain.farmImage.service.FarmImageService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.bancow.process.domain.model.ImageType.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FarmImageServiceTest {

    @Autowired
    private FarmRepository farmRepository;

    @Autowired
    private FarmImageRepository farmImageRepository;

    @Autowired
    private FarmImageService farmImageService;

    @AfterEach
    public void cleanup() {
        farmRepository.deleteAll();
        farmImageRepository.deleteAll();
    }

    @Test
    public void saveImage_success() throws Exception {
        //1. FarmImage 객체 생성 테스트
        //given
        Farm saveFarm = farmRepository.save(Farm
                .builder()
                .phoneNumber("01012345678")
                .password("1234")
                .build());

        String originalImageName1 = "AAA.jpg";
        String changedImageName1 = "BBB.jpg";
        String imageUrl1 = "http://localhost:8080/";
        ImageType imageType1 = BUCKET;

        ImageUpdateRequestDto imageUpdateRequestDto1 = ImageUpdateRequestDto
                .builder()
                .originalImageName(originalImageName1)
                .changedImageName(changedImageName1)
                .imageUrl(imageUrl1)
                .imageType(imageType1)
                .build();

        //when
        farmImageService.saveImage(saveFarm.getId(), imageUpdateRequestDto1);
        FarmImage farmImage1 = farmImageRepository.findImage(saveFarm.getId(), imageUpdateRequestDto1.getImageType());

        //then
        assertThat(farmImage1.getOriginalImageName()).isEqualTo(imageUpdateRequestDto1.getOriginalImageName());
        assertThat(farmImage1.getChangedImageName()).isEqualTo(imageUpdateRequestDto1.getChangedImageName());
        assertThat(farmImage1.getImageUrl()).isEqualTo(imageUpdateRequestDto1.getImageUrl());
        assertThat(farmImage1.getImageType()).isEqualTo(imageUpdateRequestDto1.getImageType());

        //2. FarmFile 객체 수정 테스트
        //given
        String originalImageName2 = "C.jpg";
        String changedImageName2 = "D.jpg";
        String imageUrl2 = "http://localhost:8888/";
        ImageType imageType2 = FLOOR;

        ImageUpdateRequestDto imageUpdateRequestDto2 = ImageUpdateRequestDto
                .builder()
                .originalImageName(originalImageName2)
                .changedImageName(changedImageName2)
                .imageUrl(imageUrl2)
                .imageType(imageType2)
                .build();
        //when
        farmImageService.saveImage(saveFarm.getId(), imageUpdateRequestDto2);
        FarmImage farmImage2 = farmImageRepository.findImage(saveFarm.getId(), imageUpdateRequestDto2.getImageType());

        //then
        assertThat(farmImage2.getOriginalImageName()).isEqualTo(imageUpdateRequestDto2.getOriginalImageName());
        assertThat(farmImage2.getChangedImageName()).isEqualTo(imageUpdateRequestDto2.getChangedImageName());
        assertThat(farmImage2.getImageUrl()).isEqualTo(imageUpdateRequestDto2.getImageUrl());
        assertThat(farmImage2.getImageType()).isEqualTo(imageUpdateRequestDto2.getImageType());
    }
}