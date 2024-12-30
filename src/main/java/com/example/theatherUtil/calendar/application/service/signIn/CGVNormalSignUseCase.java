package com.example.theatherUtil.calendar.application.service.signIn;

import com.example.theatherUtil.calendar.domain.SIGN_TYPE;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

@Component
public class CGVNormalSignUseCase implements SignInUseCase {
  public static final String ID_XPATH_URL= "/html/body/div[2]/form/section/div/div/div/div/div[2]/div[1]/input";
  public static final String PASSWORD_XPATH_URL= "/html/body/div[2]/form/section/div/div/div/div/div[2]/div[2]/input";
  public static final String LOGIN_XPATH_URL= "/html/body/div[2]/form/section/div/div/div/div/div[3]/button";
  private static final String URL = "https://m.cgv.co.kr";

  @Override
  public SIGN_TYPE getSignType() {
    return SIGN_TYPE.CGV;
  }

  @Override
  public void closeMainPagePopup(WebDriver driver) {
    driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/p/label")).click();
  }

  @Override
  public void moveSignUrl(WebDriver driver) {
    driver.get("https://m.cgv.co.kr/WebApp/Member/Login.aspx?RedirectURL=https%3a%2f%2fm.cgv.co.kr%2f");
  }

  @Override
  public String getUrl() {
    return URL;
  }

  @Override
  public String getIdXPathUrl() {
    return ID_XPATH_URL;
  }

  @Override
  public String getPasswordXPathUrl() {
    return PASSWORD_XPATH_URL;
  }

  @Override
  public String getSignInButtonXPathUrl() {
    return LOGIN_XPATH_URL;
  }
}
