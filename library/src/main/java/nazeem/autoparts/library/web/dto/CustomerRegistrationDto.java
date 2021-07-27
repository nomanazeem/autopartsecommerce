package nazeem.autoparts.library.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nazeem.autoparts.library.model.Customer;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegistrationDto {
	@NotEmpty(message = "First name can't be empty!")
	private String firstName;

	@NotEmpty(message = "Last name can't be empty!")
	private String lastName;

	@NotEmpty(message = "Email can't be empty!")
	@Email(message = "*Please provide a valid Email")
	private String username;

	@Length(min = 5, message = "*Your password must have at least 5 characters")
	@NotEmpty(message = "*Please provide your password")
	private String password;

	@NotEmpty(message = "*Please provide your phone")
	private String phone;

	@NotEmpty(message = "Confirm Password is mandatory")
	private String confirm;

	private String company;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String country;
	private String postalCode;
}
