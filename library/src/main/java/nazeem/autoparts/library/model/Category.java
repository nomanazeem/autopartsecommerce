package nazeem.autoparts.library.model;

/*
    Created By: noman azeem
    Contact: syed.noman.azeem@gmail.com
*/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="category")
public class Category {
    public Category(String name, String description){
        this.name =name;
        this.description = description;
        this.isActive=true;
        this.isDeleted=false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Name can't be empty!")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

}