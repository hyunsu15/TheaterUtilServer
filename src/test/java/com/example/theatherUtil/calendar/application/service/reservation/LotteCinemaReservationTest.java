package com.example.theatherUtil.calendar.application.service.reservation;

import com.example.theatherUtil.calendar.domain.Reservation;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LotteCinemaReservationTest {
  private static Stream<Arguments> reservation(){
    return Stream.of(
        Arguments.of(
            "예매번호 30149251\n"
                + "15세 관람가\n"
                + "상영일\n"
                + "01.05\n"
                + "(일)\n"
                + "상영시간\n"
                + "13:25 ~ 15:28\n"
                + "상영영화\n"
                + "하얼빈2D무대인사\n"
                + "상영관\n"
                + "건대입구 6관\n"
                + "좌석\n"
                + "M9\n"
                + "성인 1\n"
                + "주차권\n"
                + "상영시간 30분전부터 확인 가능합니다.\n",
            "Reservation{movieName='하얼빈2D무대인사', startTime=2025-01-05T13:25, endTime=2025-01-05T15:28, location='건대입구 6관', id=''}"
        ),
        Arguments.of(
            "예매번호 30162585\n"
                + "15세 관람가\n"
                + "상영일\n"
                + "TODAY\n"
                + "12.24\n"
                + "(화)\n"
                + "상영시간\n"
                + "23:40 ~ 25:43\n"
                + "상영영화\n"
                + "하얼빈\n"
                + "상영관\n"
                + "건대입구 샤롯데/샤롯데\n"
                + "좌석\n"
                + "A1\n"
                + "성인 1\n"
                + "주차권\n"
                + "상영시간 30분전부터 확인 가능합니다.",
            "Reservation{movieName='하얼빈', startTime=2024-12-24T23:40, endTime=2024-12-25T01:43, location='건대입구 샤롯데/샤롯데', id=''}"
        )
    );
  }
  private static Stream<Arguments> reservations(){
    return Stream.of(
        Arguments.of(
            List.of(
                "하얼빈\n"
                    + "건대입구 샤롯데 샤롯데 2층\n"
                    + "2024.12.24 (화) 23:40 ~ 25:43\n"
                    + "A1\n"
                    + "TODAY", "하얼빈\n"
                    + "건대입구 6관 2층\n"
                    + "2025.01.05 (일) 13:25 ~ 15:28\n"
                    + "M9",
                "하얼빈\n"
                    + "건대입구 2층\n"
                    + "2024.12.24 (화) 24:40 ~ 25:43\n"
                    + "A1\n"
                    + "TODAY", "하얼빈\n"
                    + "건대입구 6관 2층\n"
                    + "2025.01.05 (일) 13:25 ~ 15:28\n"
                    + "M9"
            ),
            "[Reservation{movieName='하얼빈', startTime=2024-12-24T23:40, endTime=2024-12-25T01:43, location='건대입구 샤롯데 샤롯데 2층', id=''}, Reservation{movieName='하얼빈', startTime=2025-01-05T13:25, endTime=2025-01-05T15:28, location='건대입구 6관 2층', id=''}, Reservation{movieName='하얼빈', startTime=2024-12-25T00:40, endTime=2024-12-25T01:43, location='건대입구 2층', id=''}, Reservation{movieName='하얼빈', startTime=2025-01-05T13:25, endTime=2025-01-05T15:28, location='건대입구 6관 2층', id=''}]"
        )
    );
  }
  @ParameterizedTest
  @MethodSource("reservation")
  void 하나의_영화표일경우_매칭된다(String summary,String expect){
    Reservation reservation= LotteCinemaReservation.makeReservation(summary,LocalDate.of(2024,12,24),"");
    Assertions.assertThat(reservation.toString()).isEqualTo(expect);
  }

  @ParameterizedTest
  @MethodSource("reservations")
  void 두개이상의_영화일경우_매칭된다(List<String> summary,String expectString){
    List<Reservation> reservation=new LotteCinemaReservation().makeReservations(summary,"");
    Assertions.assertThat(reservation.toString()).isEqualTo(expectString);
  }
}