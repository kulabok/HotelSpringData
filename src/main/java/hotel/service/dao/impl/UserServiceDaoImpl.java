package hotel.service.dao.impl;

import hotel.entity.User;
import hotel.repository.UserRepository;
import hotel.service.dao.UserServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kulabok on 06.05.2016.
 */
@Service
public class UserServiceDaoImpl implements UserServiceDao {
    @Autowired
    private UserRepository userRepository;

    public User isExist(String login, String password){
        User user = userRepository.isExist(login, password);
        return user;
    }

    @Override
    public User addUser(User user) {
        User savedUser = userRepository.saveAndFlush(user);
        return savedUser;
    }

    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }

    @Override
    public User getById(int id) {
        return userRepository.findOne(id);
    }

    @Override
    public User editUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
