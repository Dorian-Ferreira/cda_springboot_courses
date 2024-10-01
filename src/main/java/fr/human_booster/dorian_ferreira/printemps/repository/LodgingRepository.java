package fr.human_booster.dorian_ferreira.printemps.repository;

import fr.human_booster.dorian_ferreira.printemps.entity.Lodging;
import fr.human_booster.dorian_ferreira.printemps.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LodgingRepository extends JpaRepository<Lodging, String> {
    @Query("SELECT l FROM Lodging AS l ORDER BY RAND() LIMIT 1")
    Lodging findRandom();

    List<Lodging> findAllByIsOpen(boolean isOpen);

    Optional<Lodging> findBySlug(String slug);

    Optional<Lodging> findByUuidAndIsOpen(String lodgingId, boolean isOpen);
}
