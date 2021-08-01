package nazeem.autoparts.client.controller;

import nazeem.autoparts.library.model.Category;
import nazeem.autoparts.library.model.Dropdown;
import nazeem.autoparts.library.model.Make;
import nazeem.autoparts.library.service.CategoryService;
import nazeem.autoparts.library.service.MakeService;
import nazeem.autoparts.library.service.ModelService;
import nazeem.autoparts.library.service.ProductService;
import nazeem.autoparts.library.model.Product;
import nazeem.autoparts.library.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PartController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private MakeService makeService;

    @Autowired
    private ModelService modelService;

    @Autowired
    private Utility utility;

    @RequestMapping("/category")
    public String category(@RequestParam("id") Optional<Long> id, Model model) {
        model.addAttribute("classActiveCategory", "active");

        //Get all categories
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categories", categoryList);

        //Get selected product & category
        List<Product> productList;
        Category category;
        if (id.isPresent()) {
            productList = productService.findAllByCategoryId(id.get());
            category = categoryService.get(id.get());
        } else {
            productList = productService.findAllByActive();
            category = new Category();
            category.setName("All Categories");
        }


        model.addAttribute("productList", productList);
        model.addAttribute("category", category);

        return "/client/category";
    }

    @RequestMapping(value = "/part-search", method = RequestMethod.GET)
    public String partSearch(Model model) {
        model.addAttribute("classActivePartSearch", "active");

        //All categories
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categories", categoryList);

        List<Make> listMake = makeService.findAll();
        model.addAttribute("listMake", listMake);

        List<nazeem.autoparts.library.model.Model> listModel = modelService.getModels(listMake.get(0).getId());
        model.addAttribute("listModel", listModel);

        List<Integer> listYear = utility.getYears();
        model.addAttribute("listYear", listYear);

        //All parts
        List<Product> productList = productService.searchResults("", "", "", "", "");
        model.addAttribute("productList", productList);



        model.addAttribute("search", new Product());

        return "/client/part-search";
    }

    //    //public String partSearch(@RequestParam("search") Optional<String> search, @RequestParam("category_id") Optional<String> categoryId, Model model) {

    @RequestMapping(value = "/part-search", method = RequestMethod.POST)
    public String partSearch2(
            @ModelAttribute("product") Product product
            , Model model) {
        model.addAttribute("classActivePartSearch", "active");

        //All categories
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categories", categoryList);

        model.addAttribute("search", product);



        List<Make> listMake = makeService.findAll();
        model.addAttribute("listMake", listMake);

        List<nazeem.autoparts.library.model.Model> listModel = modelService.getModels(listMake.get(0).getId());

        if(product.getMake() != null) {
            listModel = modelService.getModels(product.getMake().getId());
        }
        model.addAttribute("listModel", listModel);

        List<Integer> listYear = utility.getYears();
        model.addAttribute("listYear", listYear);

        String categoryId="";
        if(product.getCategory()!=null){
            categoryId = product.getCategory().getId().toString();
        }
        String makeId="";
        if(product.getMake()!=null){
            makeId = product.getMake().getId().toString();
        }
        String modelId="";
        if(product.getModel()!=null){
            modelId = product.getModel().getId().toString();
        }
        String year="";
        if(product.getYear()!=null){
            year = product.getYear();
        }


        //All parts
        List<Product> productList = productService.searchResults(
                product.getName()
                , categoryId
                , makeId
                , modelId
                , year);

        model.addAttribute("productList", productList);

        return "/client/part-search";
    }

    @RequestMapping("/part-details")
    public String partDetails(@RequestParam("id") Long id, Model model) {
        model.addAttribute("classActivePartSearch", "active");
        model.addAttribute("search", new Product());

        //All categories
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categories", categoryList);

        List<Make> listMake = makeService.findAll();
        model.addAttribute("listMake", listMake);

        List<nazeem.autoparts.library.model.Model> listModel = modelService.getModels(listMake.get(0).getId());
        model.addAttribute("listModel", listModel);

        List<Integer> listYear = utility.getYears();
        model.addAttribute("listYear", listYear);

        try {
            //Get product
            Product product = productService.get(id);
            model.addAttribute("product", product);
        }catch (Exception ex){
            model.addAttribute("error", ex.getMessage());
            return "/client/part-details";
        }
        return "/client/part-details";
    }


}
