package id.my.hendisantika.webstore.repository;

import id.my.hendisantika.webstore.entity.UnbanRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
 * Time: 04.18
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface UnbanRequestRepository extends JpaRepository<UnbanRequest, Integer> {

    @Query(value = "select * from unban_requests", nativeQuery = true)
    List<UnbanRequest> getAllRequest();
}
