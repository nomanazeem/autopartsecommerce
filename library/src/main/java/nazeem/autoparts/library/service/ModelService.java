package nazeem.autoparts.library.service;

import nazeem.autoparts.library.model.Dropdown;
import nazeem.autoparts.library.model.Make;
import nazeem.autoparts.library.model.Model;
import nazeem.autoparts.library.repository.MakeRepository;
import nazeem.autoparts.library.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    public List<Model> findAll() {
        return (List<Model>) modelRepository.findAll();
    }

    public Model findByName(String name) {
        return modelRepository.findByName(name);
    }

    public List<Model> getModels(Long makeId) {
        return modelRepository.getModels(makeId);
    }



    public void save(Model product) {
        modelRepository.save(product);
    }

    public Model get(long  id) {
        return modelRepository.findById(id).get();
    }

    public void delete(long id) {
        modelRepository.deleteById(id);
    }
}