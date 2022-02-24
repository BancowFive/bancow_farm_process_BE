package com.bancow.process.repository;

import com.bancow.process.domain.farm.domain.Farm;
import com.bancow.process.domain.farmFile.domain.FarmFile;
import com.bancow.process.domain.model.FileType;
import com.bancow.process.domain.farm.repository.FarmRepository;
import com.bancow.process.domain.farmFile.repository.FarmFileRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import static com.bancow.process.domain.model.FileType.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FarmFileRepositoryTest {

    @Autowired
    FarmRepository farmRepository;

    @Autowired
    FarmFileRepository farmFileRepository;

    @AfterEach
    public void cleanup() {
        farmRepository.deleteAll();
        farmFileRepository.deleteAll();
    }

    @Test
    public void findFile_success() {

        // given
        Farm saveFarm = farmRepository.save(Farm.builder()
                .phoneNumber("01012345678")
                .password("1234")
                .build());

        String originalFileName = "A.jpg";
        String changedFileName = "B.jpg";
        String fileUrl = "http://localhost:8080/";
        FileType fileType = ID_CARD;

        farmFileRepository.save(FarmFile.builder()
                .farm(saveFarm)
                .originalFileName(originalFileName)
                .changedFileName(changedFileName)
                .fileUrl(fileUrl)
                .fileType(fileType)
                .build());

        // when
        FarmFile findFarmFile = farmFileRepository.findFile(saveFarm.getId(), ID_CARD);

        // then
        assertThat(findFarmFile.getFarm().getId()).isEqualTo(saveFarm.getId());
        assertThat(findFarmFile.getOriginalFileName()).isEqualTo(originalFileName);
        assertThat(findFarmFile.getChangedFileName()).isEqualTo(changedFileName);
        assertThat(findFarmFile.getFileUrl()).isEqualTo(fileUrl);
        assertThat(findFarmFile.getFileType()).isEqualTo(fileType);
    }

    @Test
    public void findFile_fail_IncorrectResultSizeDataAccessException() {
        // given
        Farm saveFarm = farmRepository.save(Farm.builder()
                .phoneNumber("01012345678")
                .password("1234")
                .build());

        String originalFileName = "A.jpg";
        String changedFileName = "B.jpg";
        String fileUrl = "http://localhost:8080/";
        FileType fileType = ID_CARD;

        farmFileRepository.save(FarmFile.builder()
                .farm(saveFarm)
                .originalFileName(originalFileName)
                .changedFileName(changedFileName)
                .fileUrl(fileUrl)
                .fileType(fileType)
                .build());

        farmFileRepository.save(FarmFile.builder()
                .farm(saveFarm)
                .originalFileName(originalFileName)
                .changedFileName(changedFileName)
                .fileUrl(fileUrl)
                .fileType(fileType)
                .build());

        // when
        // then
        assertThrows(IncorrectResultSizeDataAccessException.class, () -> {
            farmFileRepository.findFile(saveFarm.getId(), ID_CARD);
        });

    }

}