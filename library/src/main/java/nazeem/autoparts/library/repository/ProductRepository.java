package nazeem.autoparts.library.repository;

/*
    Created By: noman azeem
    Contact: syed.noman.azeem@gmail.com
*/
import nazeem.autoparts.library.model.Product;
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

    /*
    @Query(value="select * from product p " +
            "where 1=1 " +
            "and is_active  = 1 and is_deleted = 0 " +
            "and (length(?1) = 0 or p.name like concat('%', ?1, '%')) " +
            "and (length(?2) = 0 or category_id= ?2) "+
            "and (length(?3) = 0 or make_id= ?3) "+
            "and (length(?4) = 0 or model_id= ?4) "+
            //"and (?3=1  or make_id= ?3) "+
            //"and (?4=1 or model_id= ?4) "+
            "and (length(?5) =0 or year= ?5) "
            , nativeQuery = true)

     */

    @Query(value= "SELECT p.*\n" +
            ", m.name as make, p.`year`, c.name as category\n" +
            "FROM product p\n" +
            "inner join make m on m.make_id =p.make_id\n" +
            "inner join category c on c.category_id = p.category_id\n"+
            "where 1=1 \n" +
            "and p.is_active = 1 and p.is_deleted = 0 \n" +
            //"and (length(?1) = 0 or p.name like concat('%', ?1, '%')) \n" +
            "and (length(?1) = 0 or MATCH(p.name) AGAINST(?1 IN NATURAL LANGUAGE MODE) ) \n" +//ignoring keyword
            "and (length(?2) = 0 or c.name= ?2) \n"+
            "and (length(?3) = 0 or m.name= ?3) \n"+
            "and (length(?4) = 0 or p.model_id= ?4) \n"+
            "and (length(?5) = 0 or p.year= ?5) ", nativeQuery = true)
    List<Product> searchProduct2(String keyword, String categoryId, String makeId, String modelId, String year);


    @Query(value="select p.* from Product p where 1=1 and is_active=1 and is_deleted=0 limit 8", nativeQuery = true)
    public List<Product> findAllByActive();

    @Query("select p from Product p where 1=1 and is_active=1 and is_deleted=0 and category_id=?1")
    public List<Product> findAllByCategoryId(Long categoryId);

    @Query(value = "SELECT p.*, sum(quantity) as qtys\n" +
            "FROM order_detail o\n" +
            "inner join product p on p.product_id = o.product_id \n" +
            "where 1=1 " +
            "and o.is_deleted=0\n" +
            "group by p.product_id\n" +
            "order by sum(quantity) desc\n" +
            "limit ?1", nativeQuery = true )
    public List<Product> topMostOrderedProducts(Integer top);

}