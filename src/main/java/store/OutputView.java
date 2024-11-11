package store;

import store.conveniencestore.Cashier;
import store.conveniencestore.ConvenienceStore;
import store.products.Product;
import store.products.BillResourceDto;

import java.util.List;
import java.util.Map;

public class OutputView {

    private final Cashier cashier;
    private final ConvenienceStore convenienceStore;

    public OutputView(Cashier cashier, ConvenienceStore convenienceStore) {
        this.cashier = cashier;
        this.convenienceStore = convenienceStore;
    }


    public void ListUpProducts() {
        System.out.println("안녕하세요. W편의점입니다.\n" + "현재 보유하고 있는 상품입니다.");
        List<Product> products = convenienceStore.getProducts();
        for (Product product : products) {
            printPromotionProduct(product);
            printNonPromotionProduct(product);
        }
        System.out.println();
    }

    private void printPromotionProduct(Product product) {
        if (product.getPromotion().isNullPromotion()) return;
        String stock = String.format("%,d", product.getPromotionalAmount()) + "개";
        if (stock.equals("0개")) stock = "재고 없음";
        System.out.printf("- %s %s원 %s %s\n",
                product.getName(),
                String.format("%,d", convenienceStore.getPrice(product.getName())),
                stock,
                product.getPromotion().getName());
    }

    private void printNonPromotionProduct(Product product) {
        String stock = String.format("%,d", product.getNonPromotionalAmount()) + "개";
        if (stock.equals("0개")) stock = "재고 없음";
        System.out.printf("- %s %s원 %s\n",
                product.getName(),
                String.format("%,d", convenienceStore.getPrice(product.getName())),
                stock);
    }

    public void printBill() {
        BillResourceDto billResourceDto = cashier.getBillResourceDto();
        billResourceDto.printBill();
        cashier.sell();
    }
}
