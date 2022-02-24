package com.bancow.process.domain.farm.domain;

import com.bancow.process.domain.farmImage.domain.FarmImage;
import com.bancow.process.domain.model.InProgress;
import com.bancow.process.global.BaseTimeEntity;
import com.bancow.process.domain.farmFile.domain.FarmFile;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.bancow.process.domain.model.InProgress.*;

@Entity
@Table(name = "farm")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Farm extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "farm_id")
    private Long id;

    // 전화번호(필드이름 대체 예정)
    @Column(name = "phone_number",unique = true)
    @Pattern(regexp = "^01(?:0|1|[6-9])(\\d{3,4})(\\d{4})$",
            message = "올바르지 않은 휴대폰 번호 양식입니다.")
    private String phoneNumber;

    // 인증번호(필드이름 대체 예정)
    @Column(name = "password")
    private String password;

    // 이용 약관 동의
    @Column(name = "service_terms1")
    private Boolean serviceTerms1;

    // 개인정보 취금 위탁 동의
    @Column(name = "service_terms2")
    private Boolean serviceTerms2;

    // 개인정보 선택/수집 이용
    @Column(name = "service_terms3")
    private Boolean serviceTerms3;

    // 이메일
    @Column(name = "email")
    @Email
    private String email;

    // 페이지 저장
    @Column(name = "page_num")
    private Long pageNum;


    // 농장주 이름
    @Column(name = "name")
    private String name;

    // 사료
    @Column(name = "fodder")
    private String fodder;

    // 농장 주소
    @Column(name = "farm_address")
    private String farmAddress;

    // 농장 소재 행정구역
    @Column(name = "farm_province")
    private String farmProvince;

    // 농장 우편번호
    @Column(name = "farm_postcode")
    private String farmPostCode;

    // 농장 이름
    @Column(name = "farm_name")
    private String farmName;


    // 농장주 본인 확인
    @Column(name = "identification")
    private Boolean identification;

    // 농장 자가 조사
    @Column(name = "own_farm")
    private Boolean ownFarm;

    // 농장 사육 형태 조사
    @Column(name = "breeding_type")
    private String breedingType;

    // 가축의 수
    @Column(name = "population")
    private String population;

    // cctv 보유여부
    @Column(name = "cctv")
    private Boolean cctv;


    // 가축 사육업 등록증 유무
    @Column(name = "livestock_farming_business_registration")
    private Boolean livestockFarmingBusinessRegistration;

    // 축사시설 구조도 유무
    @Column(name = "facilities_structure")
    private Boolean facilitiesStructure;

    // 1년간 사료비 명세서 유무
    @Column(name = "annual_fodder_cost_specification")
    private Boolean annualFodderCostSpecification;

    // 1년간 출하 성적서 유무
    @Column(name = "annual_inspection_report")
    private Boolean annualInspectionReport;

    // 사업자 등록증 유무
    @Column(name = "business_license")
    private Boolean businessLicense;


    // 입점 상태
    @Enumerated(EnumType.STRING)
    @Column(name = "in_progress")
    private InProgress inProgress = STEP1_IN_PROGRESS;

    // 1차 제출 완료
    @Column(name = "step1_completed")
    private LocalDateTime step1Completed;

    // 2차 제출 완료
    @Column(name = "step2_completed")
    private LocalDateTime step2Completed;

    // 실사 요청일
    @Column(name = "investigation_request")
    private LocalDateTime investigationRequest;

    // 입점 완료
    @Column(name = "process_done")
    private LocalDateTime processDone;


    @JsonBackReference
    @OneToMany(mappedBy = "farm", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FarmImage> farmImage = new ArrayList<>();


    @JsonBackReference
    @OneToMany(mappedBy = "farm", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FarmFile> farmFile = new ArrayList<>();

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Builder
    public Farm(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public void updateFarm(String password) {
        this.password = password;
    }

    public void updatePageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    public void updateInvestigationRequest(Long pageNum, LocalDateTime investigationRequest) {
        this.pageNum = pageNum;
        this.investigationRequest = investigationRequest;
    }

    public void updateFarmAgreement(Boolean serviceTerms1, Boolean serviceTerms2, Boolean serviceTerms3, Long pageNum){
        this.serviceTerms1 = serviceTerms1;
        this.serviceTerms2 = serviceTerms2;
        this.serviceTerms3 = serviceTerms3;
        this.pageNum = pageNum;
    }

    public void updateFarmOwnerInfo(String name, String email, Long pageNum){
        this.name = name;
        this.email = email;
        this.pageNum = pageNum;
    }

    public void updateFarmInfo(String farmName, String farmAddress, String farmProvince, String farmPostCode, String fodder, Long pageNum) {
        this.farmName = farmName;
        this.farmAddress = farmAddress;
        this.farmProvince = farmProvince;
        this.farmPostCode = farmPostCode;
        this.fodder = fodder;
        this.pageNum = pageNum;
    }

    public void updateFarmInfoCheck(Boolean identification, Boolean ownFarm, String breedingType, String population, Boolean cctv,Long pageNum){
        this.identification = identification;
        this.ownFarm = ownFarm;
        this.breedingType = breedingType;
        this.population = population;
        this.cctv = cctv;
        this.pageNum = pageNum;
    }

    public void updateFilesInfoCheck(Boolean livestockFarmingBusinessRegistration, Boolean facilitiesStructure,
                                     Boolean annualFodderCostSpecification, Boolean annualInspectionReport,
                                     Boolean businessLicense, Long pageNum){
        this.livestockFarmingBusinessRegistration = livestockFarmingBusinessRegistration;
        this.facilitiesStructure = facilitiesStructure;
        this.annualFodderCostSpecification = annualFodderCostSpecification;
        this.annualInspectionReport = annualInspectionReport;
        this.businessLicense = businessLicense;
        this.pageNum = pageNum;
    }

    public void updateInProgress(Long pageNum, InProgress inProgress) {
        this.pageNum = pageNum;
        this.inProgress = inProgress;
    }

}
