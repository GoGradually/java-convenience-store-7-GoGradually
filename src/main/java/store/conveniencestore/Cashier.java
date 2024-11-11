package store.conveniencestore;

import store.products.*;
import store.promotion.Promotion;

import java.util.ArrayList;
import java.util.List;

public class Cashier {
    private static final String ERROR_MESSAGE = "[ERROR] ";
    private final ConvenienceStore convenienceStore;
    private boolean membership = false;
    private final List<OrderedProduct> orderedProducts = new ArrayList<>();

    public Cashier(ConvenienceStore convenienceStore) {
        this.convenienceStore = convenienceStore;
    }

    public void setMembership(boolean membership) {
        this.membership = membership;
    }

    public void checkOrder(List<Order> orders) {
        for (Order order : orders) {
            Product product = convenienceStore.getProduct(order.getProductName());
            if (product == null) {
                throw new IllegalArgumentException(ERROR_MESSAGE + "존재하지 않는 상품입니다. 다시 입력해 주세요.");
            }
            if (product.getAllAmount() < order.getOffer()) {
                throw new IllegalArgumentException(ERROR_MESSAGE +
                        "재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
            }
        }
    }

    public OrderingProduct registerOrder(Order order) {
        Product product = convenienceStore.getProduct(order.getProductName());
        Promotion promotion = product.getPromotion();
        return new OrderingProduct(product, order.getOffer());
    }

    public void addOrderedProducts(OrderingProduct orderingProduct) {
        if (orderingProduct.getOffer() == 0) {
            return;
        }
        orderedProducts.add(new OrderedProduct(
                orderingProduct,
                convenienceStore.getPrice(orderingProduct.getProduct().getName())
        ));
    }

    public BillResourceDto getBillResourceDto() {
        int fullPrice = getFullPrice();
        int promotionDiscount = getPromotionDiscount();
        int membershipDiscount = 0;
        if (membership) membershipDiscount = getMembershipDiscount();
        return new BillResourceDto(orderedProducts, fullPrice, promotionDiscount, membershipDiscount);
    }

    public void sell() {
        for (OrderedProduct orderedProduct : orderedProducts) {
            orderedProduct.getProduct().sell(orderedProduct.getAmount());
        }
    }

    private int getMembershipDiscount() {
        int membershipDiscount = 0;
        for (OrderedProduct orderedProduct : orderedProducts) {
            membershipDiscount += getGetMembershipDiscountValue(orderedProduct);
        }
        membershipDiscount /= 10;
        membershipDiscount *= 3;
        return membershipDiscount;
    }

    private int getGetMembershipDiscountValue(OrderedProduct orderedProduct) {
        Product product = orderedProduct.getProduct();
        int amount = orderedProduct.getAmount();
        int price = orderedProduct.getPrice();
        int promotionalAmount = orderedProduct.getPromotionalAmount();
        int nonPromotionalAmount = amount - promotionalAmount * (product.getPromotion().getSum());
        return price * nonPromotionalAmount;
    }

    private int getPromotionDiscount() {
        int promotionDiscount = 0;
        for (OrderedProduct orderedProduct : orderedProducts) {
            Product product = orderedProduct.getProduct();
            int price = convenienceStore.getPrice(product.getName());
            int promotionalCount = orderedProduct.getPromotionalAmount();
            promotionDiscount += price * promotionalCount;
        }
        return promotionDiscount;
    }

    private int getFullPrice() {
        int fullPrice = 0;
        for (OrderedProduct orderedProduct : orderedProducts) {
            Product product = orderedProduct.getProduct();
            int price = convenienceStore.getPrice(product.getName());
            fullPrice += price * orderedProduct.getAmount();
        }
        return fullPrice;
    }
}
