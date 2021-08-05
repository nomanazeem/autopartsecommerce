package nazeem.autoparts.library.repository;

/*
    Created By: noman azeem
    Contact: syed.noman.azeem@gmail.com
*/
import nazeem.autoparts.library.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}