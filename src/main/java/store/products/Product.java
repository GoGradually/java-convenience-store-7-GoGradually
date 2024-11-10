package store.products;

import store.promotion.Promotion;

public class Product {
    private static final String ERROR_MESSAGE = "[ERROR] ";
    private String name;
    private int amount;
    private Promotion promotion;

    public Product(String name, int amount, Promotion promotion) {
        this.name = name;
        this.amount = amount;
        this.promotion = promotion;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public int sellProduct(int offer) {
        int actualCalculatedAmount = offer;
        if (actualCalculatedAmount > amount) {
            actualCalculatedAmount = amount;
        }
        amount -= actualCalculatedAmount;
        return actualCalculatedAmount;
    }
}
