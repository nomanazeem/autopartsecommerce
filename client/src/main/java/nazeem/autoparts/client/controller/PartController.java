package nazeem.autoparts.client.controller;

import nazeem.autoparts.library.model.Category;
import nazeem.autoparts.library.service.CategoryService;
import nazeem.autoparts.library.service.ProductService;
import nazeem.autoparts.library.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
public class PartController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    private ProductService productService;

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

        //All parts
        List<Product> productList = productService.searchResults("", "");
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

        String categoryId="";
        if(product.getCategory()!=null){
            categoryId = product.getCategory().getId().toString();
        }
/*


        String strSearch = "";
        String sCategoryId= "";
        if(search.isPresent())
        {
            strSearch = search.get();
        }
        if(categoryId.isPresent()){
            sCategoryId=categoryId.get();
        }
*/

        //All parts
        List<Product> productList = productService.searchResults(
                product.getName()
                , categoryId);
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

        //Get product
        Product product = productService.get(id);
        model.addAttribute("product", product);

        return "/client/part-details";
    }
}
