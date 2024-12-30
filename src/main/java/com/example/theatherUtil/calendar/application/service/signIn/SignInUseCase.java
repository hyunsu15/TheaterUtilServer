package com.example.theatherUtil.calendar.application.service.signIn;
//TODO 실패할때도 구현하기

import com.example.theatherUtil.calendar.domain.SIGN_TYPE;
import com.example.theatherUtil.calendar.domain.SignIn;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public interface SignInUseCase {
  default void execute(SignIn signIn,WebDriver driver){
    if(signIn.isNotSignInType(getSignType()))
      return ;
    driver.get(getUrl());
    closeMainPagePopup(driver);
    moveSignUrl(driver);
    driver.findElement(By.xpath(getIdXPathUrl())).sendKeys(signIn.id());
    driver.findElement(By.xpath(getPasswordXPathUrl())).sendKeys(signIn.password());
    driver.findElement(By.xpath(getSignInButtonXPathUrl())).click();
  }

  SIGN_TYPE getSignType();

  void closeMainPagePopup(WebDriver driver);
  void moveSignUrl(WebDriver driver);
  String getUrl();
  String getIdXPathUrl();
  String getPasswordXPathUrl();
  String getSignInButtonXPathUrl();

}
