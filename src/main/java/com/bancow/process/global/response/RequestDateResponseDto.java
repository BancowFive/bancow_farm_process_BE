package com.bancow.process.global.response;

import com.bancow.process.domain.model.DateType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestDateResponseDto {

    private String dateName;
    private String date;
    private DateType dateType;

    public RequestDateResponseDto(String dateName, LocalDate date, DateType dateType) {
        this.dateName = dateName;
        this.date = date.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        this.dateType = dateType;
    }
}
