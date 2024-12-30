package com.example.theatherUtil.calendar.application.service.reservation;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MegaBoxReservationTest {
  private static Stream<Arguments> testcase(){
    return Stream.of(
        Arguments.of(
            "2024.12.31 (화)\n"
                + "하얼빈\n"
                + "전대(광주) 7관\n"
                + "09:00 ~ 11:03\n"
                + "E12\n"
                + "D-8\n"
                + "보고타: 마지막 기회의 땅\n"
                + "광주상무 별관 컴포트 7관_리클\n"
                + "14:10 ~ 16:06\n"
                + "D9\n"
                + "D-8\n"
                + "2025.01.05 (일)\n"
                + "하얼빈\n"
                + "남춘천 4관 [Laser]\n"
                + "09:20 ~ 11:18\n"
                + "H10\n"
                + "D-13\n"
                + "하얼빈\n"
                + "남춘천 4관 [Laser]\n"
                + "23:20 ~ 25:18\n"
                + "H10\n"
                + "D-13",
            "[Reservation{movieName='하얼빈', startTime=2024-12-31T09:00, endTime=2024-12-31T11:03, location='전대(광주) 7관', id=''}, Reservation{movieName='보고타: 마지막 기회의 땅', startTime=2024-12-31T14:10, endTime=2024-12-31T16:06, location='광주상무 별관 컴포트 7관_리클', id=''}, Reservation{movieName='하얼빈', startTime=2025-01-05T09:20, endTime=2025-01-05T11:18, location='남춘천 4관 [Laser]', id=''}, Reservation{movieName='하얼빈', startTime=2025-01-05T23:20, endTime=2025-01-06T01:18, location='남춘천 4관 [Laser]', id=''}]"
        ));
  }

  @ParameterizedTest
  @MethodSource("testcase")
  void 매칭된다(String test,String expect){
    Assertions.assertThat(MegaBoxReservation.makeReservations(test,"").toString()).isEqualTo(expect);
  }
}
