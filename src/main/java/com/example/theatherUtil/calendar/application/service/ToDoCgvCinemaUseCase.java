package com.example.theatherUtil.calendar.application.service;

import com.example.theatherUtil.calendar.application.service.reservation.CGVReservationUseCase;
import com.example.theatherUtil.calendar.application.service.signIn.SignInUseCase;
import com.example.theatherUtil.calendar.domain.Reservation;
import com.example.theatherUtil.calendar.domain.SIGN_TYPE;
import com.example.theatherUtil.calendar.domain.SignIn;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ToDoCgvCinemaUseCase implements TodoCinemaUseCase {
  private final List<SignInUseCase> signInUseCases;
  private final CGVReservationUseCase reservationUseCase;
  public List<Reservation> execute(SignIn signIn,WebDriver webDriver){
    if(signIn.isNotSignInType(SIGN_TYPE.CGV))
      return Collections.emptyList();
    signInUseCases.forEach(signInUseCase -> signInUseCase.execute(signIn,webDriver));

    return reservationUseCase.execute(signIn.id(),webDriver);
  }
}
