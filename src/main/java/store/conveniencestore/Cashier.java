package store.conveniencestore;

import store.products.*;
import store.promotion.Promotion;
import store.InputController;
import store.OutputView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static camp.nextstep.edu.missionutils.DateTimes.now;
import static java.lang.Math.min;

public class Cashier {
    private static final String ERROR_MESSAGE = "[ERROR] ";
    private final ConvenienceStore convenienceStore;

    private final List<OrderedProduct> orderedProducts = new ArrayList<>();

    public Cashier(ConvenienceStore convenienceStore) {
        this.convenienceStore = convenienceStore;
    }

    public void checkOrder(List<Order> orders) {
        for (Order order : orders) {
            Product product = convenienceStore.getProduct(order.getProductName());
            if (product.getAllAmount() < order.getOffer()) {
                throw new IllegalArgumentException(
                        ERROR_MESSAGE + "재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
            }
        }
    }
    public OrderingProduct registerOrder(Order order) {
        Product product = convenienceStore.getProduct(order.getProductName());
        Promotion promotion = product.getPromotion();
        OrderingProduct orderingProduct = new OrderingProduct(product, order.getOffer());
        if (product.getPromotionalAmount() > 0 && promotion.isPromotable(LocalDate.from(now()))) {
            int nowPromotionalAmount = orderingProduct.getPromotionalAmount();
            int addableAmount = promotion.getAddableAmount(nowPromotionalAmount);
            if (addableAmount > 0 && nowPromotionalAmount + addableAmount <= product.getPromotionalAmount()) {
                orderingProduct.setAddable(true);
            }
            else if (orderingProduct.getPromotionalAmount() * (promotion.getBuy() + promotion.getAdd()) < order.getOffer()) {
                orderingProduct.setNonPromotional(true);
            }
        }
        return orderingProduct;
    }

    public void addOrderedProducts(OrderingProduct orderingProduct){
        if (orderingProduct.getOffer() == 0) {
            return;
        }
        orderedProducts.add(new OrderedProduct(orderingProduct));
    }

    public BillResourceDto getBillResourceDto() {
        int fullPrice = getFullPrice();
        int promotionDiscount = getPromotionDiscount();
        int membershipDiscount = getMembershipDiscount();
        return new BillResourceDto(
                orderedProducts,
                fullPrice,
                promotionDiscount,
                membershipDiscount
        );
    }

    public void sell() {
        for (OrderedProduct orderedProduct : orderedProducts) {
            orderedProduct.getProduct().sell(orderedProduct.getAmount());
        }
    }

    private int getMembershipDiscount() {
        int membershipDiscount = 0;
        for (OrderedProduct orderedProduct : orderedProducts) {
            Product product = orderedProduct.getProduct();
            int amount = orderedProduct.getAmount();
            int price = convenienceStore.getPrice(product.getName());
            int promotionalAmount = orderedProduct.getPromotionalAmount();
            int nonPromotionalAmount = amount - promotionalAmount *
                    (product.getPromotion().getAdd() + product.getPromotion().getBuy());
            membershipDiscount += price * nonPromotionalAmount;
        }
        membershipDiscount /= 10;
        membershipDiscount *= 3;
        return membershipDiscount;
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
