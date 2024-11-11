package store.products;

import java.util.ArrayList;
import java.util.List;

public class Products {
    private static final int OVER_BUY = 10000;
    private String name;
    private static final List<Product> products = new ArrayList<>();

    public Products(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addProduct(Product product) {
        products.add(product);
        products.sort((p, q) -> {
            if (p.getPromotion() == null) {
                return 1;
            } else if (q.getPromotion() == null) {
                return -1;
            }
            return p.getPromotion().getBuy() - q.getPromotion().getBuy();
        });
    }

    public boolean notContainNullPromotion() {
        return products.stream().noneMatch(p -> p.getPromotion().getBuy() == OVER_BUY);
    }
}
