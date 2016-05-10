package hotel.repository;

import hotel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kulabok on 06.05.2016.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
