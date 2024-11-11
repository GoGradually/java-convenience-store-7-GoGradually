package store.products;

public class OrderedProducts {
    private Product product;
    private int amount;
    private boolean isPromoted;
    private int promotedAmount;

    public OrderedProducts(Product product, int amount, boolean isPromoted, int promotedAmount) {
        this.product = product;
        this.amount = amount;
        this.isPromoted = isPromoted;
        this.promotedAmount = promotedAmount;
    }
}
