package nazeem.autoparts.admin;

import nazeem.autoparts.library.model.Category;
import nazeem.autoparts.library.model.Country;
import nazeem.autoparts.library.model.Role;
import nazeem.autoparts.library.repository.CategoryRepository;
import nazeem.autoparts.library.repository.CountryRepository;
import nazeem.autoparts.library.repository.RoleRepository;
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
    }

    @Transactional
    Role createRoleIfNotFound(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }

    @Transactional
    Category createCategoryIfNotFound(Category category) {
        Category category1 = categoryRepository.findByName(category.getName());
        if (category1 == null) {
            categoryRepository.save(category);
        }
        return category;
    }

    @Transactional
    Country createCountryIfNotFound(Country country) {
        Country country1 = countryRepository.findByName(country.getName());
        if (country1 == null) {
            countryRepository.save(country);
        }
        return country;
    }
}
