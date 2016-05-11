package hotel.service;

import hotel.config.DataConfig;
import hotel.entity.Bill;
import hotel.entity.Request;
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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

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
    private RequestService requestService;
    @Resource
    private UserService userService;
    @Resource
    private RoomService roomService;
    @Resource
    private BillService billService;

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
            r.setId(1);
            r.setRoomClass(RoomClass.LUX);
            r.setPersonQuantity(2);
            User u = new User();
            u.setFullName("Andrew");
            r.setUser(userService.addUser(u));
            r.setStart(new Date(dateStr.getTime()));
            r.setEnd(new Date(dateStri.getTime()));
            b.setRequest(requestService.addRequest(r));
            Room room = new Room();
            room.setRoomClass(RoomClass.STANDARD);
            room.setPersonsMax(3);
            room.setCostPerPerson(500);
            room.setAvailable(2);
            b.setRoom(roomService.addRoom(room));
            billService.addBill(b);
        }
    }

    @Test
    public void testAddBill() throws Exception {
        String start = "18-08-2016";
        String end = "18-09-2016";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date dateStr = formatter.parse(start);
        java.util.Date dateStri = formatter.parse(end);
        Bill b = new Bill();
        Request r = new Request();
        r.setId(12);
        r.setRoomClass(RoomClass.LUX);
        r.setPersonQuantity(12);
        User u = new User();
        u.setFullName("Jack Sparrow");
        r.setUser(userService.addUser(u));
        r.setStart(new Date(dateStr.getTime()));
        r.setEnd(new Date(dateStri.getTime()));
        b.setRequest(requestService.addRequest(r));
        Room room = new Room();
        room.setRoomClass(RoomClass.LUX);
        room.setPersonsMax(3);
        room.setCostPerPerson(600);
        room.setAvailable(2);
        b.setRoom(roomService.addRoom(room));
        b.setCost(b.getRoom().getCostPerPerson() * b.getRequest().getPersonQuantity());
        billService.addBill(b);
        List<Bill> billList = billService.getAll();
        for (int i = 0; i < billList.size(); i++) {
            if (billList.get(i).getCost()!=0){
                assertTrue(billList.get(i).getCost()>0);
            }
        }
    }

    @Test
    public void testDelete() throws Exception {
        List<Bill> billList = billService.getAll();
        for (int i = 0; i <billList.size(); i++) {
            if (billService.getById(i) != null) {
                billService.delete(i);
                assertTrue(billService.getById(i) == null);
                break;
            }
        }
    }

    @Test
    public void testGetById() throws Exception {
        List<Bill> billList = billService.getAll();
        for (Bill aBillList : billList) {
            int k = aBillList.getId();
            assertTrue(billService.getById(k) != null);
        }
    }

    @Test
    public void testEditBill() throws Exception {

    }

    @Test
    public void testGetAll() throws Exception {
        String start = "14-05-2016";
        String end = "18-07-2016";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date dateStr = formatter.parse(start);
        java.util.Date dateStri = formatter.parse(end);
        for (int i = 0; i < 7; i++) {
            Bill b = new Bill();
            Request r = new Request();
            r.setId(1);
            r.setRoomClass(RoomClass.LUX);
            r.setPersonQuantity(2);
            User u = new User();
            u.setFullName("Andrew");
            r.setUser(userService.addUser(u));
            r.setStart(new Date(dateStr.getTime()));
            r.setEnd(new Date(dateStri.getTime()));
            b.setRequest(requestService.addRequest(r));
            Room room = new Room();
            room.setRoomClass(RoomClass.STANDARD);
            room.setPersonsMax(3);
            room.setCostPerPerson(500);
            room.setAvailable(2);
            b.setRoom(roomService.addRoom(room));
            billService.addBill(b);
        }
        List<Bill> billList = billService.getAll();
        assertTrue(billList.size()>0);
    }

    @After
    public void tearDown() throws Exception {
        List<Request> requestList = requestService.getAll();
        List<User> userList = userService.getAll();
        List<Room> roomList = roomService.getAll();
        List<Bill> billList = billService.getAll();
        for (Bill aBillList : billList) {
            billService.delete(aBillList.getId());
        }
        for (Request aRequestList : requestList) {
            requestService.delete(aRequestList.getId());
        }
        for (User anUserList : userList) {
            userService.delete(anUserList.getId());
        }
        for (Room aRoomList : roomList) {
            roomService.delete(aRoomList.getNumber());
        }

    }
}