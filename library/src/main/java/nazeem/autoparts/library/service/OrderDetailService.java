package nazeem.autoparts.library.service;

import nazeem.autoparts.library.model.OrderDetail;
import nazeem.autoparts.library.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository repo;

    public List<OrderDetail> findAll() {
        return (List<OrderDetail>) repo.findAll();
    }

    public void save(OrderDetail order) {
        repo.save(order);
    }

    public OrderDetail get(long  id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}