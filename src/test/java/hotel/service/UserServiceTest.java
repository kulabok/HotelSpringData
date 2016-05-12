package hotel.service;

import hotel.config.DataConfig;
import hotel.entity.User;

import hotel.repository.UserRepository;
import hotel.service.dao.UserServiceDao;
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
public class UserServiceTest {

    @Resource
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Resource
    private UserServiceDao userService;

    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
        for (int i = 0; i < 10; i++) {
            User u = new User();
            u.setLogin("login");
            u.setPassword("password");
            u.setFullName("Peter Pan");
            userService.addUser(u);
        }
    }

    @Test
    public void testIsExist() throws Exception {
        User u = new User();
        u.setFullName("Chuck Norris");
        u.setLogin("Chuck");
        u.setPassword("Norris");
        u.setAdmin(false);
        userService.addUser(u);
        assertTrue(userService.isExist("Chuck", "Norris" ) != null);
    }

    @Test
    public void testAddUser() throws Exception {
        User u = new User();
        u.setFullName("Chuck Norris");
        u.setLogin("login");
        u.setPassword("password");
        u.setAdmin(false);
        userService.addUser(u);
        List<User> userList = userService.getAll();
        for (User anUserList : userList) {
            if (!Objects.equals(anUserList.getFullName(), "Peter Pan")) {
                assertTrue(anUserList.getFullName().equals("Chuck Norris"));
            }
        }
    }

    @Test
    public void testGetById() throws Exception {
        List<User> userList = userService.getAll();
        for (User anUserList : userList) {
            int k = anUserList.getId();
            assertTrue(userService.getById(k) != null);
        }
    }

    @Test
    public void testGetAll() throws Exception {
        List<User> usersList = userService.getAll();
        assertTrue(usersList.size()>0);
    }

    @Test
    public void testEditUser() throws Exception {
        User u = new User();
        u.setId(1);
        u.setLogin("login");
        u.setPassword("password");
        u.setFullName("Arnold");
        userService.editUser(u);
        assertFalse(userService.getById(1).getFullName().equals("Peter Pan"));
    }

    @Test
     public void testDelete() throws Exception {
        List<User> userList = userService.getAll();
        for (int i = 0; i <userList.size(); i++) {
            if (userService.getById(i) != null) {
                userService.delete(i);
                assertTrue(userService.getById(i) == null);
                break;
            }
        }
    }

    @After
    public void tearDown(){
        List<User> userList = userService.getAll();
        for (User anUserList : userList) {
            userService.delete(anUserList.getId());
        }
    }
}