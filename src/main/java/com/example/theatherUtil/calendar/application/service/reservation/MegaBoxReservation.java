package com.example.theatherUtil.calendar.application.service.reservation;

import com.example.theatherUtil.calendar.domain.Reservation;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class MegaBoxReservation {
   static List<Reservation> makeReservations(String text,String id){
    Queue<String> words = Arrays.stream(text.split("\n")).collect(
        Collectors.toCollection(LinkedList::new));


    List<Reservation> list = new ArrayList<>();

    LocalDate date=LocalDate.parse(
        words.poll().replaceAll("[^0-9.]",""), DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    while (!words.isEmpty()){
      List<String> summary= IntStream.range(0,5).mapToObj(x->words.poll()).toList();
      list.add(makeReservation(date,summary,id));
      if(!words.isEmpty()
          &&words.peek()
          .split("\\(")[0]
          .replaceAll("[0-9.]","")
          .trim()
          .isEmpty()
      )
        date=LocalDate.parse(
            words.poll().replaceAll("[^0-9.]",""), DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }
    return list;
  }


  static Reservation makeReservation(LocalDate date,List<String> summary,String id) {
    String title = summary.get(0);
    String location = summary.get(1);

    String[] times= summary.get(2).split("~");
    LocalDateTime startDateTime=getDate(date,times[0]);
    LocalDateTime endDateTime=getDate(date,times[1]);

    return new Reservation(title,startDateTime,endDateTime,location,id);
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
}
