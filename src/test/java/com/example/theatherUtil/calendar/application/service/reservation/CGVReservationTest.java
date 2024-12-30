package com.example.theatherUtil.calendar.application.service.reservation;

import com.example.theatherUtil.calendar.domain.Reservation;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CGVReservationTest {

  private static Stream<Arguments> reservation(){
    return Stream.of(
        Arguments.of(
            "시빌 워-분열의 시대",
            "상영일\n"
                + "1/4(토)\n"
                + "상영시간\n"
                + "17:30~ 19:29\n"
                + "상영관\n"
                + "CGV 광주금남로\n"
                + "4관 3층 (리클라이너,Laser)\n"
                + "좌석\n"
                + "일반 1\n"
                + "C05\n"
                + "좌석보기",
            "Reservation{movieName='시빌 워-분열의 시대', startTime=2025-01-04T17:30, endTime=2025-01-04T19:29, location='CGV 광주금남로', id=''}"
        ),Arguments.of(
            "시빌 워-분열의 시대",
            "상영일\n"
                + "1/4(토)\n"
                + "상영시간\n"
                + "25:10~ 28:00\n"
                + "상영관\n"
                + "CGV 광주금남로\n"
                + "4관 3층 (리클라이너,Laser)\n"
                + "좌석\n"
                + "일반 1\n"
                + "C05\n"
                + "좌석보기",
            "Reservation{movieName='시빌 워-분열의 시대', startTime=2025-01-05T01:10, endTime=2025-01-05T04:00, location='CGV 광주금남로', id=''}"
        )
    );
  }
  private static Stream<Arguments> reservations(){
    return Stream.of(
        Arguments.of(
            List.of(
                "여름날의 레몬그라스\n"
                    + "2D\n"
                    + "오늘(화) 12:35~14:37\n"
                    + "CGV 용산아이파크몰\n"
                    + "준비중",
                "1승\n"
                    + "2D\n"
                    + "내일(수) 17:10~19:07\n"
                    + "CGV 용산아이파크몰\n"
                    + "준비중",
                "원정빌라\n"
                    + "2D\n"
                    + "2024.12.06(금) 14:40~16:17\n"
                    + "CGV 용산아이파크몰\n"
                    + "준비중\n",
                "원정빌라\n"
                    + "2D\n"
                    + "2024.12.06(금) 24:40~26:17\n"
                    + "CGV 용산아이파크몰\n"
                    + "준비중\n"
            ),
            LocalDate.of(2024,12,11),
            "[Reservation{movieName='여름날의 레몬그라스', startTime=2024-12-11T12:35, endTime=2024-12-11T14:37, location='CGV 용산아이파크몰', id=''}, Reservation{movieName='1승', startTime=2024-12-12T17:10, endTime=2024-12-12T19:07, location='CGV 용산아이파크몰', id=''}, Reservation{movieName='원정빌라', startTime=2024-12-06T14:40, endTime=2024-12-06T16:17, location='CGV 용산아이파크몰', id=''}, Reservation{movieName='원정빌라', startTime=2024-12-07T00:40, endTime=2024-12-07T02:17, location='CGV 용산아이파크몰', id=''}]"
        )
    );
  }
  @ParameterizedTest
  @MethodSource("reservation")
  void 하나의_영화표일경우_매칭된다(String title,String summary,String expect){
    Reservation reservation=new CGVReservation().makeReservation(title,summary,LocalDate.of(2024,12,11),"");
    Assertions.assertThat(reservation.toString()).isEqualTo(expect);
  }

  @ParameterizedTest
  @MethodSource("reservations")
  void 두개이상의_영화일경우_매칭된다(List<String> summary,LocalDate date,String expectString){
    List<Reservation> reservation=new CGVReservation().makeReservations(summary,date,"");
    Assertions.assertThat(reservation.toString()).isEqualTo(expectString);
  }
}