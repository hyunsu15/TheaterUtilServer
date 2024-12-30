package com.example.theatherUtil.calendar.application.service;

import com.example.theatherUtil.calendar.application.port.TodoUseCase;
import com.example.theatherUtil.calendar.domain.Reservation;
import com.example.theatherUtil.calendar.domain.SignIn;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ToDoService implements TodoUseCase {
  private final List<TodoCinemaUseCase> useCases;
  public List<Reservation> execute(SignIn signIn, WebDriver driver){
    return useCases.stream()
        .map(usecase->usecase.execute(signIn,driver))
        .flatMap(List::stream)
        .toList();
  }
}
