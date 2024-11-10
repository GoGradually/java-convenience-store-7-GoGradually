package store;

import java.time.LocalDate;

public class Promotion {
    private String promotionName;
    private int buy;
    private int get;
    private LocalDate startDate;
    private LocalDate endDate;

    public Promotion(String promotionName, int buy, int get, LocalDate startDate, LocalDate endDate) {
        validatePromotion(promotionName, buy, get, startDate, endDate);
        this.promotionName = promotionName;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isPromotable(LocalDate date) {
        return !date.isAfter(endDate) && !date.isBefore(startDate);
    }

    public int getAddableAmount(int offer) {
        return Math.max((offer + get) / (buy + get) * (buy + get) - offer, 0);
    }

    public int getFreeAmount(int offer) {
        return offer / (buy + get) * get;
    }

    private void validatePromotion(String promotionName, int buy, int get, LocalDate startDate, LocalDate endDate) {
        if (promotionName == null || promotionName.isEmpty() || promotionName.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 프로모션을 등록하는 도중 예외가 발생했습니다. 재고를 다시 확인해주세요.");
        } else if (buy == 0 || get == 0 || buy > 10 || get > 10) {
            throw new IllegalArgumentException("[ERROR] 프로모션을 등록하는 도중 예외가 발생했습니다. 재고를 다시 확인해주세요.");
        } else if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("[ERROR] 프로모션을 등록하는 도중 예외가 발생했습니다. 재고를 다시 확인해주세요.");
        }
    }
}
