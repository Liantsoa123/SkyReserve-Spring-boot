package mg.noob.SkyReserve_Spring_boot.repository;

import mg.noob.SkyReserve_Spring_boot.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    // Méthodes personnalisées si nécessaire
}