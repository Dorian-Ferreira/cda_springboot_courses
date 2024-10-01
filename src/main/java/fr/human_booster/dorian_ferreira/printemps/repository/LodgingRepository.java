package fr.human_booster.dorian_ferreira.printemps.repository;

import fr.human_booster.dorian_ferreira.printemps.entity.Lodging;
import fr.human_booster.dorian_ferreira.printemps.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface LodgingRepository extends JpaRepository<Lodging, String> {
    @Query("SELECT l FROM Lodging AS l ORDER BY RAND() LIMIT 1")
    Lodging findRandom();

    List<Lodging> findAllByIsOpenIsTrue();

    List<Lodging> findAllByIsOpenIsTrueAndNameContainingIgnoreCase(String name);

    Optional<Lodging> findBySlug(String slug);

    Optional<Lodging> findByUuidAndIsOpenIsTrue(String lodgingId);

    @Query("SELECT l FROM Lodging l WHERE l NOT IN (SELECT DISTINCT b.lodging FROM Booking b WHERE (b.startedAt BETWEEN ?1 AND ?2) OR (b.finishedAt BETWEEN ?3 AND ?4)) AND l.isOpen AND l.nightPrice < ?5 AND l.capacity >= ?6 ORDER BY l.nightPrice DESC")
    List<Lodging> findAllBySearch(LocalDateTime start, LocalDateTime end, LocalDateTime start2, LocalDateTime end2, int maxPrice, int quantity);

    @Query("SELECT l FROM Lodging l WHERE l NOT IN (SELECT DISTINCT b.lodging FROM Booking b WHERE (b.startedAt BETWEEN ?1 AND ?2) OR (b.finishedAt BETWEEN ?3 AND ?4)) AND l.isOpen AND l.nightPrice < ?5 AND l.capacity >= ?6 AND l.isAccessible ORDER BY l.nightPrice DESC")
    List<Lodging> findAllAccessibleBySearch(LocalDateTime start, LocalDateTime end, LocalDateTime start2, LocalDateTime end2, int maxPrice, int quantity);

    @Query("SELECT l FROM Lodging l WHERE l NOT IN (SELECT DISTINCT b.lodging FROM Booking b WHERE (b.startedAt BETWEEN ?1 AND ?2) OR (b.finishedAt BETWEEN ?3 AND ?4)) AND l.isOpen AND l.capacity >= ?5")
    List<Lodging> findAllBySearch(LocalDateTime start, LocalDateTime end, LocalDateTime start2, LocalDateTime end2, int quantity);

}
