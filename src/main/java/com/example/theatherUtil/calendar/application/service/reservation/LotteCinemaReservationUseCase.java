package com.example.theatherUtil.calendar.application.service.reservation;

import com.example.theatherUtil.calendar.domain.Reservation;
import java.time.LocalDate;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

@Component
public class LotteCinemaReservationUseCase implements ReservationUseCase{

  @Override
  public void moveControllerUrl(WebDriver driver) {
  }

  @Override
  public String getReservationCount(WebDriver driver) {
    return getTicketElement(driver).getText();
  }

  @Override
  public boolean isZero(WebDriver driver) {
    return getReservationCount(driver).isEmpty();
  }

  @Override
  public List<Reservation> getOneReservation(WebDriver driver, String id) {
   getTicketElement(driver).click();
   String text =driver.findElement(By.className("area__info")).getText();

    return List.of(LotteCinemaReservation.makeReservation(text, LocalDate.now(),id));
  }

  @Override
  public List<Reservation> getReservations(WebDriver driver, String id) {
    getTicketElement(driver).click();
    List<String> text = driver.findElements(By.className("contlist")).stream()
        .map(WebElement::getText).toList();
    return LotteCinemaReservation.makeReservations(text,id);
  }
  private WebElement getTicketElement(WebDriver driver){
    String ticket ="/html/body/div[6]/div[2]/div";
    return driver.findElement(By.xpath(ticket));
  }
}
