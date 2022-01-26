package pl.wiktordziadosz.uekstock.sales;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.wiktordziadosz.uekstock.sales.offerting.Offer;
import pl.wiktordziadosz.uekstock.sales.ordering.Reservation;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

@SpringBootTest
public class JpaReservationStorageTest {

    @Autowired
    JpaReservationStorage jpaReservationStorage;

    @Test
    void itAllowsToStoreAndLoadReservation() {
        //Arrange
        Reservation reservation = exampleReservation();

        //Act
        jpaReservationStorage.save(reservation);
        Reservation loaded = jpaReservationStorage.load(reservation.getId()).get();

        //Assert
        assertEquals(reservation.getId(), loaded.getId());
        assertEquals(reservation.getCustomerEmail(), loaded.getCustomerEmail());
        assertTrue(reservation.getTotal().compareTo(loaded.getTotal()) == 0, "loaded to is not equal to initial one");
        //@toDo needsToBeChecked assertEquals(reservation.getLinesCount(), loaded.getLinesCount());

    }

    private Reservation exampleReservation() {
        return Reservation.of(
                Offer.of(BigDecimal.TEN, Collections.emptyList()),
                Arrays.asList(BasketItem.of("product-1", BigDecimal.valueOf(10.00))),
                new CustomerData("joh.doe@example.com", "john", "doe")
        );
    }
}
