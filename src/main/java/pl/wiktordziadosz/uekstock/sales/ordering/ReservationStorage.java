package pl.wiktordziadosz.uekstock.sales.ordering;

import java.util.Optional;

public interface ReservationStorage {
    Optional<Reservation> load(String reservationId);

    void save(Reservation reservation);
}
