package id.my.hendisantika.webstore.repository;

import id.my.hendisantika.webstore.entity.Comment;
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
 * Time: 04.17
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query(value = "select * from comments where comment_related_to = ?", nativeQuery = true)
    List<Comment> getAllComments(@Param("id") Integer id);
}
