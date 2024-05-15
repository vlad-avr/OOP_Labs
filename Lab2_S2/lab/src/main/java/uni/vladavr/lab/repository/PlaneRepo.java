package uni.vladavr.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.vladavr.lab.entity.Plane;

@Repository
public interface PlaneRepo extends JpaRepository<Plane, String> {
}
