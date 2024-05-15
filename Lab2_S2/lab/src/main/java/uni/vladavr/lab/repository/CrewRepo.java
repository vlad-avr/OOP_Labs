package uni.vladavr.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.vladavr.lab.entity.Crewmate;

@Repository
public interface CrewRepo extends JpaRepository<Crewmate, String> {
}
