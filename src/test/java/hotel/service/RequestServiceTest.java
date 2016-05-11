package hotel.service;

import hotel.config.DataConfig;
import hotel.entity.Request;
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

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
@WebAppConfiguration
public class RequestServiceTest {
    @Resource
    private EntityManagerFactory emf;
    protected EntityManager em;
    @Resource
    private RequestService requestService;
    @Resource
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
        String start = "14-05-2016";
        String end = "16-06-2016";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dateStr = formatter.parse(start);
        Date dateStri = formatter.parse(end);
        for (int i = 0; i < 10; i++) {
            Request r = new Request();
            User u = new User();
            u.setFullName("Robert");
            r.setUser(userService.addUser(u));
            r.setRoomClass(RoomClass.STANDARD);
            r.setPersonQuantity(2);
            r.setStart(new Date(dateStr.getTime()));
            r.setEnd(new Date(dateStri.getTime()));
            requestService.addRequest(r);
        }
    }

    @Test
    public void testAddRequest() throws Exception {
        String start = "14-05-2016";
        String end = "18-07-2016";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date dateStr = formatter.parse(start);
        java.util.Date dateStri = formatter.parse(end);
        Request r = new Request();
        User u = new User();
        u.setFullName("Arthur");
        r.setUser(userService.addUser(u));
        r.setRoomClass(RoomClass.JUNIOR);
        r.setPersonQuantity(1);
        r.setStart(new java.sql.Date(dateStr.getTime()));
        r.setEnd(new java.sql.Date(dateStri.getTime()));
        requestService.addRequest(r);
        List<Request> requestList = requestService.getAll();
        for (int i = 0; i < requestList.size(); i++) {
            if (!requestList.get(i).getEnd().toString().equals("2016-06-16")){
                assertTrue(requestList.get(i).getEnd().toString().equals("2016-07-18"));
            }
        }
    }

    @Test
    public void testDelete() throws Exception {
        List<Request> requestList = requestService.getAll();
        for (int i = 0; i <requestList.size(); i++) {
            if (requestService.getById(i) != null) {
                requestService.delete(i);
                assertTrue(requestService.getById(i) == null);
                break;
            }
        }
    }

    @Test
    public void testGetById() throws Exception {
        List<Request> requestList = requestService.getAll();
        for (Request aRequestList : requestList) {
            int k = aRequestList.getId();
            assertTrue(requestService.getById(k) != null);
        }
    }

    @Test
    public void testEditRequest() throws Exception {
        List<Request> reqList = requestService.getAll();
        for (int i = 0; i < reqList.size(); i++) {
            if (reqList.get(i)!=null){
                Request req = reqList.get(i);
                req.setRoomClass(RoomClass.JUNIOR);
                req.setPersonQuantity(25);
                requestService.editRequest(req);
                break;
            }
        }
        reqList = requestService.getAll();
        for (int i = 0; i < reqList.size(); i++) {
            if (reqList.get(i).getPersonQuantity()==25){
                assertTrue(reqList.get(i).getRoomClass().toString().equals("JUNIOR"));
            }
        }

    }

    @Test
    public void testGetAll() throws Exception {
        List<Request> requestList = requestService.getAll();
        assertTrue(requestList.size()>0);
    }

    @After
    public void tearDown(){
        List<Request> requestList = requestService.getAll();
        List<User> userList = userService.getAll();
        for (Request aRequestList : requestList) {
            requestService.delete(aRequestList.getId());
        }
        for (User anUserList : userList) {
            userService.delete(anUserList.getId());
        }
    }
}