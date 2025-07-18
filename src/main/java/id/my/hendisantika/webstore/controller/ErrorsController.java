package id.my.hendisantika.webstore.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

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
public class ErrorsController implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, @ModelAttribute("error") String error, Model m) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == 404) {
                m.addAttribute("title", "404 Not Found | StoreWala");
                return "errors/404";
            }

        }
        m.addAttribute("title", "Something Went Wrong | StoreWala");
        return "errors/500";

    }
}
