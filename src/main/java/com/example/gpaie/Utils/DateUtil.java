package com.example.gpaie.Utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DateUtil{
 
public static List<LocalDate> getWeekFromDate(LocalDate localDate){
   LocalDate listDays = localDate;
     // System.out.println("All the dates for the days in the week ="+Arrays.asList(DayOfWeek.values()).stream().map(listDays::with).collect(Collectors.toList()));
      return Arrays.asList(DayOfWeek.values()).stream().map(listDays::with).collect(Collectors.toList());
}
public static List<LocalDate> getMonthFromDate(LocalDate localDate){
  LocalDate listDays = localDate;
  YearMonth yearMonth= YearMonth.of(localDate.getYear(), localDate.getMonthValue());
  LocalDate firstday=yearMonth.atDay(1);
  LocalDate firstOf=yearMonth.plusMonths(1).atDay(1);
 
    // System.out.println("All the dates for the days in the week ="+Arrays.asList(DayOfWeek.values()).stream().map(listDays::with).collect(Collectors.toList()));
     return firstday.datesUntil(firstOf).collect(Collectors.toList());
}
}