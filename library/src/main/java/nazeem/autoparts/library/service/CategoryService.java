package nazeem.autoparts.library.service;

import nazeem.autoparts.library.model.Category;
import nazeem.autoparts.library.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    public List<Category> findAll() {
        return (List<Category>) repo.findAll();
    }

    public void save(Category product) {
        repo.save(product);
    }

    public Category get(long  id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}