package nazeem.autoparts.library.model;

import javax.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "category_id")
    @NotNull(message = "Select Category!")
    private Integer categoryId;

    @NotEmpty(message = "Code can't be empty!")
    @Column(name = "code")
    private String code;

    @NotEmpty(message = "Name can't be empty!")
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "cost_price")
    private float cost_price;

    @Column(name = "sale_price")
    private float sale_price;

    @Column(name = "our_price")
    private float our_price;

    @Column(name = "stock_qty")
    private Integer stock_qty;

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
    private boolean is_active;

    @Column(name = "is_deleted")
    private boolean is_deleted;

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getCost_price() {
        return cost_price;
    }

    public void setCost_price(float cost_price) {
        this.cost_price = cost_price;
    }

    public float getSale_price() {
        return sale_price;
    }

    public void setSale_price(float sale_price) {
        this.sale_price = sale_price;
    }

    public float getOur_price() {
        return our_price;
    }

    public void setOur_price(float our_price) {
        this.our_price = our_price;
    }

    public Integer getStock_qty() {
        return stock_qty;
    }

    public void setStock_qty(Integer stock_qty) {
        this.stock_qty = stock_qty;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public MultipartFile getImage_posted1() {
        return image_posted1;
    }

    public void setImage_posted1(MultipartFile image_posted1) {
        this.image_posted1 = image_posted1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public MultipartFile getImage_posted2() {
        return image_posted2;
    }

    public void setImage_posted2(MultipartFile image_posted2) {
        this.image_posted2 = image_posted2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public MultipartFile getImage_posted3() {
        return image_posted3;
    }

    public void setImage_posted3(MultipartFile image_posted3) {
        this.image_posted3 = image_posted3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public MultipartFile getImage_posted4() {
        return image_posted4;
    }

    public void setImage_posted4(MultipartFile image_posted4) {
        this.image_posted4 = image_posted4;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
}