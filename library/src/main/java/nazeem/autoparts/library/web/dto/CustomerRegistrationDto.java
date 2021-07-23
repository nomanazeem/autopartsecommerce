package nazeem.autoparts.library.web.dto;


public class CustomerRegistrationDto {

	//@NotEmpty(message = "First name can't be empty!")
	private String firstName;

	//@NotEmpty(message = "Last name can't be empty!")
	private String lastName;

	//@NotEmpty(message = "Email name can't be empty!")
	//@Email(message = "*Please provide a valid Email")
	private String userName;

	//@Length(min = 5, message = "*Your password must have at least 5 characters")
	//@NotEmpty(message = "*Please provide your password")
	private String password;

	private String phone;


	public CustomerRegistrationDto(){
	}

	public CustomerRegistrationDto(String firstName, String lastName, String userName, String password, String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.phone = phone;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String email) {
		this.userName = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}



	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
