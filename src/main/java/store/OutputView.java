package store;

import java.util.List;
import java.util.Map;

public class OutputView {

    public void printProductsList(Map<String, Products> products) {
        List<ProductsDto> productsDtos = products.values().stream().map(ProductsDto::new).toList();
    }
}
