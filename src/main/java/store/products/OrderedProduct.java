package store.products;

public class OrderedProduct {
    private Product product;
    private int amount;
    private int promotionalAmount;

    public OrderedProduct(OrderingProduct orderingProduct) {
        this.product = orderingProduct.getProduct();
        this.amount = orderingProduct.getOffer();
        this.promotionalAmount = orderingProduct.getPromotionalAmount();
    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    public int getPromotionalAmount() {
        return promotionalAmount;
    }
}
