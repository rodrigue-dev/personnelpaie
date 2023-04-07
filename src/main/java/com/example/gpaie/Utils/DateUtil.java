package com.example.gpaie.Utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DateUtil{
 
public static List<LocalDate> getWeekFromDate(LocalDate localDate){
   LocalDate listDays = LocalDate.now();
     // System.out.println("All the dates for the days in the week ="+Arrays.asList(DayOfWeek.values()).stream().map(listDays::with).collect(Collectors.toList()));
      return Arrays.asList(DayOfWeek.values()).stream().map(listDays::with).collect(Collectors.toList());
}
}