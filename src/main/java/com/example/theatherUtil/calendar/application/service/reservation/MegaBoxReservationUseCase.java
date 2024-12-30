package com.example.theatherUtil.calendar.application.service.reservation;

import com.example.theatherUtil.calendar.domain.Reservation;
import com.example.theatherUtil.common.exception.BaseException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

@Component
public class MegaBoxReservationUseCase implements ReservationUseCase{

  @Override
  public void moveControllerUrl(WebDriver driver) {
     driver.findElement(By.xpath("/html/body/header/div[1]/div[2]/a[2]")).click();
  }


  @Override
  public boolean isOne(WebDriver driver) {
    return false;
  }

  @Override
  public String getReservationCount(WebDriver driver) {
    final String reservationCount = "count_ticket";
    return driver.findElement(By.className(reservationCount)).getText();
  }


  @Override
  public List<Reservation> getOneReservation(WebDriver driver, String id) {
    throw new BaseException("메가박스는 한개 그 표만 나오는 경우가 없음. 나오면 안됨.");
  }

  @Override
  public List<Reservation> getReservations(WebDriver driver, String id) {
    final String reservationCount = "count_ticket";
    driver.findElement(By.className(reservationCount)).click();

    String summary=driver.findElement(By.id("tktList")).getText();

    return MegaBoxReservation.makeReservations(summary,id);
  }
}
