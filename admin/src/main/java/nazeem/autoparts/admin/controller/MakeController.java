package nazeem.autoparts.admin.controller;

import nazeem.autoparts.library.model.Make;
import nazeem.autoparts.library.service.MakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/make")
public class MakeController {

    @Autowired
    private MakeService makeService;

    private String add_edit_template="/make/add-edit-make";
    private String list_template="/make/list-make";
    private String list_redirect="redirect:/make/list";

    @GetMapping("/add")
    public String addMake(Make make, Model model){
        model.addAttribute("make", make);
        return add_edit_template;
    }

    @GetMapping("/edit/{id}")
    public String editMake(@PathVariable("id") int id, Model model){
        Make make = makeService.get(id);

        model.addAttribute("make", make);

        return add_edit_template;
    }

    @PostMapping("/save")
    public String saveMake(@Valid @ModelAttribute("make") Make make, BindingResult result, Model model){
        model.addAttribute("make", make);

        if(result.hasErrors()){
            return add_edit_template;
        }
        makeService.save(make);

        return list_redirect+"?success";
    }

    @GetMapping("/delete/{id}")
    public String deleteMake(@PathVariable("id") int id, Model model) {
        makeService.delete(id);

        return list_redirect+"?deleted";
    }

    @GetMapping("/list")
    public String listMake(Model model) {
        List<Make> listMake = makeService.findAll();
        model.addAttribute("listMake", listMake);

        return list_template;
    }
}