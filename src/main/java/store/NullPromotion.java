package store;

import java.time.LocalDate;

public class NullPromotion extends Promotion {
    private static NullPromotion INSTANCE = new NullPromotion(
            "null",
            10000,
            0,
            LocalDate.of(2000,1,1),
            LocalDate.of(2000,1,1)
    );

    private NullPromotion(String promotionName, int buy, int get, LocalDate startDate, LocalDate endDate) {
        super(promotionName, buy, get, startDate, endDate);
    }

    public static NullPromotion getInstance(){
        return INSTANCE;
    }

    @Override
    public int getBuy(){
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
