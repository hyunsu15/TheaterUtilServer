package com.example.theatherUtil.calendar.adapter.in.web;

import com.example.theatherUtil.calendar.application.port.TodoUseCase;
import com.example.theatherUtil.calendar.domain.Reservation;
import com.example.theatherUtil.calendar.domain.SignIn;
import com.example.theatherUtil.common.CustomWebDriverTemplate;
import java.util.List;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class CalendarController {
  private  final TodoUseCase useCase;
  private final CustomWebDriverTemplate customWebDriverTemplate;
  @Bean
  public Function<List<CalendarRequest>, List<Reservation>> reverseString() {
    return values->
        values.parallelStream()
            .map(value->new SignIn(value.id(),value.password(),value.type()))
            .map(signIn-> customWebDriverTemplate.execute(useCase::execute,signIn))
            .flatMap(List::stream)
            .sorted((x,y)-> x.getStartTime().compareTo(y.getEndTime()))
            .toList();
  }
}

