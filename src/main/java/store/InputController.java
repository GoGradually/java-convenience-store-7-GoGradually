package store;

import store.conveniencestore.Cashier;
import store.products.Order;
import store.products.OrderingProduct;
import store.products.Product;

import java.util.ArrayList;
import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class InputController {
    private static final String ERROR_MESSAGE = "[ERROR] ";
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
            System.out.println(ERROR_MESSAGE + "잘못된 입력입니다. 다시 입력해주세요.");
        }
    }

    public List<Order> inputOrders() {
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        String input = readLine();
        List<Order> orders = new ArrayList<>();
        for (String s : input.split(",")) {
            validateOrderInput(s);
            s = peelBlank(s);
            String[] split = s.split("-");
            addOrder(orders, split);
        }
        return orders;
    }

    private String peelBlank(String s) {
        s = s.substring(1, s.length() - 1);
        return s;
    }

    public void receiveOrder(List<Order> orders) {
        cashier.checkOrder(orders);
        for (Order order : orders) {
            OrderingProduct orderingProduct = cashier.registerOrder(order);
            askPromotionAddable(orderingProduct);
            askStillPurchase(orderingProduct);
            cashier.addOrderedProducts(orderingProduct);
        }
    }

    public void askMembership() {
        if (askUserTF("멤버십 할인을 받으시겠습니까? (Y/N)")) {
            cashier.setMembership(true);
        }
    }

    public boolean askReuse() {
        return askUserTF("감사합니다. 구매하고 싶은 다른 상품이 있으신가요? (Y/N)");
    }

    private void askPromotionAddable(OrderingProduct orderingProduct) {
        if (!orderingProduct.isAddable()) return;
        Product product = orderingProduct.getProduct();
        String format = String.format(
                "현재 %s은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)",
                product.getName());
        if (askUserTF(format)) {
            orderingProduct.addPromotionalCount();
        }
    }

    private void askStillPurchase(OrderingProduct orderingProduct) {
        if (!orderingProduct.haveNonPromotional()) return;

        Product product = orderingProduct.getProduct();
        int uncontainableAmount = orderingProduct.getUncontainableAmount();

        String format = String.format("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)",
                product.getName(), uncontainableAmount);
        if (askUserTF(format)) {
            orderingProduct.excludeOverPromotion();
        }
    }

    private void addOrder(List<Order> orders, String[] split) {
        try {
            orders.add(new Order(split[0], Integer.parseInt(split[1])));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_MESSAGE + "올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
        }
    }

    private void validateOrderInput(String s) {
        if (s.isBlank()) {
            throw new IllegalArgumentException(ERROR_MESSAGE + "올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
        }
        if (!s.startsWith("[") || !s.endsWith("]"))
            throw new IllegalArgumentException(ERROR_MESSAGE + "올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
    }
}
