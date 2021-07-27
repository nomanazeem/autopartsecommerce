package nazeem.autoparts.library.service;

import nazeem.autoparts.library.model.Country;
import nazeem.autoparts.library.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CountryService {

    @Autowired
    private CountryRepository repo;

    public List<Country> findAll() {
        return (List<Country>) repo.findAll();
    }

    public Country findByName(String name) {
        return repo.findByName(name);
    }

    public void save(Country product) {
        repo.save(product);
    }

    public Country get(long  id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}