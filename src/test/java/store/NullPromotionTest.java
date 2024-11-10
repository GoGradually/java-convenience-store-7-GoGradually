package store;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NullPromotionTest {
    @Test
    void NullPromotion_객체_동작_테스트() {
        NullPromotion instance = NullPromotion.getInstance();
        assertThat(instance.isPromotable(LocalDate.of(2000, 1, 1))).isFalse();
        assertThat(instance.getBuy()).isEqualTo(10000);
        assertThat(instance.getAddableAmount(10000)).isZero();
        assertThat(instance.getAddableAmount(10000)).isZero();
    }
}