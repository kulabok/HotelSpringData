package hotel.service;

import hotel.config.DataConfig;
import hotel.entity.Bill;
import hotel.entity.Request;
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

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import static org.junit.Assert.*;
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
@WebAppConfiguration
public class BillServiceTest {
    @Resource
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Resource
    private BillService billService;

    @Resource
    private UserService userService;

    @Resource
    private RequestService requestService;

    @Resource
    private RoomService roomService;

    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
        String start = "14-05-2016";
        String end = "18-07-2016";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date dateStr = formatter.parse(start);
        java.util.Date dateStri = formatter.parse(end);
        for (int i = 0; i < 10; i++) {
            Bill b = new Bill();
            Request r = new Request();
            r.setRoomClass(RoomClass.LUX);
            r.setPersonQuantity(2);
            User u = new User();
            userService.addUser(u);
            u.setFullName("Andrew");
            r.setUser(u);
            r.setStart(new java.sql.Date(dateStr.getTime()));
            r.setEnd(new java.sql.Date(dateStri.getTime()));
            requestService.addRequest(r);
            b.setRequest(r);
            Room room = new Room();
            room.setRoomNumber(222);
            room.setRoomClass(RoomClass.LUX);
            room.setPersonsMax(3);
            room.setCostPerPerson(500);
            room.setAvailable(2);
            b.setRoom(room);
            billService.addBill(b);
        }
    }

    @Test
    public void testAddBill() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testGetById() throws Exception {

    }

    @Test
    public void testEditBill() throws Exception {

    }

    @Test
    public void testGetAll() throws Exception {

    }
}