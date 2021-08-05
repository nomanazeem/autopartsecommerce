package nazeem.autoparts.library.repository;

/*
    Created By: noman azeem
    Contact: syed.noman.azeem@gmail.com
*/
import nazeem.autoparts.library.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}