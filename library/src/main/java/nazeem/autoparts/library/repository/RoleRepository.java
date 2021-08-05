package nazeem.autoparts.library.repository;

/*
    Created By: noman azeem
    Contact: syed.noman.azeem@gmail.com
*/
import nazeem.autoparts.library.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}