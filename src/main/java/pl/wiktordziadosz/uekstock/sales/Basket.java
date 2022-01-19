package pl.wiktordziadosz.uekstock.sales;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    List<BasketItem> items;

    public Basket() {
        this.items = new ArrayList<>();
    }

    public static Basket empty() {
        return new Basket();
    }

    public int getProductsCount() {
        return items.size();
    }

    public void add(BasketItem basketItem) {
        items.add(basketItem);
    }

    public List<BasketItem> getBasketItems() {
        return items;
    }
}
