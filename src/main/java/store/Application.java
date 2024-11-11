package store;

import store.conveniencestore.Cashier;
import store.conveniencestore.ConvenienceStore;
import store.products.Order;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        ConvenienceStore convenienceStore = new ConvenienceStore();
        convenienceStore.setUp();
        while (true) {
            Cashier cashier = new Cashier(convenienceStore);
            InputController inputController = new InputController(cashier);
            OutputView outputView = new OutputView(cashier, convenienceStore);
            outputView.ListUpProducts();
            List<Order> orders;
            while (true) {
                try {
                    orders = inputController.inputOrders();
                    cashier.checkOrder(orders);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            inputController.receiveOrder(orders);
            inputController.askMembership();
            outputView.printBill();
            if (!inputController.askReuse()) {
                break;
            }
        }
    }
}
