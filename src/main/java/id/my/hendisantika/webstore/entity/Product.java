package id.my.hendisantika.webstore.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * Project : webstore
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 18/07/25
 * Time: 15.00
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "product_name")
    @NotBlank(message = "Product name is required.")
    @Size(min = 10, max = 40)
    private String name;

    @Column(name = "product_highlights", length = 200)
    @NotBlank(message = "Product highlights is required.")
    @Size(min = 10)
    private String highlights;

    @Column(name = "product_description", length = Integer.MAX_VALUE)
    @NotBlank(message = "Product description is required.")
    @Size(min = 10, message = "Product description should be have atleast 10 characters.")
    private String description;

    @Column(name = "product_price")
    @NotNull(message = "Product price is required.")
    private Integer price;

    @Column(name = "product_discount")
    @NotNull(message = "Product discount is required.")
    private Integer discount;

    @Column(name = "product_stocks")
    @NotNull(message = "Product stocks is required.")
    private Integer stocks;

    @ElementCollection
    @CollectionTable(name = "product_images")
    private Collection<String> images;

    @Column(name = "product_seller_name")
    private String sellerName;

    @Column(name = "product_seller_id")
    private Integer sellerId;

    @Column(name = "price_after_discount")
    private Integer priceAfterDiscount;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", highlights=" + highlights + ", description=" + description
                + ", price=" + price + ", discount=" + discount + ", stocks=" + stocks + ", images=" + images
                + ", sellerName=" + sellerName + ", sellerId=" + sellerId + ", priceAfterDiscount=" + priceAfterDiscount
                + ", category=" + category + "]";
    }

    /* getting price after applying discount */

    public int getDiscountPrice() {
        int discount = (int) ((this.getDiscount()/100.0)*this.getPrice());
        return this.getPrice()-discount;
    }

    public String highlightShorter(String highlight) {

        String[] highlights = highlight.split(" ");

        if(highlights.length > 14) {

            String res = "";

            for(int i = 0; i < 14 ; i++) {

                res += highlights[i]+" ";
            }

            return (res + "...");

        }


        return highlight + "...";
    }
}
