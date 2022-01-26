package pl.wiktordziadosz.uekstock.sales.ordering;

import javax.persistence.*;

@Entity
public class ReservationLine {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    Reservation reservation;

    private String productId;
    private int quantity;

    ReservationLine() {};

    public ReservationLine(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
