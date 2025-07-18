package id.my.hendisantika.webstore.repository;

import id.my.hendisantika.webstore.entity.Category;
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
 * Time: 04.16
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = "select * from categories", nativeQuery = true)
    List<Category> getCategories();

    @Query(value = "select count(*) from categories where category_title = ?", nativeQuery = true)
    Integer getCategoriesByTitle(@Param("category_title") String title);
}
