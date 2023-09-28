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
public class Tulip extends Flower{
    private String color;

    public void set_color(String color){
        this.color = color;
    }
}
