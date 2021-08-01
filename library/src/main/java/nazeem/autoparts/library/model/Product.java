package nazeem.autoparts.library.model;

import javax.persistence.*;

import javax.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @NotNull(message = "Select Category!")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;


    //Make
    @NotNull(message = "Select Make!")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "make_id", nullable = true, referencedColumnName = "make_id")
    private Make make;

    //Model
    @NotNull(message = "Select Model!")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", referencedColumnName = "model_id")
    private Model model;

    //Year
    @Column(name = "year")
    private String year;



    @NotEmpty(message = "Code can't be empty!")
    @Column(name = "code")
    private String code;

    @NotEmpty(message = "Name can't be empty!")
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "cost_price")
    private float costPrice;

    @Column(name = "sale_price")
    private float salePrice;

    @Column(name = "our_price")
    private float ourPrice;

    @Column(name = "stock_qty")
    private Integer stockQty;

    @Column(name = "image1")
    private String image1;

    @Transient
    private MultipartFile image_posted1;

    @Column(name = "image2")
    private String image2;

    @Transient
    private MultipartFile image_posted2;

    @Column(name = "image3")
    private String image3;

    @Transient
    private MultipartFile image_posted3;

    @Column(name = "image4")
    private String image4;

    @Transient
    private MultipartFile image_posted4;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public String getFullImage1Url(){
        if(id != null && image1 !=null) {
            return "/upload/product/" + id + "/" + image1;
        }else {
            return "/upload/no_preview.jpg";
        }
    }
    public String getFullImage2Url(){
        if(id != null && image2 !=null) {
            return "/upload/product/" + id + "/" + image2;
        }else {
            return "/upload/no_preview.jpg";
        }
    }
    public String getFullImage3Url(){
        if(id != null && image3 !=null) {
            return "/upload/product/" + id + "/" + image3;
        }else {
            return "/upload/no_preview.jpg";
        }
    }
    public String getFullImage4Url(){
        if(id != null && image4 !=null) {
            return "/upload/product/" + id + "/" + image4;
        }else {
            return "/upload/no_preview.jpg";
        }
    }
}