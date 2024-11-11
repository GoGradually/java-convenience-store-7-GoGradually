package store;

import store.conveniencestore.Cashier;
import store.products.Order;
import store.products.OrderingProduct;
import store.products.Product;

import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class InputController {
    private final Cashier cashier;

    public InputController(Cashier cashier) {
        this.cashier = cashier;
    }

    public boolean askUserTF(String message) {
        while (true) {
            System.out.println(message);
            String result = readLine();
            if (result.equals("Y")) {
                return true;
            } else if (result.equals("N")) {
                return false;
            }
            System.out.println("[ERROR] 잘못된 입력입니다. 다시 입력해주세요.");
        }
    }


    public void askPromotionAddable(OrderingProduct orderingProduct) {
        Product product = orderingProduct.getProduct();
        String format = String.format("현재 %s은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)", product.getName());

        if (askUserTF(format)) {
            orderingProduct.addPromotionalCount();
        }
    }

    public void askStillPurchase(OrderingProduct orderingProduct) {
        if (!orderingProduct.haveNonPromotional()) {
            return;
        }
        Product product = orderingProduct.getProduct();
        int uncontainableAmount = orderingProduct.getUncontainableAmount();
        String format = String.format("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)", product.getName(), uncontainableAmount);
        if (askUserTF(format)) {
            orderingProduct.excludeOverPromotion();
        }
    }

    // todo
    public List<Order> inputOrders() {
        return null;
    }

    //todo
    public void receiveOrder(List<Order> orders) {
    }
}
