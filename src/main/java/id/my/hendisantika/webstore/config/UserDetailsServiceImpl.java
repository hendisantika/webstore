package id.my.hendisantika.webstore.config;

import id.my.hendisantika.webstore.entity.User;
import id.my.hendisantika.webstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by IntelliJ IDEA.
 * Project : webstore
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 19/07/25
 * Time: 04.20
 * To change this template use File | Settings | File Templates.
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userRepo.loadUserByUserName(username);

        if(user==null) {
            throw new UsernameNotFoundException("No user is available for this email address.");
        }

        return new CustomUserDetails(user);
    }
}
