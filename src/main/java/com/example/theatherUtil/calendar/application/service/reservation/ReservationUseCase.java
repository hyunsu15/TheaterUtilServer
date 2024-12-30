package com.example.theatherUtil.calendar.application.service.reservation;

import com.example.theatherUtil.calendar.domain.Reservation;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.WebDriver;

public interface ReservationUseCase {
  default List<Reservation> execute(String id, WebDriver driver){
    moveControllerUrl(driver);
    if(isZero(driver))
      return Collections.EMPTY_LIST;
    if(isOne(driver))
      return getOneReservation(driver,id);

    return getReservations(driver,id);
  }

  void moveControllerUrl(WebDriver driver);


  default boolean isZero(WebDriver driver){
    int value =Integer.parseInt(getReservationCount(driver));
    return value==0;
  }
  default boolean isOne(WebDriver driver){
    int value =Integer.parseInt(getReservationCount(driver));
    return value==1;
  }

   String getReservationCount(WebDriver driver);

  List<Reservation> getOneReservation(WebDriver driver,String id);

  List<Reservation> getReservations(WebDriver driver,String id);
}
