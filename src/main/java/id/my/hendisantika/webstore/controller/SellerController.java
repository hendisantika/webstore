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
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : webstore
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 19/07/25
 * Time: 05.23
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/seller")
public class SellerController {

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;
    private final UserRepository userRepo;

    static String usingRandomUUID() {

        UUID randomUUID = UUID.randomUUID();

        return randomUUID.toString().replaceAll("_", "");

    }

    @GetMapping(value = { "/home", "/" })
    public String sellerHome(Model m, Principal principal) {

        User user = this.userRepo.loadUserByUserName(principal.getName());
        List<Product> sellerProducts = this.productRepo.getSellerAllProducts(user.getId());
        List<Category> categories = this.categoryRepo.getCategories();

        m.addAttribute("title", "Seller Panel | StoreWala");
        m.addAttribute("product", new Product());
        m.addAttribute("categories", categories);
        m.addAttribute("user", user);
        m.addAttribute("products", sellerProducts);
        return "seller/index";
    }
}
