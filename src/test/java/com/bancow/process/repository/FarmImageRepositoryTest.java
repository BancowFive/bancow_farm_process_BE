package com.bancow.process.repository;

import com.bancow.process.domain.farm.domain.Farm;
import com.bancow.process.domain.FarmImage;
import com.bancow.process.domain.model.ImageType;
import com.bancow.process.domain.farm.repository.FarmRepository;
import com.bancow.process.domain.farmImage.repository.FarmImageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.bancow.process.domain.model.ImageType.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FarmImageRepositoryTest {

    @Autowired
    FarmRepository farmRepository;

    @Autowired
    FarmImageRepository farmImageRepository;

    @AfterEach
    public void cleanup() {
        farmRepository.deleteAll();
        farmImageRepository.deleteAll();
    }

    @Test
    public void findImage_success() {

        // given
        Farm saveFarm = farmRepository.save(Farm.builder()
                .phoneNumber("01012345678")
                .password("1234")
                .build());

        String originalImageName = "AAA.jpg";
        String changedImageName = "BBBB.jpg";
        String imageUrl = "http://localhost:8888/";
        ImageType imageType = CATTLE_SIDE;

        farmImageRepository.save(FarmImage.builder()
                .farm(saveFarm)
                .originalImageName(originalImageName)
                .changedImageName(changedImageName)
                .imageUrl(imageUrl)
                .imageType(imageType)
                .build());

        // when
        FarmImage findFarmImage = farmImageRepository.findImage(saveFarm.getId(), CATTLE_SIDE);

        // then
        assertThat(findFarmImage.getFarm().getId()).isEqualTo(saveFarm.getId());
        assertThat(findFarmImage.getOriginalImageName()).isEqualTo(originalImageName);
        assertThat(findFarmImage.getChangedImageName()).isEqualTo(changedImageName);
        assertThat(findFarmImage.getImageUrl()).isEqualTo(imageUrl);
        assertThat(findFarmImage.getImageType()).isEqualTo(imageType);
    }

}