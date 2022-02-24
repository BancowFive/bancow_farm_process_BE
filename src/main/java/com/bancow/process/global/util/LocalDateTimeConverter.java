package com.bancow.process.global.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LocalDateTimeConverter {

    public static LocalDateTime LocalDateToLocalDateTime(LocalDate localDate) {
        return localDate.atStartOfDay();
    }

    public static LocalDate LocalDateTimeToLocalDate(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }

}
