package com.bancow.process.domain.farmImage.controller;

import com.bancow.process.domain.farmImage.dto.request.ImageUpdateRequestDto;
import com.bancow.process.domain.farmImage.service.FarmImageService;
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
public class FarmImageController {
    private final FarmImageService farmImageService;

    @PutMapping(value = "/api/farm/{id}/images")
    public ApiResponseDto updateImage(@PathVariable Long id,
                                      @RequestBody @Valid ImageUpdateRequestDto imageUpdateRequestDto) {
        farmImageService.saveImage(id, imageUpdateRequestDto);
        return ApiResponseDto.of(HttpStatus.OK);
    }
}
