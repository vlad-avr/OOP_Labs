package uni.vladavr.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uni.vladavr.lab.entity.Crewmate;

import java.util.List;
import java.util.Optional;

@Repository
public interface CrewRepo extends JpaRepository<Crewmate, String> {
    Optional<List<Crewmate>> findByName(String name);
    Optional<List<Crewmate>> findByQualification(Crewmate.Qualification qualification);
}
