package com.example.demo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestDate {

	public static void main(String[] args) {
        LocalDate sevenDaysAgo = LocalDate.now().minusDays(0);
        String dateStr = sevenDaysAgo.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        
        System.out.println("dateStr:::"+ dateStr);
	}

}
