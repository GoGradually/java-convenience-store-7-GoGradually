package store.products;

public class OrderedProduct {
    private Product product;
    private int amount;
    private int price;
    private int promotionalAmount;

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
