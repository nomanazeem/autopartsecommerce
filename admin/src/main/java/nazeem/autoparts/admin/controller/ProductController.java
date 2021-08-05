package nazeem.autoparts.admin.controller;

/*
    Created By: noman azeem
    Contact: syed.noman.azeem@gmail.com
*/
import javax.validation.Valid;

import nazeem.autoparts.library.model.*;
import nazeem.autoparts.library.service.CategoryService;
import nazeem.autoparts.library.service.MakeService;
import nazeem.autoparts.library.service.ModelService;
import nazeem.autoparts.library.service.ProductService;
import nazeem.autoparts.library.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MakeService makeService;

    @Autowired
    private ModelService modelService;

    @Autowired
    private Utility utility;


    private String add_edit_template="/product/add-edit-product";
    private String list_template="/product/list-product";
    private String list_redirect="redirect:/product/list";


    @GetMapping("/add")
    public String addProduct(Product product, org.springframework.ui.Model model){
        model.addAttribute("product", product);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        List<Make> listMake = makeService.findAll();
        model.addAttribute("listMake", listMake);

        List<Model> listModel = modelService.getModels(listMake.get(0).getId());
        model.addAttribute("listModel", listModel);

        List<Integer> listYear = utility.getYears();
        model.addAttribute("listYear", listYear);

        return add_edit_template;
    }


    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") long id, org.springframework.ui.Model model){
        Product product = productService.get(id);
        model.addAttribute("product", product);

        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        List<Make> listMake = makeService.findAll();
        model.addAttribute("listMake", listMake);

        //get models if make is not empty
        List<Model> listModel = modelService.getModels(listMake.get(0).getId());
        if(product.getMake() !=null) {
            listModel = modelService.getModels(product.getMake().getId());
        }
        model.addAttribute("listModel", listModel);


        List<Integer> listYear = utility.getYears();
        model.addAttribute("listYear", listYear);

        return add_edit_template;
    }

    @PostMapping("/save")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, org.springframework.ui.Model model){
        model.addAttribute("product", product);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        List<Make> listMake = makeService.findAll();
        model.addAttribute("listMake", listMake);

        List<Integer> listYear = utility.getYears();
        model.addAttribute("listYear", listYear);

        if(result.hasErrors()){
            return add_edit_template;
        }
        //if(product.)

        productService.save(product);
        return list_redirect+"?success";
    }



    /*@GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id, org.springframework.ui.Model model) {
        productService.delete(id);

        return list_redirect+"?deleted";
    }*/

    @GetMapping("/list")
    public String listProduct(org.springframework.ui.Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        List<Product> listProducts = productService.findAll();
        model.addAttribute("listProducts", listProducts);

        return list_template;
    }

    @RequestMapping(value = "/models")
    @ResponseBody
    public List<Dropdown> getModels(@RequestParam Long make) {
        List<Model> modelList = modelService.getModels(make);
        List<Dropdown> dropdownList=new ArrayList<>();
        for (Model model: modelList) {
            dropdownList.add(new Dropdown(model.getId(), model.getName()));
        }
        return dropdownList;
    }

}