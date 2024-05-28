package uni.vladavr.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uni.vladavr.lab.entity.Plane;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaneRepo extends JpaRepository<Plane, String> {
    Optional<List<Plane>> findByModel(String model);
    @Query("DELETE Flight f WHERE f.planeId = ?1")
    void cascadeDelete(String Id);
}
