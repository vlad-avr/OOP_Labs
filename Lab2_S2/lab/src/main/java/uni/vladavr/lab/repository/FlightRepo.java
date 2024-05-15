package uni.vladavr.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.vladavr.lab.entity.Flight;

@Repository
public interface FlightRepo extends JpaRepository<Flight, String> {
}
