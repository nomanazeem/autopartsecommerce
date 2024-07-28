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
@Table(name="year")
public class Year {
    public Year(String name){
        this.name =name;
        this.isDeleted=false;
        this.isDeleted=true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "year_id")
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Name can't be empty!")
    private String name;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "is_active")
    private Boolean isActive;
    @Override
    public String toString() {
        return "Year{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", is_deleted=" + isDeleted +
                ", is_active=" + isActive +
                '}';
    }

    @Override
    public int hashCode() {
        return 42;
    }
}