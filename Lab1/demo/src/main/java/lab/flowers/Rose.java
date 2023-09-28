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
public class Rose extends Flower{
    private String spike_prop;

    public void set_spike_prop(String prop){
        this.spike_prop = prop;
    }
}
