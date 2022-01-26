package pl.wiktordziadosz.uekstock.sales.ordering;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PaymentDetails {
    private String reservationId;
    private String paymentId;
    private String paymentUrl;

    public String getId() {
        return paymentId;
    }

    public String getUrl() {
        return paymentUrl;
    }
}
