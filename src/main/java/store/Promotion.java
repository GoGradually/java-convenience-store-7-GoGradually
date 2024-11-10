package store;

import java.time.LocalDate;

public interface Promotion {

    int getBuy();

    boolean isPromotable(LocalDate date);

    int getAddableAmount(int offer);

    int getFreeAmount(int offer);
}
