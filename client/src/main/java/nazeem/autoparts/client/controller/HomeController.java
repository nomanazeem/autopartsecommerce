package nazeem.autoparts.client.controller;

import nazeem.autoparts.library.model.*;
import nazeem.autoparts.library.service.*;
import nazeem.autoparts.library.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private MakeService makeService;

    @Autowired
    private ModelService modelService;

    @Autowired
    private Utility utility;


    @Autowired
    ProductService productService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("classActiveHome", "home active ");

        List<Make> listMake = makeService.findAll();
        model.addAttribute("listMake", listMake);

        List<nazeem.autoparts.library.model.Model> listModel = modelService.getModels(listMake.get(0).getId());
        model.addAttribute("listModel", listModel);

        List<Integer> listYear = utility.getYears();
        model.addAttribute("listYear", listYear);

        List<Product> productList = productService.findAllByActive();
        model.addAttribute("productList", productList);

        //Getting top 4
        List<Product> productList2 = productService.topMostOrderedProducts(8);
        model.addAttribute("productList2", productList2);

        return "/client/home";
    }



    @RequestMapping("/about-us")
    public String aboutUs(Model model) {
        model.addAttribute("classActivePages", "home active ");

        return "/client/about-us";
    }
    @RequestMapping("/contact-us")
    public String contactUs(Model model) {
        model.addAttribute("classActivePages", "home active ");

        return "/client/contact-us";
    }
    @RequestMapping("/faq")
    public String faq(Model model) {
        model.addAttribute("classActivePages", "home active ");

        return "/client/faq";
    }
    @RequestMapping("/privacy-policy")
    public String privacyPolicy(Model model) {
        model.addAttribute("classActivePages", "home active ");

        return "/client/privacy-policy";
    }
    @RequestMapping("/return-policy")
    public String returnPolicy(Model model) {
        model.addAttribute("classActivePages", "home active ");

        return "/client/return-policy";
    }
    @RequestMapping("/terms-and-conditions")
    public String termsAndConditions(Model model) {
        model.addAttribute("classActivePages", "home active ");

        return "/client/terms-and-conditions";
    }

    @RequestMapping(value = "/models")
    @ResponseBody
    public List<Dropdown> getModels(@RequestParam Long make) {
        List<nazeem.autoparts.library.model.Model> modelList = modelService.getModels(make);
        List<Dropdown> dropdownList=new ArrayList<>();
        for (nazeem.autoparts.library.model.Model model: modelList) {
            dropdownList.add(new Dropdown(model.getId(), model.getName()));
        }
        return dropdownList;
    }

    // Added to test 500 page
    @RequestMapping(path = "/tigger-error", produces = MediaType.APPLICATION_JSON_VALUE)
    public void error500() throws Exception {
        throw new Exception();
    }

}
