package store.promotion;

import java.time.LocalDate;

public interface Promotion {
    String getName();

    int getBuy();

    boolean isPromotable(LocalDate date);

    int getAddableAmount(int offer);

    int getFreeAmount(int offer);

    boolean isNullPromotion();

    int getSum();
}
