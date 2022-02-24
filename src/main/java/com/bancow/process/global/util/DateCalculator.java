package com.bancow.process.global.util;

import com.bancow.process.domain.model.DateType;
import com.bancow.process.global.response.RequestDateResponseDto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class DateCalculator {

    public static List<RequestDateResponseDto> getWeekendList(LocalDate startDate, LocalDate endDate) {

        List WeekendList = new ArrayList();

        while (!startDate.isEqual(endDate)) {
            startDate = startDate.plusDays(1);
            if (startDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
                WeekendList.add(new RequestDateResponseDto(DateType.SATURDAY.getDateName(), startDate, DateType.SATURDAY));
            }
            if (startDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                WeekendList.add(new RequestDateResponseDto(DateType.SUNDAY.getDateName(), startDate, DateType.SUNDAY));
            }
        }

        return WeekendList;
    }

    public static LocalDate getDayAtEndOfMonthAfterAddNumToMonth(LocalDate date, int num) {
        return date
                .plusMonths(num)
                .plusDays(date.plusMonths(num).lengthOfMonth() - date.getDayOfMonth());
    }

}
