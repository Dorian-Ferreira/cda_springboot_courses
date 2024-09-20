package fr.human_booster.dorian_ferreira.printemps.repository;

import fr.human_booster.dorian_ferreira.printemps.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT u FROM User AS u ORDER BY RAND() LIMIT 1")
    User findRandom();
}
