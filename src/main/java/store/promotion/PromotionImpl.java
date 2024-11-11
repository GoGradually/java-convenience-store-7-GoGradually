package store.promotion;

import java.time.LocalDate;

public class PromotionImpl implements Promotion {
    private String promotionName;
    private int buy;
    private int get;
    private LocalDate startDate;
    private LocalDate endDate;

    public PromotionImpl(String promotionName, int buy, int get, LocalDate startDate, LocalDate endDate) {
        validatePromotion(promotionName, buy, get, startDate, endDate);
        this.promotionName = promotionName;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getBuy() {
        return buy;
    }

    public boolean isPromotable(LocalDate date) {
        return !date.isAfter(endDate) && !date.isBefore(startDate);
    }

    public int getAddableAmount(int offer) {
        return Math.max((offer + get) - (offer + get) % (buy + get) - offer, 0);
    }

    public int getFreeAmount(int offer) {
        return offer / (buy + get) * get;
    }

    @Override
    public boolean isNullPromotion() {
        return false;
    }

    private void validatePromotion(String promotionName, int buy, int get, LocalDate startDate, LocalDate endDate) {
        if (promotionName == null || promotionName.isEmpty() || promotionName.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 프로모션을 등록하는 도중 예외가 발생했습니다.");
        } else if (buy < 1 || buy > 10 || get != 1) {
            throw new IllegalArgumentException("[ERROR] 프로모션을 등록하는 도중 예외가 발생했습니다.");
        } else if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("[ERROR] 프로모션을 등록하는 도중 예외가 발생했습니다.");
        }
    }
}
