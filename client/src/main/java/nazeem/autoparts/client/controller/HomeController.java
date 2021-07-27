package nazeem.autoparts.client.controller;

import nazeem.autoparts.library.model.*;
import nazeem.autoparts.library.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CustomerService customerService;

    @Autowired
    CountryService countryService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    OrderService orderService;


    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("classActiveHome", "home hover active ");

        List<Product> productList = productService.findAllByActive();
        model.addAttribute("productList", productList);

        List<Product> productList2 = productService.findAllByCategoryId(1L);
        model.addAttribute("productList2", productList2);

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

        //All categories
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categories", categoryList);

        //All parts
        List<Product> productList = productService.findAllByActive();
        model.addAttribute("productList", productList);

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


    //----------------- CART ---------------//
    @RequestMapping("/view-cart")
    public String viewCart(Model model, Principal principal) {
        model.addAttribute("classActiveViewCart", "active");

        Customer customer = customerService.findByUsername(principal.getName());//get logged in user
        ShoppingCart shoppingCart = customer.getShoppingCart();

        model.addAttribute("shoppingCart", shoppingCart);

        return "/client/view-cart";
    }

    @RequestMapping("/add-to-cart")
    public String addToCart(
            @ModelAttribute("id") Long id
            , @ModelAttribute("quantity") Long quantity
            , Model model
            , Principal principal) {
        model.addAttribute("classActiveViewCart", "active");

        //Find product
        Product product = productService.get(id);

        //Find customer
        Customer customer = customerService.findByUsername(principal.getName());//get logged in user

        //Add item to shopping cart
        shoppingCartService.addItemToCart(product, quantity, customer);

        return "redirect:/part-details?id="+product.getId()+"&addtocart";
    }
    @RequestMapping("/empty-cart")
    public String emptyCart(Model model, Principal principal) {
        model.addAttribute("classActiveViewCart", "active");

        Customer customer = customerService.findByUsername(principal.getName());//get logged in user

        shoppingCartService.emptyShoppingCart(customer);

        model.addAttribute("shoppingCart", customer.getShoppingCart());

        //Set message
        //model.addAttribute("removeCartMessage", "Cart has been empty successfully.");

        return "redirect:/view-cart?emptyCart";
    }

    @RequestMapping(value="/update-cart", method= RequestMethod.POST, params="action=update")
    public String updateCart(
            @ModelAttribute("id") Long id
            , @ModelAttribute("quantity") String quantity
            , Model model
            , Principal principal) {
        model.addAttribute("classActiveViewCart", "active");

        //Check if valid quantity then use it otherwise default value is 1
        Long qty=1L;
        try{
            qty = Long.parseLong(quantity);
        }catch (NumberFormatException ex) {
            qty = 1L;
        }

        //Find product
        Product product = productService.get(id);

        Customer customer = customerService.findByUsername(principal.getName());//get logged in user

        shoppingCartService.updateItemFromCart(product, qty, customer);

        model.addAttribute("shoppingCart", customer.getShoppingCart());

        return "redirect:/view-cart?updateCart";
    }

    @RequestMapping(value="/update-cart", method= RequestMethod.POST, params="action=delete")
    public String removeCart(
            @ModelAttribute("id") Long id
            , Model model
            , Principal principal) {
        model.addAttribute("classActiveViewCart", "active");

        //Find product
        Product product = productService.get(id);

        Customer customer = customerService.findByUsername(principal.getName());//get logged in user

        shoppingCartService.removeItemFromCart(product, customer);

        model.addAttribute("shoppingCart", customer.getShoppingCart());

        return "redirect:/view-cart?removeCart";
    }
    //----------------- END CART ---------------//


    //--------------- END CHECKOUT ---------------------//
    @RequestMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("classActiveCheckout", "active");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        Customer customer = customerService.findByUsername(currentUserName);

        //Get shopping cart
        model.addAttribute("shoppingCart", customer.getShoppingCart());

        //Get countries list
        List<Country> countryList = countryService.findAll();
        model.addAttribute("countries", countryList);

        return "/client/checkout";
    }
    @PostMapping("/checkout")
    public String myAccountSave(@Valid
             @ModelAttribute("shoppingCart") ShoppingCart shoppingCart
            , Principal principal
            , BindingResult result
            , Model model) {
        model.addAttribute("classActiveCheckout", "active");

        //Get countries list
        List<Country> countryList = countryService.findAll();

        //set countries
        model.addAttribute("countries", countryList);

        //Get full object
        ShoppingCart shoppingCart1 = shoppingCartService.findById(shoppingCart.getId());
        shoppingCart1.setShippingMethod(shoppingCart.getShippingMethod());
        shoppingCart1.setPaymentMethod(shoppingCart.getPaymentMethod());
        shoppingCart1.setDescription(shoppingCart.getDescription());

        //set shopping cart
        model.addAttribute("shoppingCart", shoppingCart1);

        //----------- validation ---------//
        if(result.hasErrors()){
            return "/client/checkout";
        }

        if(shoppingCart1 == null || shoppingCart1.getCartItemList() == null){
            //return "/client/checkout?cartEmpty";
            return "redirect:/checkout?cartEmpty";
        }
        //----------- end validation ---------//

        Customer customer = shoppingCart1.getCustomer();

        //save customer information
        customerService.save(customer);

        //Save order
        orderService.saveOrder(shoppingCart1);

        //make cart empty
        shoppingCartService.emptyShoppingCart(customer);

        //redirect to order-history page
        return "redirect:/order-history?success";
    }
    //--------------- END CHECKOUT ---------------------//




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
