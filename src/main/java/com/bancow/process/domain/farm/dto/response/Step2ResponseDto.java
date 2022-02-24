package com.bancow.process.domain.farm.dto.response;

import com.bancow.process.domain.farmFile.dto.response.FarmFileTypeResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Step2ResponseDto {
    private Long id;
    private List<FarmFileTypeResponseDto> farmFile;

    public Step2ResponseDto(Long id, List<FarmFileTypeResponseDto> farmFile) {
        this.id = id;
        this.farmFile = farmFile;
    }
}
