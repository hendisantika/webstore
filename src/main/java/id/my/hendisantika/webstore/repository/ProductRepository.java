package id.my.hendisantika.webstore.repository;

import id.my.hendisantika.webstore.entity.Product;
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
 * Time: 04.18
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select * from products", nativeQuery = true)
    List<Product> getProducts();

    @Query(value = "select * from products where product_seller_id = ?", nativeQuery = true)
    List<Product> getSellerAllProducts(@Param("id") int id);
}
