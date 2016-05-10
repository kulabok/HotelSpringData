package hotel.service;

import hotel.entity.Request;
import hotel.entity.User;

import java.util.List;

/**
 * Created by kulabok on 06.05.2016.
 */
public interface UserService {
    User addUser(User user);
    void delete(int id);
    User getById(int id);
    User editUser (User user);
    List<User> getAll();
}
