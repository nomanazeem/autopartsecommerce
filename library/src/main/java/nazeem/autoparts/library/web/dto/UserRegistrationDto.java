package nazeem.autoparts.library.web.dto;

/*
    Created By: noman azeem
    Contact: syed.noman.azeem@gmail.com
*/
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {

	@NotEmpty(message = "First name can't be empty!")
	private String firstName;

	@NotEmpty(message = "Last name can't be empty!")
	private String lastName;

	@Email(message = "*Please provide a valid Email")
	private String username;

	@Length(min = 5, message = "*Your password must have at least 5 characters")
	@NotEmpty(message = "*Please provide your password")
	private String password;
}
