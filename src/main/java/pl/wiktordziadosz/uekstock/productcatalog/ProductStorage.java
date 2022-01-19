package pl.wiktordziadosz.uekstock.productcatalog;

import java.util.HashMap;
import java.util.Optional;

public class ProductStorage {
    HashMap<String, Product> products;

    public ProductStorage() {
        this.products = new HashMap<>();
    }

    public void save(Product product) {
        products.put(product.getId(), product);
    }

    public Optional<Product> load(String productId) {
        return Optional.ofNullable(products.get(productId));
    }
}
