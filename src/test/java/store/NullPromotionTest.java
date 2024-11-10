package store;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NullPromotionTest {
    private static final int OVER_BUY = 10000;
    @Test
    void NullPromotion_객체_동작_테스트() {
        NullPromotion instance = NullPromotion.getInstance();
        assertThat(instance.isPromotable(LocalDate.of(2000, 1, 1))).isFalse();
        assertThat(instance.getBuy()).isEqualTo(OVER_BUY);
        assertThat(instance.getAddableAmount(OVER_BUY)).isZero();
        assertThat(instance.getAddableAmount(OVER_BUY)).isZero();
    }
}