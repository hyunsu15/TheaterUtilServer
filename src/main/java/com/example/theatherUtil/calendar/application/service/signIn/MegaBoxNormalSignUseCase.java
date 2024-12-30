package com.example.theatherUtil.calendar.application.service.signIn;

import com.example.theatherUtil.calendar.domain.SIGN_TYPE;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

@Component
public class MegaBoxNormalSignUseCase implements SignInUseCase {
  public static final String URL = "https://m.megabox.co.kr";

  @Override
  public SIGN_TYPE getSignType() {
    return SIGN_TYPE.MEGABOX;
  }

  @Override
  public void closeMainPagePopup(WebDriver driver) {
    driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/label")).click();
    driver.findElement(By.xpath("/html/body/div[3]/div[2]/button")).click();
  }

  @Override
  public void moveSignUrl(WebDriver driver) {
    driver.get(URL);
    driver.findElement(By.xpath("/html/body/header/div[1]/div[2]/a[1]/i")).click();
    driver.switchTo().alert().accept();
  }

  @Override
  public String getUrl() {
    return URL;
  }

  @Override
  public String getIdXPathUrl() {
    return "/html/body/div[1]/div[1]/div[1]/form/div[1]/div/input";
  }

  @Override
  public String getPasswordXPathUrl() {
    return "/html/body/div[1]/div[1]/div[1]/form/div[2]/div/input";
  }

  @Override
  public String getSignInButtonXPathUrl() {
    return "/html/body/div[1]/div[1]/div[1]/div[2]/button";
  }
}
