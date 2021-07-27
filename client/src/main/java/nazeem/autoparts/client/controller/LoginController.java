package nazeem.autoparts.client.controller;

import nazeem.autoparts.library.model.Country;
import nazeem.autoparts.library.model.Customer;
import nazeem.autoparts.library.service.CountryService;
import nazeem.autoparts.library.service.CustomerService;
import nazeem.autoparts.library.web.dto.CustomerRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CountryService countryService;


    @GetMapping("/login")
    public String login(Model model) {
        CustomerRegistrationDto customerRegistrationDto = new CustomerRegistrationDto();
        model.addAttribute("customerRegistrationDto", customerRegistrationDto);

        return "auth/login";
    }

    @GetMapping("/forget-password")
    public String forgetPassword(Model model) {
        CustomerRegistrationDto customerRegistrationDto = new CustomerRegistrationDto();
        model.addAttribute("customerRegistrationDto", customerRegistrationDto);

        return "auth/forget-password";
    }

    @PostMapping("/forget-password")
    public String forgetPasswordRecovery(@Valid @ModelAttribute("customerRegistrationDto") CustomerRegistrationDto customerRegistrationDto, BindingResult result, Model model) {
        model.addAttribute("customerRegistrationDto", customerRegistrationDto);

        Customer customerExists = customerService.findByUsername(customerRegistrationDto.getUsername());

        //System.out.println("user-->"+userRegistrationDto.getUserName());
        System.out.println("customerExists-->"+customerExists);

        if (customerExists != null) {
            return "redirect:/forget-password?email";
        }
        if(result.hasErrors()){
            return "auth/forget-password";
        }
        //Send email
        return "redirect:/forget-password?success";

    }



    @GetMapping("/register")
    public String register(Model model) {
        CustomerRegistrationDto customerRegistrationDto = new CustomerRegistrationDto();
        model.addAttribute("customerRegistrationDto", customerRegistrationDto);

        //Get countries list
        List<Country> countryList = countryService.findAll();
        model.addAttribute("countries", countryList);

        return "auth/register";
    }
    @PostMapping("/register")
    public String registerUserAccount(@Valid @ModelAttribute("customerRegistrationDto") CustomerRegistrationDto customerRegistrationDto, BindingResult result, Model model) {
        model.addAttribute("customerRegistrationDto", customerRegistrationDto);

        //Get countries list
        List<Country> countryList = countryService.findAll();
        model.addAttribute("countries", countryList);

        Customer customerExists = customerService.findByUsername(customerRegistrationDto.getUsername());

        //System.out.println("user-->"+userRegistrationDto.getUserName());
        System.out.println("customerExists-->"+customerExists);

        if (customerExists != null) {
            return "redirect:/register?email";
        }
        if(result.hasErrors()){
            return "auth/register";
        }
        customerService.save(customerRegistrationDto);

        return "redirect:/register?success";
    }
}