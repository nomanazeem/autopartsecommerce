package nazeem.autoparts.library.repository;
import nazeem.autoparts.library.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
   /* @Query("select p from Product p " +
            //"join p.productType pt " +
            "where 1=1" +
            "and ( upper(p.name) like concat('%', upper(?1), '%') " +
            "       or upper(p.brand) like concat('%', upper(?1), '%') " +
            "       or upper(p.madein) like concat('%', upper(?1), '%')" +
            //"       or upper(pt.name) like concat('%', upper(?1), '%')" +
            ")")
    List<Product> searchProduct(String criteria);*/

    @Query("select p from Product p " +
            "where 1=1 and is_deleted=0" +
            "and ( upper(p.name) like concat('%', upper(?1), '%') " +
            "       or upper(p.description) like concat('%', upper(?1), '%') " +
            ")")
    List<Product> searchProduct(String criteria);

    @Query("select p from Product p where p.is_active = 1")
    public List<Product> findAllByActive();

    @Query("select p from Product p where p.is_active = 1 and category_id=?1")
    public List<Product> findAllByCategoryId(Long categoryId);
}