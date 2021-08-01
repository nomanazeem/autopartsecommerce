package nazeem.autoparts.library.repository;

import nazeem.autoparts.library.model.Make;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MakeRepository extends JpaRepository<Make, Long> {
    Make findByName(String name);
}