package nazeem.autoparts.client.controller;

import nazeem.autoparts.library.model.Category;
import nazeem.autoparts.library.model.Make;
import nazeem.autoparts.library.model.Year;
import nazeem.autoparts.library.service.CategoryService;
import nazeem.autoparts.library.service.MakeService;
import nazeem.autoparts.library.service.ModelService;
import nazeem.autoparts.library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
    @Autowired
    private MakeService makeService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    private ModelService modelService;

    @GetMapping("/makes")
    public List<Make> makes(){
        //return makeService.findAll();
        return Arrays.asList(new Make());
    }
    @GetMapping("/categories")
    public List<Category> categories(){
        //return categoryService.findAll();
        return Arrays.asList(new Category());
    }
    @GetMapping("/years")
    public List<Year> years(){
        List<Year> years=new ArrayList<>();
        for(Long year=1999L; year<= 2024; year++){
            Year yearObj = new Year();
            yearObj.setId(year);
            yearObj.setName(year.toString());
            years.add(yearObj);
        }
        return years;
    }
}
