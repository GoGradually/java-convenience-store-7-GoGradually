package store.conveniencestore;

import store.products.OrderedProducts;
import store.view.InputView;
import store.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class Cashier {
    private final ConvenienceStore convenienceStore;
    private final InputView inputView;
    private final OutputView outputView;

    private List<OrderedProducts> nonPromotionalProducts = new ArrayList<>();
    private List<OrderedProducts> promotionalProducts = new ArrayList<>();

    public Cashier(ConvenienceStore convenienceStore, InputView inputView, OutputView outputView) {
        this.convenienceStore = convenienceStore;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    // Todo
    public void receiveOrder(String productsName, int offer){
    }

}
