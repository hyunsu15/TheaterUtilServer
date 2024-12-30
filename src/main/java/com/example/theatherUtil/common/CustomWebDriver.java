package com.example.theatherUtil.common;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class CustomWebDriver  implements  WebDriver{
  private final WebDriver driver;

  CustomWebDriver(WebDriver driver) {
    this.driver = driver;
  }

  @Override
  public void get(String url) {
    driver.get(url);
  }

  @Override
  public @Nullable String getCurrentUrl() {
    return driver.getCurrentUrl();
  }

  @Override
  public @Nullable String getTitle() {
    return driver.getTitle();
  }

  @Override
  public List<WebElement> findElements(By by) {
    new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(by));
    return driver.findElements(by);
  }

  @Override
  public WebElement findElement(By by) {
    new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(by));
    return driver.findElement(by);
  }

  @Override
  public @Nullable String getPageSource() {
    return driver.getPageSource();
  }

  @Override
  public void close() {
    driver.close();
  }

  @Override
  public void quit() {
    driver.quit();
  }

  @Override
  public Set<String> getWindowHandles() {
    return driver.getWindowHandles();
  }

  @Override
  public String getWindowHandle() {
    return driver.getWindowHandle();
  }

  @Override
  public TargetLocator switchTo() {
    return driver.switchTo();
  }

  @Override
  public Navigation navigate() {
    return driver.navigate();
  }

  @Override
  public Options manage() {
    return driver.manage();
  }
}
