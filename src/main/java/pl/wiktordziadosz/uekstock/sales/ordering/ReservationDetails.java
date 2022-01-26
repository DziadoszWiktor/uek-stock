package pl.wiktordziadosz.uekstock.sales.ordering;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDetails {
    private String reservationId;
    private String paymentId;
    private String paymentUrl;

    public static ReservationDetails ofPayment(String reservationId, String paymentId, String paymentUrl) {
        return new ReservationDetails(reservationId, paymentId, paymentUrl);
    }
}
