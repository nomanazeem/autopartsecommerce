package nazeem.autoparts.library.repository;

import nazeem.autoparts.library.model.Customer;
import nazeem.autoparts.library.model.Product;
import nazeem.autoparts.library.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}