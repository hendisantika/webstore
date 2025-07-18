package id.my.hendisantika.webstore.controller;

import id.my.hendisantika.webstore.entity.Category;
import id.my.hendisantika.webstore.entity.Product;
import id.my.hendisantika.webstore.entity.User;
import id.my.hendisantika.webstore.repository.CategoryRepository;
import id.my.hendisantika.webstore.repository.ProductRepository;
import id.my.hendisantika.webstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : webstore
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 19/07/25
 * Time: 05.17
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserRepository userRepo;

    private final ProductRepository productRepo;

    private final CategoryRepository categoryRepo;

    @GetMapping(value = { "/", "/home" })
    public String home(Model m, Principal principal) {

        User user = this.userRepo.loadUserByUserName(principal.getName());

        List<User> users = this.userRepo.getUsers();
        List<Category> categories = this.categoryRepo.getCategories();
        List<Product> products = this.productRepo.getProducts();

        m.addAttribute("title", "Admin | StoreWala");
        m.addAttribute("user", user);
        m.addAttribute("users", users);
        m.addAttribute("products", products);
        m.addAttribute("categories", categories);
        m.addAttribute("category", new Category());

        return "admin/index";
    }
}
