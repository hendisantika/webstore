package id.my.hendisantika.webstore.repository;

import id.my.hendisantika.webstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : webstore
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 19/07/25
 * Time: 04.19
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.email =:email")
    User loadUserByUserName(@Param("email") String email);

    @Query(value = "select * from users", nativeQuery = true)
    List<User> getUsers();
}
