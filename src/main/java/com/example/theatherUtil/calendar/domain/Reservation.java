package com.example.theatherUtil.calendar.domain;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Reservation {
  private String movieName;

  private LocalDateTime startTime;
  private LocalDateTime endTime;

  private String location;
  private String id;

  public Reservation(String movieName, LocalDateTime startTime, LocalDateTime endTime,
      String location, String id) {
    this.movieName = movieName;
    this.startTime = startTime;
    this.endTime = endTime;
    this.location = location;
    this.id = id;
  }

  @Override
  public String toString() {
    return "Reservation{" +
        "movieName='" + movieName + '\'' +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", location='" + location + '\'' +
        ", id='" + id + '\'' +
        '}';
  }
}
