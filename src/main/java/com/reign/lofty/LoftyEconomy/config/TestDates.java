package com.reign.lofty.LoftyEconomy.config;

import java.time.LocalDate;
import java.time.Month;

public class TestDates {

    public static void main(String[] args) {
        System.err.println(Month.of(LocalDate.now().getMonthValue() - 1));
    }
}