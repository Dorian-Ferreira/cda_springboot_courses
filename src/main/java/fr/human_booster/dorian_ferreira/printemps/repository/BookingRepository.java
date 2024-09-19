package fr.human_booster.dorian_ferreira.printemps.repository;

import fr.human_booster.dorian_ferreira.printemps.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
}
