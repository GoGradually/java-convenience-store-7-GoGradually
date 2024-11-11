package store;

import store.conveniencestore.Cashier;
import store.conveniencestore.ConvenienceStore;
import store.products.Order;

import java.util.List;

public class ConvenienceStoreContext {
    private final ConvenienceStore convenienceStore;
    private final InputController inputController;
    private final OutputView outputView;
    private final Cashier cashier;

    public ConvenienceStoreContext(ConvenienceStore convenienceStore) {
        this.convenienceStore = convenienceStore;
        cashier = new Cashier(convenienceStore);
        inputController = new InputController(cashier);
        outputView = new OutputView(cashier, convenienceStore);
    }
    public List<Order> serve(){
        outputView.ListUpProducts();
        while (true) {
            try {
                List<Order> orders = inputController.inputOrders();
                cashier.checkOrder(orders);
                return orders;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void sellProduct(List<Order> orders){
        inputController.receiveOrder(orders);
        inputController.askMembership();
        outputView.printBill();
    }

    public boolean askReuse() {
        return inputController.askReuse();
    }
}
