package nazeem.autoparts.library.repository;

/*
    Created By: noman azeem
    Contact: syed.noman.azeem@gmail.com
*/
import nazeem.autoparts.library.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}