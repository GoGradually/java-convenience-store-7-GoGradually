package store.conveniencestore;

import store.products.Product;
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
    private final Map<String, Product> products = new HashMap<>();
    private final Map<String, Integer> priceTags = new HashMap<>();

    public void setUp() {
        enrollPromotions();
        enrollProducts();
    }

    public Product getProduct(String productsName) {
        return products.get(productsName);
    }

    public int getPrice(String productsName){
        return priceTags.get(productsName);
    }

    public List<Product> getProducts(){
        return products.values().stream().toList();
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
    }

    private void enrollProduct(List<String> productsInfos) {
        for (int i = 1; i < productsInfos.size(); i++) {
            String[] split = productsInfos.get(i).split(",");
            String productName = split[0];
            int price = Integer.parseInt(split[1]);
            int amount = Integer.parseInt(split[2]);
            Promotion promotion = promotions.get(split[3]);
            addProductInProducts(productName, amount, promotion);
            putPriceTag(productName, price);
        }
    }

    private void addProductInProducts(String productName, int amount, Promotion promotion) {
        products.computeIfAbsent(productName, n -> new Product(n, NullPromotion.getInstance()));
        Product nowProduct = products.get(productName);
        if(promotion.isNullPromotion()){
            nowProduct.setNonPromotionalAmount(amount);
            return;
        }
        nowProduct.setPromotion(promotion);
        nowProduct.setPromotionalAmount(amount);
    }

    private void putPriceTag(String productName, int price) {
        priceTags.put(productName, price);
    }
}
