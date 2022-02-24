package com.bancow.process.domain.farmFile.service;

import com.bancow.process.domain.farm.domain.Farm;
import com.bancow.process.domain.farmFile.domain.FarmFile;
import com.bancow.process.domain.farmFile.dto.request.FileUpdateRequestDto;
import com.bancow.process.domain.farmFile.repository.FarmFileRepository;
import com.bancow.process.domain.farm.repository.FarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FarmFileService {

    private final FarmRepository farmRepository;
    private final FarmFileRepository farmFileRepository;

    @Transactional
    public void saveFile(Long farmId, FileUpdateRequestDto fileUpdateRequestDto) {
        Farm farm = farmRepository.findById(farmId).orElseThrow(
                () -> new IllegalArgumentException("해당 농장이 없습니다. farmId =" + farmId)
        );

        // farmId 와 farmFile.getFileType() 을 파라미터로 파일을 검색한다.
        FarmFile farmFile
                = farmFileRepository.findFile(farmId, fileUpdateRequestDto.getFileType());

        // 파일이 없다면 FarmFile 객체를 생성한다.
        if (farmFile == null) {
            farmFileRepository.save(new FarmFile(farm,
                    fileUpdateRequestDto.getOriginalFileName(),
                    fileUpdateRequestDto.getChangedFileName(),
                    fileUpdateRequestDto.getFileUrl(),
                    fileUpdateRequestDto.getFileType()));

        // 파일이 있다면 FarmFile 객체를 업데이트한다.
        } else {
            farmFile.updateFile(fileUpdateRequestDto.getOriginalFileName(),
                    fileUpdateRequestDto.getChangedFileName(),
                    fileUpdateRequestDto.getFileUrl(),
                    fileUpdateRequestDto.getFileType());
        }

    }
}
