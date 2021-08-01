package nazeem.autoparts.client.controller;

import nazeem.autoparts.library.model.*;
import nazeem.autoparts.library.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class ShoppingCartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private EmailService emailService;

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
            , HttpServletRequest request
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
        Order newOrder= orderService.saveOrder(shoppingCart1);

        //make cart empty
        shoppingCartService.emptyShoppingCart(customer);

        String appUrl = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();

        //Send email
        emailService.orderCreation(appUrl, newOrder);


        //redirect to order-history page
        return "redirect:/order-history?success";
    }
    //--------------- END CHECKOUT ---------------------//


}
