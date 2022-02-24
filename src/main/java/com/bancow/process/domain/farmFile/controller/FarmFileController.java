package com.bancow.process.domain.farmFile.controller;

import com.bancow.process.domain.farm.service.FarmService;
import com.bancow.process.domain.farmFile.dto.request.FileUpdateRequestDto;
import com.bancow.process.domain.farmFile.service.FarmFileService;
import com.bancow.process.global.response.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@RestController
@RequiredArgsConstructor
public class FarmFileController {
    private final FarmFileService farmFileService;

    @PutMapping("/api/farm/{id}/files")
    public ApiResponseDto updateFile(@PathVariable Long id,
                                     @RequestBody @Valid FileUpdateRequestDto fileUpdateRequestDto) {
        farmFileService.saveFile(id, fileUpdateRequestDto);
        return ApiResponseDto.of(HttpStatus.OK);
    }
}
