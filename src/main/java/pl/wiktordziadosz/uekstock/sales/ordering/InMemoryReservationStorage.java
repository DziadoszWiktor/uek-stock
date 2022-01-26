package pl.wiktordziadosz.uekstock.sales.ordering;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryReservationStorage implements ReservationStorage {
    Map<String, Reservation> reservations;

    public InMemoryReservationStorage() {
        this.reservations = new HashMap<>();
    }

    @Override
    public Optional<Reservation> load(String reservationId) {
        return Optional.ofNullable(reservations.get(reservationId));
    }

    @Override
    public void save(Reservation reservation) {
        reservations.put(reservation.getId(), reservation);
    }
}
