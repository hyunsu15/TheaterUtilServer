package com.example.theatherUtil.common;

import com.example.theatherUtil.common.exception.BaseException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomWebDriverTemplate {
    public  <T,R> R execute(BiFunction<T, ? super WebDriver, R> fun,T input){
        WebDriver driver = getSettingDriver();
        try{
            return fun.apply(input,driver);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new BaseException("셀리니움 에러.\n"+e.getMessage());
        }finally {
            driver.quit();
        }
    }


    private WebDriver getSettingDriver(){
        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName","Surface Duo");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        chromeOptions.addArguments("--ignore-certificate-errors");
        WebDriver driver = new ChromeDriver(chromeOptions);
        return new CustomWebDriver(driver);
    }
}
