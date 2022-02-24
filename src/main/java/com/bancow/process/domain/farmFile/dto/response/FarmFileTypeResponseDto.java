package com.bancow.process.domain.farmFile.dto.response;

import com.bancow.process.domain.model.FileType;
import com.bancow.process.domain.farmFile.domain.FarmFile;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FarmFileTypeResponseDto {

    private Long farmFileId;
    private FileType fileType;

    public FarmFileTypeResponseDto(FarmFile farmFile) {
        this.farmFileId = farmFile.getId();
        this.fileType = farmFile.getFileType();
    }
}
