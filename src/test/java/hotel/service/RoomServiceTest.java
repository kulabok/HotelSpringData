package hotel.service;

import hotel.config.DataConfig;
import hotel.entity.Room;
import hotel.entity.User;
import hotel.entity.enums.RoomClass;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
@WebAppConfiguration
public class RoomServiceTest {
    @Resource
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Resource
    private RoomService roomService;

    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
        for (int i = 0; i < 10; i++) {
            Room r = new Room();
            r.setNumber((int)(Math.random() * (10 * i)));
            r.setRoomClass(RoomClass.LUX);
            r.setAvailable(2);
            r.setCostPerPerson(400);
            r.setPersonsMax(2);
            roomService.addRoom(r);
        }
    }

    @Test
    public void testAddRoom() throws Exception {
        Room r = new Room();
        r.setNumber((int)(Math.random() * (10 + 2)));
        r.setRoomClass(RoomClass.STANDARD);
        r.setAvailable(2);
        r.setCostPerPerson(200);
        r.setPersonsMax(2);
        roomService.addRoom(r);
        List<Room> roomList = roomService.getAll();
        for (Room aRoomList : roomList) {
            if (!Objects.equals(aRoomList.getCostPerPerson(), 400)) {
                assertTrue(aRoomList.getCostPerPerson() == 200);
            }
        }
    }

    @Test
    public void testDelete() throws Exception {
        List<Room> roomList = roomService.getAll();
        for (int i = 0; i <roomList.size(); i++) {
            if (roomService.getByNumber(i) != null) {
                roomService.delete(i);
                assertTrue(roomService.getByNumber(i) == null);
                break;
            }
        }
    }

    @Test
    public void testGetByNumber() throws Exception {
        List<Room> roomList = roomService.getAll();
        for (Room aRoomList : roomList) {
            int k = aRoomList.getNumber();
            assertTrue(roomService.getByNumber(k) != null);
        }
    }

    @Test
    public void testEditRoom() throws Exception {
        Room r = new Room();
        r.setNumber(21);
        r.setRoomClass(RoomClass.STANDARD);
        r.setPersonsMax(3);
        r.setAvailable(3);
        r.setCostPerPerson(555);
        roomService.addRoom(r);
        Room room = roomService.getByNumber(21);
        room.setCostPerPerson(333);
        roomService.editRoom(room);
        assertTrue(roomService.getByNumber(21).getCostPerPerson() == 333);
        assertFalse(roomService.getByNumber(21).getCostPerPerson() == 555);
    }

    @Test
    public void testGetAll() throws Exception {
        List<Room> roomsList = roomService.getAll();
        assertTrue(roomsList.size()>0);
    }

    @After
    public void tearDown(){
        List<Room> userList = roomService.getAll();
        for (Room aRoomList : userList) {
            roomService.delete(aRoomList.getNumber());
        }
    }
}