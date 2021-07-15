package nazeem.autoparts.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index() {
        return "/client/home";
    }

    @RequestMapping("/category")
    public String category() {
        return "/client/category";
    }

    @RequestMapping("/part-search")
    public String partSearch() {
        return "/client/part-search";
    }
    @RequestMapping("/part-details")
    public String partDetails() {
        return "/client/part-details";
    }

    @RequestMapping("/cart")
    public String cart() {
        return "/client/cart";
    }

    @RequestMapping("/checkout")
    public String checkout() {
        return "/client/checkout";
    }

    // Added to test 500 page
    @RequestMapping(path = "/tigger-error", produces = MediaType.APPLICATION_JSON_VALUE)
    public void error500() throws Exception {
        throw new Exception();
    }

}
