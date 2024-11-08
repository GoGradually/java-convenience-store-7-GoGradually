package store;

import java.time.LocalDate;

public class Promotion {
    private String promotionName;
    private int buy;
    private int get;
    private LocalDate startDate;
    private LocalDate endDate;

    public Promotion(String promotionName, int buy, int get, LocalDate startDate, LocalDate endDate) {
        this.promotionName = promotionName;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
