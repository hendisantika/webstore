package id.my.hendisantika.webstore.config;

import id.my.hendisantika.webstore.entity.User;
import id.my.hendisantika.webstore.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * Project : webstore
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 19/07/25
 * Time: 04.21
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserRepository userRepo;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsServiceImpl getUserDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/customer/**").hasRole("CUSTOMER")
                        .requestMatchers("/seller/**").hasRole("SELLER")
                        .requestMatchers("/**", "/register").permitAll()
                )
                .formLogin(form -> form
                        .successHandler(new AuthenticationSuccessHandler() {
                            @Override
                            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                                Authentication authentication) throws IOException, ServletException {

                                User user = userRepo.loadUserByUserName(authentication.getName());

                                String redirectURL = request.getContextPath();

                                if(user.getRole().equals("ROLE_CUSTOMER")) {
                                    redirectURL = "/customer/home";
                                }
                                if(user.getRole().equals("ROLE_SELLER")) {
                                    redirectURL = "/seller/home";
                                }
                                if(user.getRole().equals("ROLE_ADMIN")) {
                                    redirectURL = "/admin/home";
                                }

                                response.sendRedirect(redirectURL);
                            }
                        })
                        .failureHandler(new AuthenticationFailureHandler() {
                            @Override
                            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                                                AuthenticationException exception) throws IOException, ServletException {

                                HttpSession httpSession = request.getSession();

                                if(exception.getMessage().equals("Bad credentials")) {
                                    httpSession.setAttribute("status", "bad-credentials");
                                    response.sendRedirect("/login?=BadCredentials");
                                    return;
                                }

                                if(exception.getMessage().equals("User is disabled")) {
                                    httpSession.setAttribute("status", "user-disabled");
                                    response.sendRedirect("/login?=AccountSuspended");
                                }
                            }
                        })
                        .loginPage("/login")
                )
                .logout(logout -> logout
                        .logoutSuccessHandler(new LogoutSuccessHandler() {
                            @Override
                            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
                                    throws IOException, ServletException {

                                HttpSession httpSession = request.getSession();

                                if(authentication!=null) {
                                    httpSession.setAttribute("status", "logout-success");
                                    response.sendRedirect("/login?logoutSuccess");
                                }
                                else {
                                    response.sendRedirect("/login?doLogin");
                                }
                            }
                        })
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
