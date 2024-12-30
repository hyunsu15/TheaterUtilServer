package com.example.theatherUtil.calendar.application.service.reservation;

import com.example.theatherUtil.calendar.domain.Reservation;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class CGVReservation{

  static Reservation makeReservation(String title,String summary,LocalDate defaultDate,String id) {
    String[] words=summary.split("\n");
    LocalDate date = matchDate(words[1],defaultDate);

    String[] times= words[3].split("~");
    LocalDateTime startDate= getDate(date,times[0]);
    LocalDateTime endDate =getDate(date,times[1]);
    String location = words[5];

    return new Reservation(title,startDate,endDate,location,id);
  }
  private static LocalDateTime getDate(LocalDate date, String times) {
    String[] time=times.trim().split(":");
    long weight = (long)Math.pow(60,time.length);
    long seconds =  0;
    for (String s : time) {
      seconds+=Integer.parseInt(s)*weight;
      weight/=60;
    }
    return LocalDateTime.of(date,LocalTime.of(0,0)).plusSeconds(seconds);
  }


  private static LocalDate matchDate(String word,LocalDate defaultDate) {
    String removeNotWord= word.trim().replaceAll("[()]+"," ");
    String[] dateDay=removeNotWord.split(" ");

    String []dates =dateDay[0].trim().split("/");
    int month = Integer.parseInt(dates[0]);
    int day = Integer.parseInt(dates[1]);
    LocalDate suggestDate=LocalDate.of(defaultDate.getYear(),month,day);
    if(suggestDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREAN).equals(dateDay[1]))
      return suggestDate;

    return suggestDate.plusYears(1);
  }

  static List<Reservation> makeReservations(List<String> summarys,LocalDate defaultDate,String id){
    List<Reservation> list = new ArrayList<>();
    for (String summary : summarys) {
      String[] words=summary.split("\n");
      String movieName=words[0];
      LocalDate date = initializeDate(words[2],defaultDate);
      String[] times=words[2]
          .split("\\)")[1]
          .replaceAll("[^0-9:~]","")
          .trim()
          .split("~");
      LocalDateTime startDate= getDate(date,times[0]);
      LocalDateTime endDate =getDate(date,times[1]);

      String location= words[3];
      list.add(new Reservation(movieName,startDate,endDate,location,id));
    }
    return list;
  }
  private static LocalDate initializeDate(String word,LocalDate defaultDate){
    if(word.contains("내일"))
      return defaultDate.plusDays(1);
    if(word.contains("오늘"))
      return defaultDate;
    return LocalDate.parse(word.substring(0,word.indexOf('(')), DateTimeFormatter.ofPattern("yyyy.MM.dd"));
  }
}
