package pl.wiktordziadosz.uekstock.sales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wiktordziadosz.uekstock.sales.offerting.OfferMaker;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CollectingProductsTest {

    private BasketStorage basketStorage;
    private DummyProductDetailsProvider productDetailsProvider;

    @BeforeEach
    void initializeSharedObjects() {
        basketStorage = new BasketStorage();
        productDetailsProvider = new DummyProductDetailsProvider();
    }

    @Test
    void itAllowsAddingProductsToBasket() {
        //arrange
        String productId = thereIsProduct("product-1");
        String customerId = thereIsCustomer("Kuba");
        SalesFacade sales = thereIsSalesModule();

        //Act
        sales.addToBasket(customerId, productId);

        //Assert
        thereIsXProductsInCustomersBasket(1, customerId);
    }

    @Test
    void itAllowsToAddMultipleProductsToBasket() {
        //arrange
        String productId1 = thereIsProduct("product-1");
        String productId2 = thereIsProduct("product-2");
        String customerId = thereIsCustomer("Kuba");
        SalesFacade sales = thereIsSalesModule();

        //Act
        sales.addToBasket(customerId, productId1);
        sales.addToBasket(customerId, productId2);

        //Assert
        thereIsXProductsInCustomersBasket(2, customerId);
    }

    @Test
    void itDoNotShareCustomersBaskets() {
        //arrange
        String productId1 = thereIsProduct("product-1");
        String customerId1 = thereIsCustomer("Kuba");
        String customerId2 = thereIsCustomer("Aga");
        SalesFacade sales = thereIsSalesModule();

        //Act
        sales.addToBasket(customerId1, productId1);
        sales.addToBasket(customerId2, productId1);

        //Assert
        thereIsXProductsInCustomersBasket(1, customerId1);
        thereIsXProductsInCustomersBasket(1, customerId2);
    }

    @Test
    void itAllowToAddSameProductMultpipleTime() {
        //arrange
        String productId = thereIsProduct("product-1");
        String customerId = thereIsCustomer("Kuba");
        SalesFacade sales = thereIsSalesModule();

        //Act
        sales.addToBasket(customerId, productId);
        sales.addToBasket(customerId, productId);
        sales.addToBasket(customerId, productId);

        //Assert
        thereIsXProductsInCustomersBasket(1, customerId);
        thereIsXQuantityOfProductXInCustomerBasket(3, productId, customerId);
    }

    private void thereIsXQuantityOfProductXInCustomerBasket(int i, String productId, String customerId) {

    }


    private SalesFacade thereIsSalesModule() {
        return new SalesFacade(
                basketStorage,
                productDetailsProvider,
                new OfferMaker(productDetailsProvider)
        );
    }

    private String thereIsCustomer(String customerName) {
        return customerName;
    }

    private String thereIsProduct(String productId) {
        ProductDetails product = new ProductDetails(productId, BigDecimal.TEN);
        productDetailsProvider.products.put(productId, product);
        return productId;
    }

    private void thereIsXProductsInCustomersBasket(int productsCount, String customerId) {
        Basket basket = loadBasketForCustomer(customerId);
        assertEquals(productsCount, basket.getProductsCount());
    }

    private Basket loadBasketForCustomer(String customerId) {
        return basketStorage.getForCustomer(customerId).get();
    }
}