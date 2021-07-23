package nazeem.autoparts.client.controller;

import nazeem.autoparts.library.service.CustomerService;
import nazeem.autoparts.library.web.dto.CustomerRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class LoginController {

    //@Autowired
    private CustomerService customerService;


    @GetMapping("/login")
    public String login(Model model) {
        return "auth/login";
    }

    @GetMapping("/forget-password")
    public String forgetPassword(Model model) {
        return "auth/forget-password";
    }

    @GetMapping("/register")
    public String register(Model model) {
        CustomerRegistrationDto customerRegistrationDto = new CustomerRegistrationDto();
        model.addAttribute("customerRegistrationDto", customerRegistrationDto);

        return "auth/register";
    }
    @PostMapping("/register")
    public String registerUserAccount(@Valid @ModelAttribute("customerRegistrationDto") CustomerRegistrationDto customerRegistrationDto, BindingResult result, Model model) {
        model.addAttribute("clientRegistrationDto", customerRegistrationDto);

        /*Client userExists = clientService.findByUsername(clientRegistrationDto.getUserName());

        //System.out.println("user-->"+userRegistrationDto.getUserName());
        //System.out.println("userExists-->"+userExists);

        if (userExists != null) {
            return "redirect:/register?username";
        }
        if(result.hasErrors()){
            return "admin/auth/register";
        }
        clientService.save(clientRegistrationDto);
        return "redirect:/register?success";*/

        return "redirect:/register?success";
    }

}
