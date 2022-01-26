package pl.wiktordziadosz.uekstock.sales;

import pl.wiktordziadosz.uekstock.sales.ordering.Reservation;
import pl.wiktordziadosz.uekstock.sales.ordering.ReservationRepository;
import pl.wiktordziadosz.uekstock.sales.ordering.ReservationStorage;

import java.util.Optional;

public class JpaReservationStorage implements ReservationStorage {

    private ReservationRepository reservationRepository;

    public JpaReservationStorage(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Optional<Reservation> load(String reservationId) {
        return reservationRepository.findById(reservationId);
    }

    @Override
    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }
}
