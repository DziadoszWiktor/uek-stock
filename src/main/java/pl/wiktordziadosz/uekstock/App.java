package pl.wiktordziadosz.uekstock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.wiktordziadosz.uekstock.payment.PayU;
import pl.wiktordziadosz.uekstock.productcatalog.Product;
import pl.wiktordziadosz.uekstock.productcatalog.ProductCatalog;
import pl.wiktordziadosz.uekstock.productcatalog.ProductRepository;
import pl.wiktordziadosz.uekstock.sales.*;
import pl.wiktordziadosz.uekstock.sales.SalesFacade;
import pl.wiktordziadosz.uekstock.sales.basket.BasketStorage;
import pl.wiktordziadosz.uekstock.sales.catalog.ProductDetails;
import pl.wiktordziadosz.uekstock.sales.catalog.ProductDetailsProvider;
import pl.wiktordziadosz.uekstock.sales.offerting.OfferMaker;
import pl.wiktordziadosz.uekstock.sales.ordering.InMemoryReservationStorage;
import pl.wiktordziadosz.uekstock.sales.ordering.JpaReservationStorage;
import pl.wiktordziadosz.uekstock.sales.ordering.ReservationRepository;
import pl.wiktordziadosz.uekstock.sales.payment.PayUPaymentGateway;

import java.math.BigDecimal;
import java.util.Arrays;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public ProductCatalog createProductCatalog(
            ProductRepository productRepository) {
        ProductCatalog productCatalog = new ProductCatalog(productRepository);
        String productId1 = productCatalog.addProduct(
                "MY Example product 1",
                BigDecimal.valueOf(10.10),
                Arrays.asList("tag1", "tag2"),
                "https://picsum.photos/200/300"
        );
        productCatalog.publish(productId1);

        String productId2 = productCatalog.addProduct(
                "Example product 2",
                BigDecimal.valueOf(20.10),
                Arrays.asList("tag2"),
                "https://picsum.photos/300/200"
        );
        productCatalog.publish(productId2);

        String productId3 = productCatalog.addProduct(
                "Example product 3",
                BigDecimal.valueOf(30.10),
                Arrays.asList("tag2"),
                "https://picsum.photos/301/201"
        );
        productCatalog.publish(productId3);

        String productId4 = productCatalog.addProduct(
                "Example product 4",
                BigDecimal.valueOf(40.10),
                Arrays.asList("tag2"),
                "https://picsum.photos/401/201"
        );
        productCatalog.publish(productId4);

        return productCatalog;
    }

    @Bean
    public SalesFacade createSalesFacade(ProductDetailsProvider productDetailsProvider, PayU payU) {
        return new SalesFacade(
                new BasketStorage(),
                productDetailsProvider,
                new OfferMaker(productDetailsProvider),
                new InMemoryReservationStorage(),
                new PayUPaymentGateway(payU));
    }

    @Bean
    public ProductDetailsProvider productDetailsProvider(ProductCatalog productCatalog ) {
        return (id) -> {
            Product product = productCatalog.getById(id);
            return new ProductDetails(
                    product.getId(),
                    product.getPrice()
            );
        };
    }

    @Bean
    public JpaReservationStorage createJpaReervationStorage(ReservationRepository reservationRepository) {
        return new JpaReservationStorage(reservationRepository);
    }
}
