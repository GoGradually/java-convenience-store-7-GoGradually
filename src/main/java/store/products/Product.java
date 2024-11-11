package store.products;

import store.promotion.Promotion;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Product {
    private String name;
    private int nonPromotionalAmount;
    private int promotionalAmount;

    private Promotion promotion;

    public Product(String name, Promotion promotion) {
        this.name = name;
        this.promotion = promotion;
    }

    public int getNonPromotionalAmount() {
        return nonPromotionalAmount;
    }

    public int getPromotionalAmount() {
        return promotionalAmount;
    }

    public int getAllAmount() {
        return nonPromotionalAmount + promotionalAmount;
    }

    public String getName() {
        return name;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setNonPromotionalAmount(int nonPromotionalAmount) {
        this.nonPromotionalAmount = nonPromotionalAmount;
    }

    public void setPromotionalAmount(int promotionalAmount) {
        this.promotionalAmount = promotionalAmount;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public void sell(int offer) {
        int promotionalOffer = min(promotionalAmount, offer);
        int nonPromotionalOffer = offer - promotionalOffer;
        promotionalAmount -= promotionalOffer;
        nonPromotionalAmount -= nonPromotionalOffer;
    }
}
