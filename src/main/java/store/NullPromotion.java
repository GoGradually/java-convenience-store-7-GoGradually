package store;

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
}
