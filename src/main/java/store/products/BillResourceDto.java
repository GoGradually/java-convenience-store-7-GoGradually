package store.products;

import java.util.List;

public class BillResourceDto {
    private final List<OrderedProduct> orderedProducts;
    private int fullAmount;
    private final int fullPrice;
    private final int promotionDiscount;
    private final int membershipDiscount;

    public BillResourceDto(List<OrderedProduct> orderedProducts,
                           int fullPrice,
                           int promotionDiscount,
                           int membershipDiscount) {
        this.orderedProducts = orderedProducts;
        for (OrderedProduct orderedProduct : orderedProducts) {
            fullAmount += orderedProduct.getAmount();
        }
        this.fullPrice = fullPrice;
        this.promotionDiscount = promotionDiscount;
        this.membershipDiscount = membershipDiscount;
    }

    public void printBill() {
        printFullPurchase();
        printPromotion();
        printFinalBill();
    }

    private void printFinalBill() {
        System.out.println("===================================");
        System.out.printf("총구매액             %s          %,d\n", fullAmount, fullPrice);
        System.out.printf("행사할인                         %,d\n", -promotionDiscount);
        System.out.printf("멤버십할인                        %,d\n", -membershipDiscount);
        System.out.printf("내실돈                          %,d\n", fullPrice - promotionDiscount - membershipDiscount);
    }

    private void printPromotion() {
        System.out.println("================증 정================");
        for (OrderedProduct orderedProduct : orderedProducts) {
            if (orderedProduct.getPromotionalAmount() == 0) continue;
            System.out.printf("%s               %,d\n", orderedProduct.getProduct().getName(), orderedProduct.getPromotionalAmount());
        }
    }

    private void printFullPurchase() {
        System.out.println("===============W 편의점===============");
        System.out.println("상품명              수량      금액");
        for (OrderedProduct orderedProduct : orderedProducts) {
            System.out.printf("%s               %,d     %,d\n", orderedProduct.getProduct().getName(), orderedProduct.getAmount(), orderedProduct.getPrice());
        }
    }


}
