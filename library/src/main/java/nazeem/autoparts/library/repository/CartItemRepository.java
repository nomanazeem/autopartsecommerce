package nazeem.autoparts.library.repository;

import nazeem.autoparts.library.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}