package nazeem.autoparts.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @NotEmpty(message = "First name can't be empty!")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Last name can't be empty!")
    @Column(name = "last_name")
    private String lastName;

    public String getFullName(){
        return firstName +" "+lastName;
    }


    @Column(name = "username")
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String username;

    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;

    @Column(name = "phone")
    private String phone;


    //--------Address information----------//
    @Column(name = "company")
    private String company;

    @NotNull(message = "Enter address 1!")
    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @NotNull(message = "Enter city!")
    @Column(name = "city")
    private String city;

    @NotNull(message = "Enter state!")
    @Column(name = "state")
    private String state;

    @NotNull(message = "Enter postal code!")
    @Column(name = "postal_code")
    private String postalCode;

    @NotNull(message = "Select Country!")
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
    private Country country;
    //---------------- end address -------------//



    @Column(name = "is_deleted")
    private Boolean isDeleted;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "customer_roles",
            joinColumns = @JoinColumn(
                    name = "customer_id", referencedColumnName = "customer_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "role_id"))
    private Collection <Role> roles;

    @OneToOne(mappedBy = "customer")
    private ShoppingCart shoppingCart;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;


    @Override
    public int hashCode() {
        return 42;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", company='" + company + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country=" + country +
                ", isDeleted=" + isDeleted +
                ", roles=" + roles +
                //", shoppingCart=" + shoppingCart +
                //", orders=" + orders +
                '}';
    }
}