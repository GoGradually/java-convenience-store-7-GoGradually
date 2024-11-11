package store.view;

import store.products.Product;
import store.products.ProductsDto;

import java.util.List;
import java.util.Map;

public class OutputView {

    public void printProductsList(Map<String, Product> products) {
        List<ProductsDto> productsDtos = products.values().stream().map(ProductsDto::new).toList();
    }
}
