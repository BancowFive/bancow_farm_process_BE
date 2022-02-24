package com.bancow.process.domain.farm.service;

import com.bancow.process.domain.model.DateType;
import com.bancow.process.domain.model.ErrorCode;
import com.bancow.process.domain.model.InProgress;
import com.bancow.process.global.exception.CustomException;
import com.bancow.process.domain.farm.dto.request.*;
import com.bancow.process.domain.farm.dto.response.InProgressResponseDto;
import com.bancow.process.domain.farm.dto.response.PasswordResponseDto;
import com.bancow.process.domain.farm.dto.response.Step1ResponseDto;
import com.bancow.process.domain.farm.dto.response.Step2ResponseDto;
import com.bancow.process.domain.farm.dto.response.Step3ResponseDto;
import com.bancow.process.domain.farm.repository.FarmRepository;
import com.bancow.process.domain.farm.domain.Farm;
import com.bancow.process.domain.farmImage.repository.FarmImageRepository;
import com.bancow.process.domain.farm.api.CertificationService;
import com.bancow.process.domain.farm.mapper.FarmMapper;
import com.bancow.process.global.response.RequestDateResponseDto;
import com.bancow.process.global.util.DateCalculator;
import com.bancow.process.global.util.HolidayApi;

import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import static com.bancow.process.global.util.LocalDateTimeConverter.LocalDateTimeToLocalDate;
import static com.bancow.process.global.util.LocalDateTimeConverter.LocalDateToLocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class FarmService {

    private final FarmRepository farmRepository;
    private final CertificationService certificationService;
    private final PasswordEncoder passwordEncoder;
    private final FarmMapper farmMapper;
    private final FarmImageRepository farmImageRepository;

    @Transactional
    public PasswordResponseDto join(String phoneNumber) {

        // userName으로 번호가 있는지 조회
        Optional<Farm> user = farmRepository.findByPhoneNumber(phoneNumber);

        //인증번호 생성z
        Random rand = new Random();
        String numStr = "";
        for (int i = 0; i < 4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr += ran;
        }

        // 생성한 랜덤 인증번호를 인코딩
        String password = passwordEncoder.encode(numStr);

        if (user.isEmpty()) {
            //farm 객체 생성해서 userName과 인코딩한 password 저장
            Farm farm = new Farm(phoneNumber, password);
            farmRepository.save(farm);

        } else {
            Farm farm = user.get();
            farm.updateFarm(password);
            farmRepository.save(farm);

        }

        // userName(폰 번호)과 인증번호 발송
        certificationService.certifiedPhoneNumber(phoneNumber, numStr);

        PasswordResponseDto passwordResponseDto = new PasswordResponseDto(numStr);

        return passwordResponseDto;
    }

    @Transactional
    public void updatePageNum(Long farmId, PageNumUpdateRequestDto pageNumUpdateRequestDto) {
        Farm farm = farmRepository.findById(farmId).orElseThrow(
                () -> new IllegalArgumentException("해당 농장이 없습니다. farmId =" + farmId)
        );

        farm.updatePageNum(pageNumUpdateRequestDto.getPageNum());

    }

    public InProgressResponseDto getInprogress(String phoneNumber) {
        Farm farm = farmRepository.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new IllegalArgumentException("해당 농장이 없습니다. phoneNumber =" + phoneNumber)
        );
        InProgressResponseDto inProgressResponseDto = new InProgressResponseDto(farm.getId(), farm.getInProgress(),farm.getPageNum());
        return inProgressResponseDto;
    }

    public Step1ResponseDto getStep1(Long id){

        Farm farm = farmRepository.findById(id).orElseThrow(
                () -> new NullPointerException("농장이 없습니다. ")
        );

        if(InProgress.getStep1InProgressList().contains(farm.getInProgress())){
            return farmMapper.createResponseStep1FarmEntity(farm);

        }else
            throw new IllegalArgumentException("잘못된 inprogress 입니다. ");

    }

    public Step2ResponseDto getStep2(Long id) {
        Farm farm = farmRepository.findById(id).orElseThrow(
                () -> new NullPointerException("농장이 없습니다. ")
        );

        if(InProgress.getStep2InProgressList().contains(farm.getInProgress())){
            return farmMapper.createResponseStep2FarmEntity(farm);
        }else
            throw new IllegalArgumentException("잘못된 inprogress 입니다. ");
    }

    public void updateFarmAgreement(Long id, FarmAgreementRequestDto farmAgreementDto){

        Farm farm = farmRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.FARM_NOT_FOUND)
        );

        farm.updateFarmAgreement(
                farmAgreementDto.getServiceTerms1(),
                farmAgreementDto.getServiceTerms2(),
                farmAgreementDto.getServiceTerms3(),
                farmAgreementDto.getPageNum());
    }

    public void updateFarmOwnerInfo(Long id, FarmOwnerInfoRequestDto farmOwnerInfoDto) {
        Farm farm = farmRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.FARM_NOT_FOUND)
        );

        farm.updateFarmOwnerInfo(
                farmOwnerInfoDto.getName(),
                farmOwnerInfoDto.getEmail(),
                farmOwnerInfoDto.getPageNum());
    }

    public void updateFarmInfo(Long id, FarmInfoRequestDto farmInfoDto) {
        Farm farm = farmRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.FARM_NOT_FOUND)
        );

        farm.updateFarmInfo(farmInfoDto.getFarmName(),
                farmInfoDto.getFarmAddress(),
                extractProvince(farmInfoDto),
                farmInfoDto.getFarmPostCode(),
                farmInfoDto.getFodder(),
                farmInfoDto.getPageNum());

    }

    public void updateFarmInfoCheck(Long id, FarmInfoCheckRequestDto farmInfoCheckDto) {

        Farm farm = farmRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.FARM_NOT_FOUND)
        );
        farm.updateFarmInfoCheck(
                farmInfoCheckDto.getIdentification(),
                farmInfoCheckDto.getOwnFarm(),
                farmInfoCheckDto.getBreedingType(),
                farmInfoCheckDto.getPopulation(),
                farmInfoCheckDto.getCctv(),
                farmInfoCheckDto.getPageNum());
    }

    public void updateFarmFilesCheck(Long id, FarmFilesCheckRequestDto farmFilesCheckDto) {
        Farm farm = farmRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.FARM_NOT_FOUND)
        );
        farm.updateFilesInfoCheck(farmFilesCheckDto.getLivestockFarmingBusinessRegistration(),
                farmFilesCheckDto.getFacilitiesStructure(),
                farmFilesCheckDto.getAnnualFodderCostSpecification(),
                farmFilesCheckDto.getAnnualInspectionReport(),
                farmFilesCheckDto.getBusinessLicense(),
                farmFilesCheckDto.getPageNum());
    }

    public void updateInvestigationRequest(Long farmId, InvestigationRequestUpdateRequestDto investigationRequestUpdateRequestDto) {
        Farm farm = farmRepository.findById(farmId).orElseThrow(
                () -> new IllegalArgumentException("해당 농장이 없습니다. farmId =" + farmId)
        );

        farm.updateInvestigationRequest(investigationRequestUpdateRequestDto.getPageNum(),
                LocalDateToLocalDateTime(investigationRequestUpdateRequestDto.getInvestigationRequest()));
    }

    public void updateInProgress(Long farmId, InProgressUpdateRequestDto inProgressUpdateRequestDto) {
        Farm farm = farmRepository.findById(farmId).orElseThrow(
                () -> new IllegalArgumentException("해당 농장이 없습니다. farmId =" + farmId)
        );

        farm.updateInProgress(inProgressUpdateRequestDto.getPageNum(),
                              inProgressUpdateRequestDto.getInProgress());
    }

    public void creatFarm(LoginRequestDto loginRequestDto){
        String password = passwordEncoder.encode(loginRequestDto.getPassword());
        Farm farm = new Farm(loginRequestDto.getPhoneNumber(),password);
        farmRepository.save(farm);
    }

    public String extractProvince(FarmInfoRequestDto farmInfoDto){
        return farmInfoDto.getFarmAddress().substring(0, 2);
    }

    public Step3ResponseDto getNoReservationAllowedList(Long id) throws IOException, ParseException {
        Farm farm = farmRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.FARM_NOT_FOUND)
        );

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = DateCalculator.getDayAtEndOfMonthAfterAddNumToMonth(startDate, 3);

        List<RequestDateResponseDto> requestDateResponseDtoList = new ArrayList<>();
        requestDateResponseDtoList.addAll(HolidayApi.getHoliday(startDate, endDate));
        requestDateResponseDtoList.addAll(DateCalculator.getWeekendList(startDate, endDate));
        requestDateResponseDtoList.addAll(getFarmReservationList(startDate, endDate));

        return new Step3ResponseDto(farm.getId(), requestDateResponseDtoList);
    }

    public List<RequestDateResponseDto> getFarmReservationList(LocalDate startDate, LocalDate endDate) {
        List<Farm> farm = farmRepository.findFarmsByInvestigationRequestIsNotNull();

        List<RequestDateResponseDto> ReservationList = farm.stream()
                .filter(o -> LocalDateTimeToLocalDate(o.getInvestigationRequest()).isAfter(startDate)
                        && LocalDateTimeToLocalDate(o.getInvestigationRequest()).isBefore(endDate.plusDays(1)))
                .map(o -> new RequestDateResponseDto(DateType.RESERVED.getDateName()
                        , LocalDateTimeToLocalDate(o.getInvestigationRequest()), DateType.RESERVED))
                .collect(Collectors.toList());

        return ReservationList;
    }


}
