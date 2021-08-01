package nazeem.autoparts.library.model;

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
