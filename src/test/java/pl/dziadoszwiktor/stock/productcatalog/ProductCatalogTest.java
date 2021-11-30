package pl.dziadoszwiktor.stock.productcatalog;

import org.junit.jupiter.api.Test;
import pl.dziadoszwiktor.stock.productcatalog.ProductCatalogFacade;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class ProductCatalogTest {
    @Test
    void itWorks() {
        assertEquals(1, 1);
    }

    @Test
    void itAllowsAddPicture() {
        ProductCatalogFacade api = thereIsProductCatalog();

        String id = api.addPicture(
                "Nice picture",
                BigDecimal.valueOf(10.10),
                Arrays.asList("#nice", "#picture"),
                "images/picture1.jpeg"
        );

        thereIsProductWithIdAvailable(api, id);
    }

    private ProductCatalogFacade thereIsProductCatalog() {
        return new ProductCatalogFacade();
    }

    private void thereIsProductWithIdAvailable(ProductCatalogFacade api, String id) {
        assertTrue(api.isProductExists(id));
    }
}
