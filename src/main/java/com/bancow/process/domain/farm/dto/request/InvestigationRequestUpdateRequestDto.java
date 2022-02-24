package com.bancow.process.domain.farm.dto.request;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class InvestigationRequestUpdateRequestDto {

    @NotNull
    private Long pageNum;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate investigationRequest;

}
