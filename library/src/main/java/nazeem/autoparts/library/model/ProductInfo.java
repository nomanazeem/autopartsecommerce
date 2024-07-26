package nazeem.autoparts.library.model;

/*
    Created By: noman azeem
    Contact: syed.noman.azeem@gmail.com
*/

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo {
    @JsonProperty("product_id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;


    @JsonProperty("our_price")
    private float ourPrice;


    @JsonProperty("image1")
    private String image1;

    @JsonProperty("image2")
    private String image2;

    public String getFullImage1Url(){
        if(id != null && image1 !=null) {
            return "/upload/product/" + image1;
        }else {
            return "/upload/no_preview.jpg";
        }
    }
    public String getFullImage2Url(){
        if(id != null && image2 !=null) {
            return "/upload/product/" + image2;
        }else {
            return "/upload/no_preview.jpg";
        }
    }
}