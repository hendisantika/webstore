package id.my.hendisantika.webstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * Project : webstore
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 18/07/25
 * Time: 15.02
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id; // user id is auto increment.

    @Column(name="user_name")
    @NotBlank(message = "Full Name is required.")
    private String name;  // user full name

    @Column(name="user_email", unique = true)
    @NotBlank(message = "Email is required.")
    private String email; // user email

    @Column(name = "user_pwd")
    @NotBlank(message = "Password is required.")
    private String password; // user password

    @Column(name = "user_role")
    private String role; // user role

    @Column(name = "user_phone")
    @NotBlank(message = "Phone Number is required.")
    private String phone; // user phone (String)

    @Column(name = "user_suspension_status")
    private boolean isEnable; // user Suspension status, true if yes and false if no.

    @Column(name="user_profile")
    private String profile;	// user profile image link (No actual image)


    @Column(name = "registered_date")
    private Date date;
}
