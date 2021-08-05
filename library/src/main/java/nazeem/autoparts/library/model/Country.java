package nazeem.autoparts.library.model;

/*
    Created By: noman azeem
    Contact: syed.noman.azeem@gmail.com
*/
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="country")
public class Country {
    public Country(String code, String name){
        this.code =code;
        this.name =name;
        this.isDeleted=false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }

    @Override
    public int hashCode() {
        return 42;
    }
}
