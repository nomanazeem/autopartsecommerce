package nazeem.autoparts.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="shopping_cart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopping_cart_id")
    private Long id;

    //------------ Mapped Column -----------//
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;
    //-------------------------------------//

    @Column(name = "sub_total")
    private Float subTotal;

    @Column(name = "shipping_total")
    private Float shippingTotal;

    //Default 5 %
    @Column(name = "tax_rate")
    private Float taxRate;

    @Column(name = "tax_total")
    private Float taxTotal;

    @Column(name = "grand_total")
    private Float grandTotal;

    @Column(name = "shipping_method")
    private String shippingMethod;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "description")
    private String description;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shoppingCart")
    private List<CartItem> cartItemList;

    @Override
    public int hashCode() {
        return 42;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                //", customer=" + customer +
                ", subTotal=" + subTotal +
                ", shippingTotal=" + shippingTotal +
                ", taxRate=" + taxRate +
                ", taxTotal=" + taxTotal +
                ", grandTotal=" + grandTotal +
                ", shippingMethod='" + shippingMethod + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", description='" + description + '\'' +
                ", cartItemList=" + cartItemList +
                '}';
    }
}
