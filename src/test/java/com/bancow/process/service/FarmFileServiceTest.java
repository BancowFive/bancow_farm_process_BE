package com.bancow.process.service;

import com.bancow.process.domain.farm.domain.Farm;
import com.bancow.process.domain.farmFile.domain.FarmFile;
import com.bancow.process.domain.model.FileType;
import com.bancow.process.domain.farmFile.dto.request.FileUpdateRequestDto;
import com.bancow.process.domain.farmFile.repository.FarmFileRepository;
import com.bancow.process.domain.farm.repository.FarmRepository;
import com.bancow.process.domain.farmFile.service.FarmFileService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.bancow.process.domain.model.FileType.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class FarmFileServiceTest {

    @Autowired
    private FarmRepository farmRepository;

    @Autowired
    private FarmFileRepository farmFileRepository;

    @Autowired
    private FarmFileService farmFileService;

    @AfterEach
    public void cleanup() {
        farmRepository.deleteAll();
        farmFileRepository.deleteAll();
    }

    @Test
    public void saveFile_success() throws Exception {
        //1. FarmFile 객체 생성 테스트
        //given
        Farm saveFarm = farmRepository.save(Farm
                .builder()
                .phoneNumber("01012345678")
                .password("1234")
                .build());

        String originalFileName1 = "A.jpg";
        String changedFileName1 = "B.jpg";
        String fileUrl1 = "http://localhost:8080/";
        FileType fileType1 = ID_CARD;

        FileUpdateRequestDto fileUpdateRequestDto1 = FileUpdateRequestDto
                .builder()
                .originalFileName(originalFileName1)
                .changedFileName(changedFileName1)
                .fileUrl(fileUrl1)
                .fileType(fileType1)
                .build();

        //when
        farmFileService.saveFile(saveFarm.getId(), fileUpdateRequestDto1);
        FarmFile farmFile1 = farmFileRepository.findFile(saveFarm.getId(), fileUpdateRequestDto1.getFileType());

        //then
        assertThat(farmFile1.getOriginalFileName()).isEqualTo(fileUpdateRequestDto1.getOriginalFileName());
        assertThat(farmFile1.getChangedFileName()).isEqualTo(fileUpdateRequestDto1.getChangedFileName());
        assertThat(farmFile1.getFileUrl()).isEqualTo(fileUpdateRequestDto1.getFileUrl());
        assertThat(farmFile1.getFileType()).isEqualTo(fileUpdateRequestDto1.getFileType());

        //2. FarmFile 객체 수정 테스트
        //given
        String originalFileName2 = "C.jpg";
        String changedFileName2 = "D.jpg";
        String fileUrl2 = "http://localhost:8888/";
        FileType fileType2 = BUSINESS_REGISTRATION;

        FileUpdateRequestDto fileUpdateRequestDto2 = FileUpdateRequestDto
                .builder()
                .originalFileName(originalFileName2)
                .changedFileName(changedFileName2)
                .fileUrl(fileUrl2)
                .fileType(fileType2)
                .build();
        //when
        farmFileService.saveFile(saveFarm.getId(), fileUpdateRequestDto2);
        FarmFile farmFile2 = farmFileRepository.findFile(saveFarm.getId(), fileUpdateRequestDto2.getFileType());

        //then
        assertThat(farmFile2.getOriginalFileName()).isEqualTo(fileUpdateRequestDto2.getOriginalFileName());
        assertThat(farmFile2.getChangedFileName()).isEqualTo(fileUpdateRequestDto2.getChangedFileName());
        assertThat(farmFile2.getFileUrl()).isEqualTo(fileUpdateRequestDto2.getFileUrl());
        assertThat(farmFile2.getFileType()).isEqualTo(fileUpdateRequestDto2.getFileType());
    }


}