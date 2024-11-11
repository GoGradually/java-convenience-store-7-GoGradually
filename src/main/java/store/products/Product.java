package store.products;

import store.promotion.Promotion;

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

    public void setNonPromotionalAmount(int nonPromotionalAmount) {
        this.nonPromotionalAmount = nonPromotionalAmount;
    }

    public void setPromotionalAmount(int promotionalAmount) {
        this.promotionalAmount = promotionalAmount;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
}
