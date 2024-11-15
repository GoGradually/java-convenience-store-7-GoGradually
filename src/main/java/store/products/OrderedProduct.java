package store.products;

public class OrderedProduct {
    private final Product product;
    private final int amount;
    private final int price;
    private final int promotionalAmount;

    public OrderedProduct(OrderingProduct orderingProduct, int price) {
        this.product = orderingProduct.getProduct();
        this.amount = orderingProduct.getOffer();
        this.promotionalAmount = orderingProduct.getPromotionalAmount();
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

    public int getPromotionalAmount() {
        return promotionalAmount;
    }
}
