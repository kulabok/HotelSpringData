package hotel.service.dao.impl;

import hotel.entity.Room;
import hotel.repository.RoomRepository;
import hotel.service.dao.RoomServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kulabok on 06.05.2016.
 */
@Service
public class RoomServiceDaoImpl implements RoomServiceDao {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room addRoom(Room room) {
        Room savedRoom = roomRepository.saveAndFlush(room);
        return savedRoom;
    }

    @Override
    public void delete(int id) {
        roomRepository.delete(id);
    }

    @Override
    public Room getByNumber(int number) {
        return roomRepository.findOne(number);
    }

    @Override
    public Room editRoom(Room room) {
        return roomRepository.saveAndFlush(room);
    }

    @Override
    public List<Room> getAll() {
        return roomRepository.findAll();
    }
}
