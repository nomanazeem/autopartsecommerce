package nazeem.autoparts.library.repository;

/*
    Created By: noman azeem
    Contact: syed.noman.azeem@gmail.com
*/
import nazeem.autoparts.library.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}