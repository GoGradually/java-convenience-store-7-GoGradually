package store;

import store.conveniencestore.Cashier;
import store.conveniencestore.ConvenienceStore;
import store.products.Order;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        ConvenienceStore convenienceStore = new ConvenienceStore();
        convenienceStore.setUp();
        while (true) {
            Cashier cashier = new Cashier(convenienceStore);
            InputController inputController = new InputController(cashier);
            OutputView outputView = new OutputView(cashier);

            List<Order> orders = inputController.inputOrders();
            cashier.checkOrder(orders);
            inputController.receiveOrder(orders);

            outputView.printBill();
            if (!inputController.askUserTF("감사합니다. 구매하고 싶은 다른 상품이 있으신가요? (Y/N)")) {
                break;
            }
        }
    }
}
