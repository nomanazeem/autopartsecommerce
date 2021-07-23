package nazeem.autoparts.client.controller;

import nazeem.autoparts.library.service.CategoryService;
import nazeem.autoparts.library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyAccountController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/my-account")
    public String dashboard(Model model) {
        model.addAttribute("classActiveMyAccount", "active");

        return "/my-account/my-account";
    }

    @RequestMapping("/order-history")
    public String orderHistory(Model model) {
        model.addAttribute("classActiveMyAccount", "active");

        return "/my-account/order-history";
    }
    @RequestMapping("/order-details")
    public String orderDetails(Model model) {
        model.addAttribute("classActiveMyAccount", "active");

        return "/my-account/order-details";
    }
}
