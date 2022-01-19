package pl.wiktordziadosz.uekstock.productcatalog;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProductCatalog {

    private ProductRepository repository;

    public ProductCatalog(ProductRepository repository) {

        this.repository = repository;
    }

    public String addProduct(String title, BigDecimal price, List<String> keywords, String mediaPath) {
        Product product = new Product(UUID.randomUUID(), title, price, keywords, mediaPath);
        repository.save(product);
        return product.getId();
    }

    public boolean isProductExists(String productId) {
        return repository.existsById(productId);
    }

    public void publish(String id) {
        Product product = repository.findById(id).get();
        product.publish();
        repository.save(product);
    }

    public List<Product> allProducts() {
        return repository.findAll()
                .stream()
                .filter(Product::isPublished)
                .filter(p -> p.isPublished())
                .collect(Collectors.toList());
    }

    public void empty() {
        repository.deleteAll();
    }

    public Product getById(String productId) {
        Product product = repository.findById(productId).get();
        return product;
    }
}
