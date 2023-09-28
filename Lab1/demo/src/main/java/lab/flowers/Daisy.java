package lab.flowers;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "daisy_db")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class Daisy extends Flower{
    private String flower_diameter;

    public void set_flower_diameter(String D){
        this.flower_diameter = D;
    }
}
