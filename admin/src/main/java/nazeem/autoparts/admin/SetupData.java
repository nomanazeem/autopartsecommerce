package nazeem.autoparts.admin;

import nazeem.autoparts.library.model.*;
import nazeem.autoparts.library.repository.*;
import nazeem.autoparts.library.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class SetupData implements ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup = false;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private MakeRepository makeRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (alreadySetup)
            return;

        //Setup roles
        createRoleIfNotFound("ADMIN");
        createRoleIfNotFound("CUSTOMER");

        //Setup categories
        createCategoryIfNotFound(new Category("Lighting", "Lighting Desc"));
        createCategoryIfNotFound(new Category("Oil Fluids", "Oil Fluids Desc"));
        createCategoryIfNotFound(new Category("Tools & Equipment", "Tools & Equipment Desc"));
        createCategoryIfNotFound(new Category("Wheels & Tires", "Wheels & Tires Desc"));
        createCategoryIfNotFound(new Category("Car Parts", "Car Parts Desc"));
        createCategoryIfNotFound(new Category("Body Interior", "Body Interior Desc"));
        createCategoryIfNotFound(new Category("Body Exterior", "Body Exterior Desc"));
        createCategoryIfNotFound(new Category("Engine", "Engine Desc"));

        //Setup Countries
        createCountryIfNotFound(new Country("PK", "Pakistan"));
        createCountryIfNotFound(new Country("USA", "United States"));
        createCountryIfNotFound(new Country("UK", "United Kingdom"));
        createCountryIfNotFound(new Country("RSA", "Russia"));
        createCountryIfNotFound(new Country("CHI", "China"));

        //Setup Make
        Make allMake = createMakeIfNotFound(new Make("All Make"));

        Make acura = createMakeIfNotFound(new Make("Acura"));

        Make bmw = createMakeIfNotFound(new Make("BMW"));

        Make gmc = createMakeIfNotFound(new Make("GMC"));

        Make honda = createMakeIfNotFound(new Make("Honda"));

        Make kawasaky = createMakeIfNotFound(new Make("Kawasaky"));

        Make lexus = createMakeIfNotFound(new Make("Lexus"));

        Make mazda = createMakeIfNotFound(new Make("Mazda"));

        Make mercedes = createMakeIfNotFound(new Make("Mercedes"));

        Make toyota = createMakeIfNotFound(new Make("Toyota"));

        Make yamaha = createMakeIfNotFound(new Make("Yamaha"));

        //Setup model
        createModelIfNotFound(new Model("All Model", allMake));


        createModelIfNotFound(new Model("2D Model", acura));
        createModelIfNotFound(new Model("3D Model", acura));

        createModelIfNotFound(new Model("Excel Model", bmw));
        createModelIfNotFound(new Model("2D Model", bmw));
        createModelIfNotFound(new Model("3 Series", bmw));

        createModelIfNotFound(new Model("4D Model", gmc));

        createModelIfNotFound(new Model("2D Model", honda));
        createModelIfNotFound(new Model("3D Model", honda));
        createModelIfNotFound(new Model("Excel Model", honda));

        createModelIfNotFound(new Model("2D Model", kawasaky));

        createModelIfNotFound(new Model("3D Model", lexus));

        createModelIfNotFound(new Model("2D Model", mazda));
        createModelIfNotFound(new Model("3D Model", mazda));

        createModelIfNotFound(new Model("3D Model", mercedes));
        createModelIfNotFound(new Model("4D Model", mercedes));

        createModelIfNotFound(new Model("2D Model", toyota));
        createModelIfNotFound(new Model("3D Model", toyota));
        createModelIfNotFound(new Model("4D Model", toyota));
        createModelIfNotFound(new Model("Excel Model", toyota));

        createModelIfNotFound(new Model("3D Model", yamaha));
    }

    @Transactional
    Role createRoleIfNotFound(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);

            //return saved object
            role = roleRepository.findByName(name);
        }
        return role;
    }

    @Transactional
    Category createCategoryIfNotFound(Category category) {
        Category category1 = categoryRepository.findByName(category.getName());
        if (category1 == null) {
            categoryRepository.save(category);

            //return saved object
            category1 = categoryRepository.findByName(category.getName());
        }
        return category1;
    }

    @Transactional
    Country createCountryIfNotFound(Country country) {
        Country country1 = countryRepository.findByName(country.getName());
        if (country1 == null) {
            countryRepository.save(country);

            //return saved object
            country1 = countryRepository.findByName(country.getName());
        }
        return country1;
    }

    @Transactional
    Make createMakeIfNotFound(Make make) {
        Make make1 = makeRepository.findByName(make.getName());
        if (make1 == null) {
            makeRepository.save(make);
            //return saved object
            make1 = makeRepository.findByName(make.getName());
        }

        return make1;
    }

    @Transactional
    Model createModelIfNotFound(Model model) {
        Model model1 = modelRepository.findByName2(model.getName(), model.getMake().getId());
        if (model1 == null) {
            modelRepository.save(model);

            //return saved object
            model1 = modelRepository.findByName2(model.getName(), model.getMake().getId());
        }
        return model1;
    }
}
