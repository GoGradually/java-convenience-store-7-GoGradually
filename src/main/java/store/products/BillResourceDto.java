package store.products;

import java.util.List;
import java.util.Map;

public class BillResourceDto {
    private final List<OrderedProduct> orderedProducts;
    private final int fullPrice;
    private final int promotionDiscount;
    private final int membershipDiscount;

    public BillResourceDto(List<OrderedProduct> orderedProducts, int fullPrice, int promotionDiscount, int membershipDiscount) {
        this.orderedProducts = orderedProducts;
        this.fullPrice = fullPrice;
        this.promotionDiscount = promotionDiscount;
        this.membershipDiscount = membershipDiscount;
    }

    public void printBill() {
    }

}
