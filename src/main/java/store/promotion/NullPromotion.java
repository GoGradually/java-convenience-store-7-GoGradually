package store.promotion;

import java.time.LocalDate;

public class NullPromotion implements Promotion {
    private static final int OVER_BUY = 10000;
    private static NullPromotion INSTANCE = new NullPromotion();

    public static NullPromotion getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isPromotable(LocalDate date) {
        return false;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public int getBuy() {
        return OVER_BUY;
    }

    @Override
    public int getAddableAmount(int offer) {
        return 0;
    }

    @Override
    public int getFreeAmount(int offer) {
        return 0;
    }

    @Override
    public boolean isNullPromotion() {
        return true;
    }

    @Override
    public int getSum() {
        return OVER_BUY;
    }
}
