package store.conveniencestore;

import store.products.Product;
import store.products.Products;
import store.promotion.NullPromotion;
import store.promotion.Promotion;
import store.promotion.PromotionImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvenienceStore {
    private static final String ERROR_MESSAGE = "[ERROR] ";
    private static final String PROMOTION_PATH = "src/main/resources/promotions.md";
    private static final String PRODUCTS_PATH = "src/main/resources/products.md";
    private final Map<String, Promotion> promotions = new HashMap<>();
    private final Map<String, Products> products = new HashMap<>();
    private final Map<String, Integer> priceTags = new HashMap<>();

    public void setUp() {
        enrollPromotions();
        enrollProducts();
    }

    private void enrollPromotions() {
        Path promotionPath = Path.of(PROMOTION_PATH);
        List<String> promotionInfos;
        try {
            promotionInfos = Files.readAllLines(promotionPath);
        } catch (IOException e) {
            throw new RuntimeException(ERROR_MESSAGE + "프로모션 정보를 읽어오는데 실패했습니다.", e);
        }
        enrollPromotion(promotionInfos);
        enrollNullPromotion();
    }

    private void enrollPromotion(List<String> promotionInfos) {
        for (int i = 1; i < promotionInfos.size(); i++) {
            String[] split = promotionInfos.get(i).split(",");
            Promotion promotion = new PromotionImpl(
                    split[0],
                    Integer.parseInt(split[1]),
                    Integer.parseInt(split[2]),
                    LocalDate.parse(split[3]),
                    LocalDate.parse(split[4]));
            promotions.put(split[0], promotion);
        }
    }

    private void enrollNullPromotion() {
        promotions.put("null", NullPromotion.getInstance());
    }

    private void enrollProducts() {
        Path productPath = Path.of(PRODUCTS_PATH);
        List<String> productsInfos;
        try {
            productsInfos = Files.readAllLines(productPath);
        } catch (IOException e) {
            throw new RuntimeException(ERROR_MESSAGE + "상품 정보를 읽어오는데 실패했습니다.");
        }
        enrollProduct(productsInfos);
        enrollEmptyProduct();
    }

    private void enrollProduct(List<String> productsInfos) {
        for (int i = 1; i < productsInfos.size(); i++) {
            String[] split = productsInfos.get(i).split(",");
            String productName = split[0];
            int price = Integer.parseInt(split[1]);
            int amount = Integer.parseInt(split[2]);
            Promotion promotion = promotions.get(split[3]);
            addProductInProducts(productName, price, amount, promotion);
            putPriceTag(productName, price);
        }
    }

    private void addProductInProducts(String productName, int price, int amount, Promotion promotion) {
        if (products.get(productName) == null) {
            products.put(productName, new Products(productName));
        }
        Products nowProducts = products.get(productName);
        Product newProduct = new Product(productName, amount, promotion);
        nowProducts.addProduct(newProduct);
    }

    private void putPriceTag(String productName, int price) {
        priceTags.put(productName, price);
    }

    private void enrollEmptyProduct() {
        for (Products nowProducts : products.values()) {
            if (nowProducts.notContainNullPromotion()) {
                nowProducts.addProduct(
                        new Product(nowProducts.getName(),
                                0,
                                NullPromotion.getInstance()
                        ));
            }
            ;
        }
    }
}
