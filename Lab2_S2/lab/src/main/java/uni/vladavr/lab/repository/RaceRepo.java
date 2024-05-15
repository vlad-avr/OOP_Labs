package uni.vladavr.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.vladavr.lab.entity.Race;

@Repository
public interface RaceRepo extends JpaRepository<Race, String> {
}
