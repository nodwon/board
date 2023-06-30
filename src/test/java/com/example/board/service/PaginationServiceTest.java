package com.example.board.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.List;
import java.util.stream.Stream;

@DisplayName("비지니스로직 - 페이지 네이션")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = PaginationService.class)
// none을 webE 무게를 줄일수있음 root부분을 임이로 정해주거나 위치를 정해줄수 있다.
class PaginationServiceTest {

    private final PaginationService sut;

    PaginationServiceTest(@Autowired PaginationService paginationService) {
        this.sut = paginationService;
    }
    @DisplayName("현재 페이지 번호와 총 페이지 수를 주면 , 페이징 바리스트를 만들어준다.")
    @MethodSource
    @ParameterizedTest(name = "[{index}] {0}, {1} => {2}") // 값을 연속적으로 주입해서 동일한 대상 메소드를 여러번 테스트 하면서 입력값을 볼슈있다.
    public void Pageging(int currentNumber, int totalPages, List<Integer> expected) throws Exception {
        //given

        //when
        List<Integer> actual = sut.getPaginationBarNumbers(currentNumber, totalPages);
        //then
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> Pageging(){
        return Stream.of(
                arguments(1, 13, List.of(0,1,2,3,4)),
                arguments(2, 13, List.of(0,1,2,3,4)),
                arguments(3, 13, List.of(1,2,3,4,5)),
                arguments(4, 13, List.of(2,3,4,5,6)),
                arguments(5, 13, List.of(3,4,5,6,7)),
                arguments(6, 13, List.of(4,5,6,7,8)),
                arguments(10, 13, List.of(8,9,10,11,12)),
                arguments(11, 13, List.of(9,10,11,12)),
                arguments(12, 13, List.of(10,11,12))
        );
    }

    @DisplayName("현재 설정되어 있는 페이지네이션 바의 길이를 알려준다.")
    @Test
    void givenNothing_whenCalling_thenReturnsCurrentBarLength() {
        // Given

        // When
        int barLength = sut.currentBarLength();

        // Then
        assertThat(barLength).isEqualTo(5);
    }


}