package com.example.theatherUtil.calendar.application.service.reservation;

import com.example.theatherUtil.calendar.domain.Reservation;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

@Component
public class CGVReservationUseCase implements ReservationUseCase{
  public static final String RESERVATION_URL = "https://m.cgv.co.kr/WebApp/MyCgvV4/My_ReservationList.aspx";

  @Override
  public void moveControllerUrl(WebDriver driver) {
    // 버튼이 다른곳에서도 많아서 메인으로 갓는지 부터 확인해야 함. 그래서 돋보기로 설정함.
    driver.findElement(By.className("btn_search"));

    String moveControllerButton = "/html/body/div[2]/div[1]/header/div[1]/a";
    driver.findElement(By.xpath(moveControllerButton)).click();
  }

  @Override
  public String getReservationCount(WebDriver driver) {
    final String reservationCountXpath="/html/body/div[3]/div/div/div[2]/ul/li[4]/a";
    return driver.findElement(By.xpath(reservationCountXpath)).getAttribute("data-count");
  }

  @Override
  public List<Reservation> getOneReservation(WebDriver driver, String id) {
    driver.get(RESERVATION_URL);
    String title = driver.findElement(By.xpath("/html/body/div[2]/section/div/article[1]/div[2]/div[2]/strong")).getText();
    String reservation = driver.findElement(By.xpath("/html/body/div[2]/section/div/article[1]/div[3]/div[1]/ul[1]")).getText();
    return List.of(CGVReservation.makeReservation(title,reservation,LocalDate.now(),id));
  }

  @Override
  public List<Reservation> getReservations(WebDriver driver, String id) {
    driver.get(RESERVATION_URL);
    int size = driver.findElements(By.xpath("/html/body/div[2]/section/article/ul/li")).size();

    List<String> texts = new ArrayList<>();

    for (int i = 0; i < size; i++) {
      String reservation =String.format("/html/body/div[2]/section/article/ul/li[%d]/a",i+1);
      WebElement element=driver.findElement(By.xpath(reservation));
      texts.add(element.getText());
    }
    return CGVReservation.makeReservations(texts, LocalDate.now(ZoneId.of("Asia/Seoul")),id);
  }
}
