package hotel.service;



import hotel.entity.Room;

import java.util.List;

/**
 * Created by kulabok on 06.05.2016.
 */
public interface RoomService {
    Room addRoom(Room room);
    void delete(int id);
    Room getByNumber(int number);
    Room editRoom (Room room);
    List<Room> getAll();
}
