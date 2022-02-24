package com.bancow.process.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DateType {
    SATURDAY("토요일"),       // 토요일
    SUNDAY("일요일"),         // 일요일
    HOLIDAY("공휴일"),        // 공휴일
    RESERVED("예약 불가");        // 예약 불가

    private final String dateName;
}
