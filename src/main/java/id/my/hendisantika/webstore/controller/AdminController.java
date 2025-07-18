package id.my.hendisantika.webstore.controller;

import id.my.hendisantika.webstore.repository.CategoryRepository;
import id.my.hendisantika.webstore.repository.ProductRepository;
import id.my.hendisantika.webstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
