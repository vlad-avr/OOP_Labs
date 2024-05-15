package uni.vladavr.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.vladavr.lab.entity.Race;

import java.util.List;
import java.util.Optional;

@Repository
public interface RaceRepo extends JpaRepository<Race, String> {
    Optional<List<Race>> findByDeparturePlace(String departurePlace);
    Optional<List<Race>> findByArrivalPlace(String arrivalPlace);
}
