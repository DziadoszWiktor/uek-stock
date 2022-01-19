package pl.wiktordziadosz.uekstock.sales;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BasketStorage {
    Map<String, Basket> baskets;

    public BasketStorage() {
        this.baskets = new HashMap<>();
    }

    public Optional<Basket> getForCustomer(String customerId) {
        return Optional.ofNullable(baskets.get(customerId));
    }

    public void save(String customerId, Basket basket) {
        baskets.put(customerId, basket);
    }
}
