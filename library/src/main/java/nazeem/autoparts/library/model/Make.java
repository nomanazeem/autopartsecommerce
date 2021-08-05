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
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="make")
public class Make {
    public Make(String name){
        this.name =name;
        this.isDeleted=false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "make_id")
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Name can't be empty!")
    private String name;

    @Column(name = "is_deleted")
    private Boolean isDeleted;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "make")
    private List<Model> modelList;

    @Override
    public String toString() {
        return "Make{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isDeleted=" + isDeleted +
                //", modelList=" + modelList +
                '}';
    }

    @Override
    public int hashCode() {
        return 42;
    }
}