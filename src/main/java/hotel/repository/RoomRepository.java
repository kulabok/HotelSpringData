package hotel.repository;

import hotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kulabok on 06.05.2016.
 */
public interface RoomRepository extends JpaRepository<Room, Integer> {
}
