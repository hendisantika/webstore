package id.my.hendisantika.webstore.controller;

import id.my.hendisantika.webstore.entity.Category;
import id.my.hendisantika.webstore.entity.Product;
import id.my.hendisantika.webstore.entity.UnbanRequest;
import id.my.hendisantika.webstore.entity.User;
import id.my.hendisantika.webstore.repository.CategoryRepository;
import id.my.hendisantika.webstore.repository.CommentRepository;
import id.my.hendisantika.webstore.repository.ProductRepository;
import id.my.hendisantika.webstore.repository.UnbanRequestRepository;
import id.my.hendisantika.webstore.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : webstore
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 19/07/25
 * Time: 05.19
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserRepository userRepo;

    private final CategoryRepository categoryRepo;

    private final ProductRepository productRepo;

    private final UnbanRequestRepository unbanRequestRepo;

    private final CommentRepository commentRepo;

    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping(value = { "/", "/home" })
    public String firstHomeView(Model m, Principal principal) {

        if (principal != null) {
            m.addAttribute("user", this.userRepo.loadUserByUserName(principal.getName()));
        }

        List<Product> products = this.productRepo.getProducts();
        List<Category> categories = this.categoryRepo.getCategories();

        m.addAttribute("title", "StoreWala | Start Shopping Now!");
        m.addAttribute("categories", categories);
        m.addAttribute("products", products);
        return "index.html";
    }

    @GetMapping("/register")
    public String registerPage(Model m) {
        m.addAttribute("title", "Register | StoreWala");
        m.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/process-registration")
    public String doRegister(@Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes redir,
                             @RequestParam("confirm_password") String confirmPassword, @RequestParam("user_role") String role, Model m,
                             HttpSession httpSession) {

        if (result.hasErrors()) {
            m.addAttribute("user", user);
            return "register";
        }

        if (role.equals("non-selected")) {
            m.addAttribute("user", user);
            httpSession.setAttribute("status", "role-not-select");
            return "redirect:/register";
        }

        if (confirmPassword.equals("") || confirmPassword == null) {
            m.addAttribute("user", user);
            httpSession.setAttribute("status", "cp-empty");
            return "redirect:/register";
        }

        if (!user.getPassword().equals(confirmPassword)) {
            m.addAttribute("user", user);
            httpSession.setAttribute("status", "cp-not-match");
            return "redirect:/register";
        }

        try {
            user.setRole(role.equals("customer") ? "ROLE_CUSTOMER" : "ROLE_SELLER");

            user.setEnable(true);
            user.setProfile("user.png");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setDate(new Date());

            this.userRepo.save(user);

        } catch (DataIntegrityViolationException e) {
            httpSession.setAttribute("status", "email-exist");
            m.addAttribute("user", user);
            return "redirect:/register";

        } catch (Exception e) {
            httpSession.setAttribute("status", "went-wrong");
            e.printStackTrace();
        }

        httpSession.setAttribute("status", "registered-success");
        return "redirect:/register";
    }

    @GetMapping("/login")
    public String loginPage(Model m) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {

            User user = this.userRepo.loadUserByUserName(auth.getName());

            if (user.getRole().equals("ROLE_CUSTOMER")) {
                return "redirect:/customer/home";
            }
            if (user.getRole().equals("ROLE_ADMIN")) {
                return "redirect:/admin/home";
            }
            if (user.getRole().equals("ROLE_SELLER")) {
                return "redirect:/seller/home";
            }

        }

        m.addAttribute("title", "Login | StoreWala");
        return "login";
    }

    /*
     * The above function is a method that handles requests to the /login URL. If
     * the user is logged in, the function redirects them to a different URL
     * depending on their role (e.g. /customer/home, /admin/home, or /seller/home).
     * If the user is not logged in, the function adds an attribute to the Model
     * object and returns a view that will likely render a login page for the user.
     */
    @GetMapping("/search")
    public String searchProducts(@RequestParam(value = "category", required = false) Integer categoryType,
                                 @RequestParam(name = "query", required = false) String query) {

        return "search_product";
    }

    /*
     *                         EXPLANATION OF SEARCH METHOD
     *
     *  the above function is a method that handles request to the /search URL.
     *  it displays all of the products related to the query and category.
     */

    @GetMapping("/unban-request")
    public String unbanRequestView(Model m) {
        m.addAttribute("title", "Unban Request | StoreWala");
        m.addAttribute("unbanRequest", new UnbanRequest());
        return "unban";
    }
}
