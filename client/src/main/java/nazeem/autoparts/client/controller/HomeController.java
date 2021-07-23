package nazeem.autoparts.client.controller;

import nazeem.autoparts.library.model.Category;
import nazeem.autoparts.library.model.Product;
import nazeem.autoparts.library.service.CategoryService;
import nazeem.autoparts.library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;


    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("classActiveHome", "home hover active ");

        return "/client/home";
    }

    @RequestMapping("/category")
    public String category(@RequestParam("id") Optional<Long> id, Model model) {
        model.addAttribute("classActiveCategory", "active");

        //Get all categories
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categories", categoryList);

        //Get selected product & category
        List<Product> productList;
        Category category;
        if(id.isPresent()) {
            productList = productService.findAllByCategoryId(id.get());
            category = categoryService.get(id.get());
        }else {
            productList = productService.findAllByActive();
            category = new Category();
            category.setName("All Categories");
        }
        model.addAttribute("productList", productList);
        model.addAttribute("category", category);

        return "/client/category";
    }

    @RequestMapping("/part-search")
    public String productSearch(Model model) {
        model.addAttribute("classActivePartSearch", "active");

        return "/client/part-search";
    }
    @RequestMapping("/part-details")
    public String productDetails(@RequestParam("id") Long id, Model model) {
        model.addAttribute("classActivePartSearch", "active");

        //Get product
        Product product = productService.get(id);
        model.addAttribute("product", product);

        return "/client/part-details";
    }

    @RequestMapping("/view-cart")
    public String viewCart(Model model) {
        model.addAttribute("classActiveViewCart", "active");

        return "/client/view-cart";
    }

    @RequestMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("classActiveCheckout", "active");

        return "/client/checkout";
    }


    @RequestMapping("/about-us")
    public String aboutUs(Model model) {
        model.addAttribute("classActivePages", "with-sub-menu hover active ");

        return "/client/about-us";
    }
    @RequestMapping("/contact-us")
    public String contactUs(Model model) {
        model.addAttribute("classActivePages", "with-sub-menu hover active ");

        return "/client/contact-us";
    }




    @RequestMapping("/faq")
    public String faq(Model model) {
        model.addAttribute("classActivePages", "with-sub-menu hover active ");

        return "/client/faq";
    }
    @RequestMapping("/privacy-policy")
    public String privacyPolicy(Model model) {
        model.addAttribute("classActivePages", "with-sub-menu hover active ");

        return "/client/privacy-policy";
    }
    @RequestMapping("/return-policy")
    public String returnPolicy(Model model) {
        model.addAttribute("classActivePages", "with-sub-menu hover active ");

        return "/client/return-policy";
    }
    @RequestMapping("/terms-and-conditions")
    public String termsAndConditions(Model model) {
        model.addAttribute("classActivePages", "with-sub-menu hover active ");

        return "/client/terms-and-conditions";
    }





    // Added to test 500 page
    @RequestMapping(path = "/tigger-error", produces = MediaType.APPLICATION_JSON_VALUE)
    public void error500() throws Exception {
        throw new Exception();
    }

}
