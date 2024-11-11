package store.products;

import store.promotion.Promotion;

import java.time.LocalDate;

import static camp.nextstep.edu.missionutils.DateTimes.now;
import static java.lang.Math.min;

public class OrderingProduct {
    private final Product product;
    private int offer;
    private int promotionalAmount;
    private boolean addable = false;
    private boolean nonPromotional = false;

    public OrderingProduct(Product product, int offer) {
        this.product = product;
        this.offer = offer;
        if (product.getPromotion().isNullPromotion()) return;
        setDefaultPromotionalCount();
        setPromotionalPossibility();
    }

    public Product getProduct() {
        return product;
    }

    public int getOffer() {
        return offer;
    }

    public int getPromotionalAmount() {
        return promotionalAmount;
    }

    public boolean isAddable() {
        return addable;
    }

    public boolean haveNonPromotional() {
        return nonPromotional;
    }

    public int getUncontainableAmount() {
        return offer - promotionalAmount * product.getPromotion().getSum();
    }

    public void addPromotionalCount() {
        Promotion promotion = product.getPromotion();
        if (product.getPromotionalAmount() > 0 && promotion.isPromotable(LocalDate.from(now()))) {
            int promotionOffer = getPromotionOffer();
            int freeAmount = promotion.getFreeAmount(promotionOffer);
            setPromotionalAmount(freeAmount);
        }

        if (promotionalAmount * promotion.getSum() < offer) {
            setNonPromotional(true);
        }
    }

    public void excludeOverPromotion() {
        Product product = getProduct();
        Promotion promotion = product.getPromotion();
        setOffer(getPromotionalAmount() * promotion.getSum());
    }

    private int getPromotionOffer() {
        return min(offer, product.getPromotionalAmount());
    }

    private void setOffer(int offer) {
        this.offer = offer;
    }

    private void setAddable(boolean addable) {
        this.addable = addable;
    }

    private void setNonPromotional(boolean nonPromotional) {
        this.nonPromotional = nonPromotional;
    }

    private void setPromotionalAmount(int promotionalAmount) {
        this.promotionalAmount = promotionalAmount;
    }

    private void setDefaultPromotionalCount() {
        Promotion promotion = product.getPromotion();
        if (!(product.getPromotionalAmount() > 0 && promotion.isPromotable(LocalDate.from(now())))) {
            return;
        }
        int promotionOffer = min(offer, product.getPromotionalAmount());
        setPromotionalAmount(promotion.getFreeAmount(promotionOffer));
    }

    private void setPromotionalPossibility() {
        Promotion promotion = product.getPromotion();
        if (product.getPromotionalAmount() > 0 && promotion.isPromotable(LocalDate.from(now()))) {
            int nowPromotionalAmount = getPromotionalAmount();
            int addableAmount = promotion.getAddableAmount(nowPromotionalAmount);
            if (addableAmount > 0 && nowPromotionalAmount + addableAmount <= product.getPromotionalAmount()) {
                setAddable(true);
            } else if (getPromotionalAmount() * promotion.getSum() < offer) {
                setNonPromotional(true);
            }
        }
    }
}
