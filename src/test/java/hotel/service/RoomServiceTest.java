package hotel.service;

import hotel.config.DataConfig;
import hotel.entity.Room;
import hotel.entity.User;
import hotel.entity.enums.RoomClass;
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
            r.setRoomNumber((int)(Math.random() * (10 * i)));
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
        r.setRoomNumber((int)(Math.random() * (10 + 2)));
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

    }

    @Test
    public void testGetByNumber() throws Exception {

    }

    @Test
    public void testEditRoom() throws Exception {

    }

    @Test
    public void testGetAll() throws Exception {

    }
}