package hotel.repository;

import hotel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by kulabok on 06.05.2016.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.login = :login and u.password = :password")
    User isExist(@Param("login") String login, @Param("password") String password);
}
