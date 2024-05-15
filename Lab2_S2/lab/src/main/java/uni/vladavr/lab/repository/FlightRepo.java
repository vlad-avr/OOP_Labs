package uni.vladavr.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uni.vladavr.lab.entity.Flight;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepo extends JpaRepository<Flight, String> {
    void deleteAllByBrigadeId(String brigadeId);
    void deleteAllByPlaneId(String planeId);
    void deleteAllByRaceId(String raceId);
    @Query("SELECT f.brigadeId FROM Flight f")
    Optional<List<String>> findBrigadeIds();
    @Query("SELECT f.planeId FROM Flight f")
    Optional<List<String>> findPlaneIds();
    @Query("SELECT f.raceId FROM Flight f")
    Optional<List<String>> findRaceIds();
}
