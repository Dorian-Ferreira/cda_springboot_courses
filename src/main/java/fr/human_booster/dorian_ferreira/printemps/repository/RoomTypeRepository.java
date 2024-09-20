package fr.human_booster.dorian_ferreira.printemps.repository;

import fr.human_booster.dorian_ferreira.printemps.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
}
