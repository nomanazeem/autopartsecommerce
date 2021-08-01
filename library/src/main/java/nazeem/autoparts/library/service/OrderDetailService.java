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
    private OrderDetailRepository orderDetailRepository;

    public List<OrderDetail> findAll() {
        return (List<OrderDetail>) orderDetailRepository.findAll();
    }

    public void save(OrderDetail order) {
        orderDetailRepository.save(order);
    }

    public OrderDetail get(long  id) {
        return orderDetailRepository.findById(id).get();
    }

    public void delete(long id) {
        orderDetailRepository.deleteById(id);
    }
}