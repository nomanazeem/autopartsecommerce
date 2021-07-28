package nazeem.autoparts.library.repository;
import nazeem.autoparts.library.model.Product;
import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p " +
            "where 1=1 and is_deleted = 0 " +
            "and ( upper(p.name) like concat('%', upper(?1), '%') " +
            "       or upper(p.description) like concat('%', upper(?1), '%') " +
            ")")
    List<Product> searchProduct(String criteria);

    @Query(value="select * from product p " +
            "where 1=1 " +
            "and is_active = 1 " +
            "and (length(?1) =0 or p.name like concat('%', ?1, '%')) " +
            "and (length(?2) =0 or category_id= ?2)"
            , nativeQuery = true)
    List<Product> searchProduct2(String keyword, String categoryId);


    @Query("select p from Product p where 1=1 and is_active=1")
    public List<Product> findAllByActive();

    @Query("select p from Product p where 1=1 and is_active=1 and category_id=?1")
    public List<Product> findAllByCategoryId(Long categoryId);

    @Query(value = "SELECT p.*, sum(quantity) as qtys\n" +
            "FROM order_detail o\n" +
            "inner join product p on p.product_id = o.product_id \n" +
            "where o.is_deleted=0\n" +
            "group by p.product_id\n" +
            "order by sum(quantity) desc\n" +
            "limit ?1", nativeQuery = true )
    public List<Product> topMostOrderedProducts(Integer top);

}