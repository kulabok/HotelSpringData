package hotel.repository;

import hotel.entity.Bill;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kulabok on 06.05.2016.
 */
public interface BillRepository extends JpaRepository<Bill, Integer> {
}
