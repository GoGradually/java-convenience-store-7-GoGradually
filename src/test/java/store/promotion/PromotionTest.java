package store.promotion;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import store.promotion.Promotion;
import store.promotion.PromotionImpl;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class PromotionTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 프로모션_객체_생성_성공() {
        String promotionName = "sample";
        int buy = 2;
        int get = 1;
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);

        Promotion promotion = new PromotionImpl(promotionName, buy, get, startDate, endDate);
    }

    @Test
    void 프로모션_객체_생성_실패_비정상_날짜() {
        String promotionName = "sample";
        int buy = 2;
        int get = 1;
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate wrongEndDate = LocalDate.of(2023, 12, 31);

        assertThatRuntimeException().isThrownBy(() -> new PromotionImpl(promotionName, buy, get, startDate, wrongEndDate)).withMessageStartingWith(ERROR_MESSAGE);
    }

    @ParameterizedTest
    @CsvSource(value = {"-1,1", "1,0", "0,1", "0,0", "11,11", "11,1", "1, 11"}, delimiter = ',')
    void 프로모션_객체_생성_실패_비정상_수량(int buy, int get) {
        String promotionName = "sample";
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate wrongEndDate = LocalDate.of(2023, 12, 31);

        assertThatRuntimeException().isThrownBy(() -> new PromotionImpl(promotionName, buy, get, startDate, wrongEndDate)).withMessageStartingWith(ERROR_MESSAGE);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {" ", "  "})
    void 프로모션_객체_생성_실패_비정상_이름(String promotionName) {
        int buy = 2;
        int get = 1;
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate wrongEndDate = LocalDate.of(2023, 12, 31);

        assertThatRuntimeException().isThrownBy(() -> new PromotionImpl(promotionName, buy, get, startDate, wrongEndDate)).withMessageStartingWith(ERROR_MESSAGE);
    }

    @ParameterizedTest
    @CsvSource({"2023,12,31", "2025,1,1"})
    void 프로모션_적용_불가능(int year, int month, int day) {
        String promotionName = "sample";
        int buy = 2;
        int get = 1;
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        Promotion promotion = new PromotionImpl(promotionName, buy, get, startDate, endDate);

        LocalDate testDate = LocalDate.of(year, month, day);

        assertThat(promotion.isPromotable(testDate)).isFalse();
    }

    @ParameterizedTest
    @CsvSource({"2024,1,31", "2024,12,1"})
    void 프로모션_적용_가능(int year, int month, int day) {
        String promotionName = "sample";
        int buy = 2;
        int get = 1;
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        Promotion promotion = new PromotionImpl(promotionName, buy, get, startDate, endDate);

        LocalDate testDate = LocalDate.of(year, month, day);

        assertThat(promotion.isPromotable(testDate)).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"1,1,1,1", "1,1,2,0", "2,1,1,0", "2,1,2,1", "2,1,3,0", "3,1,1,0", "3,1,2,0", "3,1,3,1", "3,1,4,0"})
    void 추가구매_가능_수량_계산(int buy, int get, int offer, int addable) {
        String promotionName = "sample";
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        Promotion promotion = new PromotionImpl(promotionName, buy, get, startDate, endDate);

        assertThat(promotion.getAddableAmount(offer)).isEqualTo(addable);
    }

    @ParameterizedTest
    @CsvSource({"1,0", "2,0", "3,1"})
    void 무료_수량_계산(int offer, int free) {
        String promotionName = "sample";
        int buy = 2;
        int get = 1;
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        Promotion promotion = new PromotionImpl(promotionName, buy, get, startDate, endDate);

        assertThat(promotion.getFreeAmount(offer)).isEqualTo(free);
    }
}