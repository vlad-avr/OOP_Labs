package uni.vladavr.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.vladavr.lab.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
}
