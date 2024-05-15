package uni.vladavr.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.vladavr.lab.entity.Brigade;

@Repository
public interface BrigadeRepo extends JpaRepository<Brigade, String> {
}
