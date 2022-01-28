package pl.wiktordziadosz.uekstock.sales;

import org.springframework.web.bind.annotation.*;
import pl.wiktordziadosz.uekstock.sales.datatransferobject.CustomerData;
import pl.wiktordziadosz.uekstock.sales.offerting.Offer;
import pl.wiktordziadosz.uekstock.sales.ordering.ReservationDetails;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
public class SalesController {

    public static final String CUSTOMER_ID_KEY = "CUSTOMER_ID";
    SalesFacade sales;
    private HttpSession httpSession;

    public SalesController(SalesFacade salesFacade, HttpSession httpSession) {
        this.sales = salesFacade;
        this.httpSession = httpSession;
    }

    @PostMapping("/api/add-product/{productId}")
    public void addProductToBasket(@PathVariable String productId) {
        sales.addToBasket(getCurrentCustomerId(), productId);
    }

    @GetMapping("/api/current-offer")
    public Offer currentOffer() {
        return sales.getCurrentOffer(getCurrentCustomerId());
    }

    @PostMapping("/api/accept-offer")
    public ReservationDetails acceptOffer(@RequestBody CustomerData customerData) {
        return sales.acceptOffer(getCurrentCustomerId(), customerData);
    }

    @GetMapping("/api/current-customer-id")
    public String currentCustomerId() {
        return getCurrentCustomerId();
    }

    private String getCurrentCustomerId() {
        Object currentCustomerId = httpSession.getAttribute(CUSTOMER_ID_KEY);

        if (currentCustomerId != null) {
            return (String) currentCustomerId;
        }

        String newCustomerId = UUID.randomUUID().toString();
        httpSession.setAttribute(CUSTOMER_ID_KEY, newCustomerId);

        return newCustomerId;
    }

}
