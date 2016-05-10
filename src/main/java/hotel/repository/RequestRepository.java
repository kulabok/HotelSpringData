package hotel.repository;

import hotel.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kulabok on 06.05.2016.
 */
public interface RequestRepository extends JpaRepository<Request, Integer> {
}
