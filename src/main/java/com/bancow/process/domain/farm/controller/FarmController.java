package com.bancow.process.domain.farm.controller;

import com.bancow.process.domain.farm.dto.request.*;
import com.bancow.process.domain.farm.dto.response.InProgressResponseDto;
import com.bancow.process.domain.farm.dto.response.PasswordResponseDto;
import com.bancow.process.domain.farm.dto.response.Step1ResponseDto;
import com.bancow.process.domain.farm.dto.response.Step2ResponseDto;
import com.bancow.process.domain.farm.service.FarmService;
import com.bancow.process.domain.farmFile.service.FarmFileService;
import com.bancow.process.domain.farmImage.service.FarmImageService;
import com.bancow.process.global.response.ApiResponseDto;
import com.bancow.process.global.security.jwt.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;


@RestController
@RequiredArgsConstructor
public class FarmController {

    private final FarmService farmService;
    private final FarmFileService farmFileService;
    private final FarmImageService farmImageService;

    @PostMapping("/api/sendSMS")
    public ApiResponseDto sendUsername(@RequestParam String phoneNumber){
        PasswordResponseDto certificationNumber = farmService.join(phoneNumber);
        return ApiResponseDto.of(certificationNumber);
    }

    @GetMapping("api/farm/checkInProgress/{phoneNumber}")
    public ApiResponseDto<InProgressResponseDto> checkInprogress(@PathVariable String phoneNumber){
        InProgressResponseDto inProgressResponseDto = farmService.getInprogress(phoneNumber);
        return ApiResponseDto.of(inProgressResponseDto);

    }

    @GetMapping("api/farm/{id}/checkStep1")
    public ApiResponseDto<Step1ResponseDto> checkStep1(@PathVariable Long id){
        Step1ResponseDto step1Info = farmService.getStep1(id);
        return ApiResponseDto.of(step1Info);
    }

    @GetMapping("api/farm/{id}/checkStep2")
    public ApiResponseDto<Step2ResponseDto> checkStep2(@PathVariable Long id){
        Step2ResponseDto step2Info = farmService.getStep2(id);
        return ApiResponseDto.of(step2Info);
    }

    @PutMapping("/api/farm/{id}/move")
    public ApiResponseDto updatePageNum(@PathVariable Long id,
                              @RequestBody @Valid PageNumUpdateRequestDto pageNumUpdateRequestDto) {
        farmService.updatePageNum(id, pageNumUpdateRequestDto);
        return ApiResponseDto.of(HttpStatus.OK);
    }

    @PutMapping("/api/farm/{id}/agreement")
    public ApiResponseDto farmAgreement(@PathVariable Long id, @RequestBody @Valid FarmAgreementRequestDto farmAgreementDto){
        farmService.updateFarmAgreement(id, farmAgreementDto);
        return ApiResponseDto.of(HttpStatus.OK);
    }

    @PutMapping("/api/farm/{id}/ownerInfo")
    public ApiResponseDto farmOwnerInfo(@PathVariable Long id, @RequestBody @Valid FarmOwnerInfoRequestDto farmOwnerInfoDto){
        farmService.updateFarmOwnerInfo(id, farmOwnerInfoDto);
        return ApiResponseDto.of(HttpStatus.OK);
    }

    @PutMapping("/api/farm/{id}/info")
    public ApiResponseDto farmInfo(@PathVariable Long id, @RequestBody @Valid FarmInfoRequestDto farmInfoDto) {
        farmService.updateFarmInfo(id, farmInfoDto);
        return ApiResponseDto.of(HttpStatus.OK);
    }

    @PutMapping("/api/farm/{id}/infoCheck")
    public ApiResponseDto farmInfoCheck(@PathVariable Long id, @RequestBody @Valid FarmInfoCheckRequestDto farmInfoCheckDto){
        farmService.updateFarmInfoCheck(id, farmInfoCheckDto);
        return ApiResponseDto.of(HttpStatus.OK);
    }

    @PutMapping("/api/farm/{id}/filesCheck")
    public ApiResponseDto farmFilesCheck(@PathVariable Long id, @RequestBody @Valid FarmFilesCheckRequestDto farmFilesCheckDto){
        farmService.updateFarmFilesCheck(id, farmFilesCheckDto);
        return ApiResponseDto.of(HttpStatus.OK);
    }

    @PutMapping("/api/farm/{id}/requestDate")
    public ApiResponseDto updateInvestigationRequest(@PathVariable Long id,
                                           @RequestBody @Valid InvestigationRequestUpdateRequestDto investigationRequestUpdateRequestDto){
        farmService.updateInvestigationRequest(id, investigationRequestUpdateRequestDto);
        return ApiResponseDto.of(HttpStatus.OK);
    }

    @PutMapping("/api/farm/{id}/inProgress")
    public ApiResponseDto updateInProgress(@PathVariable Long id,
                                 @RequestBody @Valid InProgressUpdateRequestDto inProgressUpdateRequestDto) {
        farmService.updateInProgress(id, inProgressUpdateRequestDto);
        return ApiResponseDto.of(HttpStatus.OK);
    }

    @GetMapping("/api/farm/{id}/checkStep3")
    public ApiResponseDto checkStep3(@PathVariable Long id) throws IOException, ParseException {
        return ApiResponseDto.of(farmService.getNoReservationAllowedList(id));
    }

    @PostMapping("/api/auth/token")
    public ApiResponseDto checkJwtToken(HttpServletRequest request) {
        String header = request.getHeader(JwtProperties.HEADER_STRING);
        if(header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
            return ApiResponseDto.of(HttpStatus.BAD_REQUEST);
        }
        return ApiResponseDto.of(HttpStatus.OK);
    }
}
