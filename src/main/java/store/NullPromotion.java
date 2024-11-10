package store;

import java.time.LocalDate;

public class NullPromotion implements Promotion {

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
        return 10000;
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
