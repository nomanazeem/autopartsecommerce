package nazeem.autoparts.library.model;

/*
    Created By: noman azeem
    Contact: syed.noman.azeem@gmail.com
*/
import lombok.Data;

@Data
public class Dropdown {
    public Dropdown(Long id, String name){
        this.id=id;
        this.name = name;
    }
    private Long id;
    private String name;
}
