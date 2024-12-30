package com.example.theatherUtil.calendar.application.service.reservation;

import com.example.theatherUtil.calendar.domain.Reservation;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Queue;

class LotteCinemaReservation {

  static Reservation makeReservation(String text, LocalDate defaultDate,String id){
    String []words = Arrays.stream(text.split("\n"))
        .filter(word->!word.equalsIgnoreCase("TODAY"))
        .toArray(String[]::new);

    LocalDate date = matchDate(words[3]+words[4],defaultDate);
    String[] times= words[6].split("~");
    LocalDateTime startDate= getDate(date,times[0]);
    LocalDateTime endDate =getDate(date,times[1]);

    String title = words[8];
    String location = words[10];

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

    String []dates =dateDay[0].trim().split("\\.");
    int month = Integer.parseInt(dates[0]);
    int day = Integer.parseInt(dates[1]);
    LocalDate suggestDate=LocalDate.of(defaultDate.getYear(),month,day);
    if(suggestDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREAN).equals(dateDay[1]))
      return suggestDate;

    return LocalDate.of(defaultDate.getYear()+1,month,day);
  }
  static List<Reservation> makeReservations(List<String> text,String id){
    Queue<String> textQue = new LinkedList<>(text);

    List<Reservation> list = new ArrayList<>();
    while(!textQue.isEmpty()){
      String[] words =
          Arrays.stream(textQue.poll().split("\n"))
              .filter(word->!word.equalsIgnoreCase("TODAY"))
          .toArray(String[]::new);

      String title=words[0];
      String location=words[1];

      String[] dateTime= words[2].split("\\)");

      LocalDate date = LocalDate.parse(dateTime[0].trim().replaceAll("[^0-9.]",""), DateTimeFormatter.ofPattern(
          "yyyy.MM.dd"));

      String[] times=dateTime[1].split("~");
      LocalDateTime startDate= getDate(date,times[0]);
      LocalDateTime endDate =getDate(date,times[1]);
      list.add(new Reservation(title,startDate,endDate,location,id));
    }

    return list;
  }
}
