package nazeem.autoparts.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="model")
public class Model {
    public Model(String name, Make make){
        this.name =name;
        this.make = make;
        this.isDeleted=false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private Long id;

    @NotNull(message = "Select Make!")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "make_id", referencedColumnName = "make_id")
    private Make make;

    @Column(name = "name")
    @NotEmpty(message = "Name can't be empty!")
    private String name;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", make=" + make +
                ", name='" + name + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }

    @Override
    public int hashCode() {
        return 42;
    }
}