package id.my.hendisantika.webstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Table(name = "unban_requests")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnbanRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_message")
    @NotBlank(message = "Message is required.")
    @Size(min = 30, max = 500, message = "Minimum 30 char and Maximum 500 char are required.")
    private String message;

    @Column(name = "from_user")
    @NotBlank(message = "Valid Email Address is required.")
    private String email;
}
