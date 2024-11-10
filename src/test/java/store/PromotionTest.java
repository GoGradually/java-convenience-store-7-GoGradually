package store;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

class PromotionTest {
    private static final String ERROR_MESSAGE = "[ERROR]";
    @Test
    void 프로모션_객체_생성_성공(){
        String promotionName = "sample";
        int buy = 2;
        int get = 1;
        LocalDate startDate = LocalDate.of(2024,1,1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);

        Promotion promotion = new Promotion(promotionName, buy, get, startDate, endDate);
    }

    @Test
    void 프로모션_객체_생성_실패_비정상_날짜(){
        String promotionName = "sample";
        int buy = 2;
        int get = 1;
        LocalDate startDate = LocalDate.of(2024,1,1);
        LocalDate wrongEndDate = LocalDate.of(2023, 12, 31);

        assertThatIllegalArgumentException()
                .isThrownBy(() ->new Promotion(promotionName, buy, get, startDate, wrongEndDate))
                .withMessageStartingWith(ERROR_MESSAGE);
    }
    @ParameterizedTest
    @CsvSource(value = {"1,0", "0,1", "0,0", "11,11", "11,1", "1, 11"}, delimiter = ',')
    void 프로모션_객체_생성_실패_비정상_수량(int buy, int get){
        String promotionName = "sample";
        LocalDate startDate = LocalDate.of(2024,1,1);
        LocalDate wrongEndDate = LocalDate.of(2023, 12, 31);

        assertThatIllegalArgumentException()
                .isThrownBy(() ->new Promotion(promotionName, buy, get, startDate, wrongEndDate))
                .withMessageStartingWith(ERROR_MESSAGE);
    }
    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {" ", "  "})
    void 프로모션_객체_생성_실패_비정상_이름(String promotionName){
        int buy = 2;
        int get = 1;
        LocalDate startDate = LocalDate.of(2024,1,1);
        LocalDate wrongEndDate = LocalDate.of(2023, 12, 31);

        assertThatIllegalArgumentException()
                .isThrownBy(() ->new Promotion(promotionName, buy, get, startDate, wrongEndDate))
                .withMessageStartingWith(ERROR_MESSAGE);
    }
}