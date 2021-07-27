package nazeem.autoparts.client.controller;

import nazeem.autoparts.library.model.Country;
import nazeem.autoparts.library.model.Customer;
import nazeem.autoparts.library.model.Order;
import nazeem.autoparts.library.service.*;
import nazeem.autoparts.library.web.dto.CustomerRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class MyAccountController {
    @Autowired
    CustomerService customerService;

    @Autowired
    CountryService countryService;

    @Autowired
    OrderService orderService;


    //--------------- MY ACCOUNT ---------------------//
    @RequestMapping("/my-account")
    public String myAccount(Model model) {
        model.addAttribute("classActiveMyAccount", "active");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        //Get logged in customer
        Customer customer = customerService.findByUsername(currentUserName);
        model.addAttribute("customer", customer);

        //Get countries list
        List<Country> countryList = countryService.findAll();
        model.addAttribute("countries", countryList);

        return "/my-account/my-account";
    }

    @PostMapping("/my-account")
    public String myAccountSave(@Valid @ModelAttribute("customer") Customer customer, BindingResult result, Model model) {
        model.addAttribute("classActiveMyAccount", "active");

        //Get countries list
        List<Country> countryList = countryService.findAll();
        model.addAttribute("countries", countryList);

        //Get logged in customer
        model.addAttribute("customer", customer);

        if(result.hasErrors()){
            return "/my-account/my-account";
        }

        customerService.save(customer);

        return "redirect:/my-account?success";
    }
    //--------------- END MY ACCOUNT ---------------------//

    @RequestMapping("/order-history")
    public String orderHistory(Model model, Principal principal) {
        model.addAttribute("classActiveMyAccount", "active");

        Customer customer = customerService.findByUsername(principal.getName());

        //customer orders
        model.addAttribute("orders", customer.getOrders());

        return "/my-account/order-history";
    }
    @RequestMapping("/order-details")
    public String orderDetails(@RequestParam("id") Long id, Model model) {
        model.addAttribute("classActiveMyAccount", "active");

        Order order = orderService.get(id);
        //invalid order id
        if(order == null){
            return "/my-account/order-details?error";
        }

        model.addAttribute("order", order);

        return "/my-account/order-details";
    }

    @RequestMapping("/change-password")
    public String changePassword(Model model) {
        model.addAttribute("classActiveMyAccount", "active");

        return "/my-account/change-password";
    }
}