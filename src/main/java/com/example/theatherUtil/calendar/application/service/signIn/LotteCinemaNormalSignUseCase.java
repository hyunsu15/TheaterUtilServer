package com.example.theatherUtil.calendar.application.service.signIn;

import com.example.theatherUtil.calendar.domain.SIGN_TYPE;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

@Component
public class LotteCinemaNormalSignUseCase implements SignInUseCase {
  public static final String URL ="https://www.lottecinema.co.kr/";

  @Override
  public void moveSignUrl(WebDriver driver) {
      driver.findElement(By.xpath("/html/body/div[6]/div[2]/div/ul/li/a")).click();
      driver.switchTo().alert().accept();
  }

  @Override
  public String getUrl() {
    return URL;
  }

  @Override
  public SIGN_TYPE getSignType() {
    return SIGN_TYPE.LOTTE_CINEMA;
  }

  @Override
  public void closeMainPagePopup(WebDriver driver) {
    driver.findElement(By.xpath(    "/html/body/div[7]/div[12]/div/div[2]/a[1]")).click();
  }

  @Override
  public String getIdXPathUrl() {
    return "/html/body/div[5]/div[1]/div[2]/span[1]/input";
  }

  @Override
  public String getPasswordXPathUrl() {
    return "/html/body/div[5]/div[1]/div[2]/span[2]/input";
  }

  @Override
  public String getSignInButtonXPathUrl() {
    return "/html/body/div[5]/div[1]/div[4]/button";
  }

}
