package nazeem.autoparts.library.repository;

/*
    Created By: noman azeem
    Contact: syed.noman.azeem@gmail.com
*/
import nazeem.autoparts.library.model.Make;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MakeRepository extends JpaRepository<Make, Long> {
    Make findByName(String name);
}