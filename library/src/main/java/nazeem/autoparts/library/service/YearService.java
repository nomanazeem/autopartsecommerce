package nazeem.autoparts.library.service;

/*
    Created By: noman azeem
    Contact: syed.noman.azeem@gmail.com
*/
import nazeem.autoparts.library.model.Year;
import nazeem.autoparts.library.repository.YearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class YearService {

    @Autowired
    private YearRepository yearRepository;

    public List<Year> findAll() {
        return (List<Year>) yearRepository.findAll();
    }

    public Year findByName(String name) {
        return yearRepository.findByName(name);
    }

    public void save(Year product) {
        yearRepository.save(product);
    }

    public Year get(long  id) {
        return yearRepository.findById(id).get();
    }

    public void delete(long id) {
        yearRepository.deleteById(id);
    }
}