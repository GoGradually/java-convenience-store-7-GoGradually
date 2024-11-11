package store.products;

public class Order {
    private String productName;
    private int offer;

    public Order(String productName, int offer) {
        this.productName = productName;
        this.offer = offer;
    }

    public String getProductName() {
        return productName;
    }

    public int getOffer() {
        return offer;
    }
}
