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
            OutputView outputView = new OutputView(cashier, convenienceStore);
            outputView.ListUpProducts();
            List<Order> orders = inputController.inputOrders();
            cashier.checkOrder(orders);
            inputController.receiveOrder(orders);
            inputController.askMembership();
            outputView.printBill();
            if (!inputController.askReuse()) {
                break;
            }
        }
    }
}
