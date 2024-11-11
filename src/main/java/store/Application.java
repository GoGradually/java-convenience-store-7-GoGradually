package store;

import store.conveniencestore.Cashier;
import store.conveniencestore.ConvenienceStore;
import store.products.Order;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        ConvenienceStore convenienceStore = new ConvenienceStore();
        while (true) {
            ConvenienceStoreContext convenienceStoreContext = new ConvenienceStoreContext(convenienceStore);
            List<Order> orders = convenienceStoreContext.serve();
            convenienceStoreContext.sellProduct(orders);
            if (!convenienceStoreContext.askReuse()) {
                break;
            }
        }
    }
}
