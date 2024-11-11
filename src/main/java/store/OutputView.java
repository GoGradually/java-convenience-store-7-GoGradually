package store;

import store.conveniencestore.Cashier;
import store.products.Product;
import store.products.BillResourceDto;

import java.util.List;
import java.util.Map;

public class OutputView {

    private final Cashier cashier;

    public OutputView(Cashier cashier) {
        this.cashier = cashier;
    }

    //todo
    public void printBill() {
        BillResourceDto billResourceDto = cashier.getBillResourceDto();
        billResourceDto.printBill();
        cashier.sell();
    }
}
