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
        setDefaultPromotionalCount();
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

    public int getPromotionOffer() {
        return min(offer, product.getPromotionalAmount());
    }

    public int getUncontainableAmount() {
        return offer - promotionalAmount * (product.getPromotion().getBuy() + product.getPromotion().getAdd());
    }

    public void setAddable(boolean addable) {
        this.addable = addable;
    }

    public void setNonPromotional(boolean nonPromotional) {
        this.nonPromotional = nonPromotional;
    }

    public void setOffer(int offer) {
        this.offer = offer;
    }

    public void setPromotionalAmount(int promotionalAmount) {
        this.promotionalAmount = promotionalAmount;
    }

    public void addPromotionalCount() {
        Promotion promotion = product.getPromotion();

        if (product.getPromotionalAmount() > 0 && promotion.isPromotable(LocalDate.from(now()))) {

            int promotionOffer = getPromotionOffer();
            int freeAmount = promotion.getFreeAmount(promotionOffer);
            setPromotionalAmount(freeAmount);
        }

        if (promotionalAmount * (promotion.getBuy() + promotion.getAdd()) < offer) {
            setNonPromotional(true);
        }
    }

    public void excludeOverPromotion() {
        Product product = getProduct();
        Promotion promotion = product.getPromotion();
        setOffer(getPromotionalAmount() * (promotion.getBuy() + promotion.getAdd()));
    }

    private void setDefaultPromotionalCount() {
        Promotion promotion = product.getPromotion();
        if (product.getPromotionalAmount() > 0 && promotion.isPromotable(LocalDate.from(now()))) {
            return;
        }
        int promotionOffer = min(offer, product.getPromotionalAmount());
        setPromotionalAmount(promotionOffer);
    }


}
