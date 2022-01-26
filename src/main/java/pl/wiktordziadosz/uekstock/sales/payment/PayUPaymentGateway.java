package pl.wiktordziadosz.uekstock.sales.payment;

import pl.wiktordziadosz.uekstock.payment.PayU;
import pl.wiktordziadosz.uekstock.payment.RegisterPaymentRequest;
import pl.wiktordziadosz.uekstock.payment.RegisterPaymentResponse;
import pl.wiktordziadosz.uekstock.sales.ordering.CustomerDetails;
import pl.wiktordziadosz.uekstock.sales.ordering.PaymentDetails;

import java.math.BigDecimal;
import java.util.Collections;

public class PayUPaymentGateway implements PaymentGateway {
    private PayU payU;

    public PayUPaymentGateway(PayU payU) {

        this.payU = payU;
    }

    @Override
    public PaymentDetails register(String reservationId, BigDecimal total, CustomerDetails customerDetails) {
        RegisterPaymentResponse response = payU.handle(RegisterPaymentRequest.builder()
                .customerIp("127.0.0.1")
                .description("My Noce service")
                .currencyCode("PLN")
                .totalAmount(getValueAsInteger(total))
                .extOrderId(reservationId)
                .buyer(new RegisterPaymentRequest.Buyer(customerDetails.getEmail(), customerDetails.getFirstname(), customerDetails.getLastname()))
                .products(Collections.emptyList())
                .build());

        return new PaymentDetails(reservationId, response.getOrderId(), response.getRedirectUri());
    }

    private int getValueAsInteger(BigDecimal total) {
        return total.multiply(BigDecimal.valueOf(100)).intValue();
    }
}
