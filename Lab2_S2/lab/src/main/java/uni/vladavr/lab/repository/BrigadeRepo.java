package uni.vladavr.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uni.vladavr.lab.entity.Brigade;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrigadeRepo extends JpaRepository<Brigade, String> {
    Optional<List<Brigade>> findByName(String name);
}
