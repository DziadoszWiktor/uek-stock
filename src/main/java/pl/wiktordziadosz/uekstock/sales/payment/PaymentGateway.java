package pl.wiktordziadosz.uekstock.sales.payment;

import pl.wiktordziadosz.uekstock.sales.ordering.CustomerDetails;
import pl.wiktordziadosz.uekstock.sales.ordering.PaymentDetails;

import java.math.BigDecimal;

public interface PaymentGateway {
    PaymentDetails register(String id, BigDecimal total, CustomerDetails customerDetails);
}
