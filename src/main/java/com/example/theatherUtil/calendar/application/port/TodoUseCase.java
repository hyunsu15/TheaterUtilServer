package com.example.theatherUtil.calendar.application.port;

import com.example.theatherUtil.calendar.domain.Reservation;
import com.example.theatherUtil.calendar.domain.SignIn;
import java.util.List;
import org.openqa.selenium.WebDriver;

public interface TodoUseCase {
  List<Reservation> execute(SignIn signIn, WebDriver driver);
}
