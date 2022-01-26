package pl.wiktordziadosz.uekstock.sales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wiktordziadosz.uekstock.sales.offerting.Offer;
import pl.wiktordziadosz.uekstock.sales.offerting.OfferMaker;
import pl.wiktordziadosz.uekstock.sales.ordering.InMemoryReservationStorage;
import pl.wiktordziadosz.uekstock.sales.payment.DummyPaymentGateway;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.UUID;

public class MakingAnOfferTest {

    private BasketStorage basketStorage;
    private DummyProductDetailsProvider productDetailsProvider;

    @BeforeEach
    void initializeSharedObjects() {
        basketStorage = new BasketStorage();
        productDetailsProvider = new DummyProductDetailsProvider();
    }

    @Test
    void itGenerateOfferBasedOnProductsWithinBasket() {
        //Arrange
        String productId = thereIsProduct("product-1", BigDecimal.valueOf(10.10));
        String customerId = thereIsCustomer();
        SalesFacade sales = thereIsSalesModule();

        //Act
        sales.addToBasket(customerId, productId);
        sales.addToBasket(customerId, productId);
        Offer currentOffer = sales.getCurrentOffer(customerId);

        //Assert
        assertEquals(BigDecimal.valueOf(20.20), currentOffer.getTotal());
        assertEquals(2, currentOffer.getLinesCount());
    }

    private String thereIsProduct(String productId, BigDecimal price) {
        ProductDetails product = new ProductDetails(productId, price);
        productDetailsProvider.products.put(productId, product);
        return productId;
    }

    private String thereIsCustomer() {
        return UUID.randomUUID().toString();
    }

    private SalesFacade thereIsSalesModule() {
        return new SalesFacade(
                basketStorage,
                productDetailsProvider,
                new OfferMaker(productDetailsProvider),
                new InMemoryReservationStorage(), new DummyPaymentGateway());
    }
}
