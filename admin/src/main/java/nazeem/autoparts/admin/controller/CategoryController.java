package nazeem.autoparts.admin.controller;

import javax.validation.Valid;

import nazeem.autoparts.library.model.Category;
import nazeem.autoparts.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    private String add_edit_template="/admin/category/add-edit-category";
    private String list_template="/admin/category/list-category";
    private String list_redirect="redirect:/category/list";

    @GetMapping("/add")
    public String addCategory(Category category, Model model){
        model.addAttribute("category", category);
        return add_edit_template;
    }

    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable("id") int id, Model model){
        Category category = categoryService.get(id);

        model.addAttribute("category", category);

        return add_edit_template;
    }

    @PostMapping("/save")
    public String saveCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model){
        model.addAttribute("category", category);

        if(result.hasErrors()){
            return add_edit_template;
        }
        categoryService.save(category);

        return list_redirect+"?success";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") int id, Model model) {
        categoryService.delete(id);

        return list_redirect+"?deleted";
    }

    @GetMapping("/list")
    public String listCategory(Model model) {
        List<Category> listCategories = categoryService.findAll();
        model.addAttribute("listCategories", listCategories);

        return list_template;
    }
}