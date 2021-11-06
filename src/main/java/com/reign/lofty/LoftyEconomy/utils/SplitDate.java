package com.reign.lofty.LoftyEconomy.utils;

import java.time.LocalDate;
import java.time.Month;

public class SplitDate {

    public static LocalDate separateDayMonthYear(String date) {
        String day = date.substring(0, 2);
        String month = date.substring(3, 5);
        String year = date.substring(6);
        return LocalDate.of(Integer.parseInt(year), Month.of(Integer.parseInt(month)), Integer.parseInt(day));
    }
}