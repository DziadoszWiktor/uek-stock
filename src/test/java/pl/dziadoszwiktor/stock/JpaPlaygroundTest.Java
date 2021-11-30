package pl.dziadoszwiktor.stock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.dziadoszwiktor.stock.productcatalog.Image;
import pl.dziadoszwiktor.stock.productcatalog.ProductRepository;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

@SpringBootTest
public class JpaPlaygroundTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    void itStoreProduct() {
        UUID id = UUID.randomUUID();
        Image product = new Image(
                id,
                "My prod",
                BigDecimal.ONE,
                Arrays.asList("k1", "k2"),
                "media.jpeg");
        productRepository.save(product);

        Image loaded = productRepository.findById(id.toString()).get();
    }
}