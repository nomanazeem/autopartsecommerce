package nazeem.autoparts.library.service;

import nazeem.autoparts.library.model.User;
import nazeem.autoparts.library.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
    User save(UserRegistrationDto registrationDto);
    User findByUsername(String username);
}
