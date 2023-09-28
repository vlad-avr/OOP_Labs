package lab.flowers;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Flower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private Float stalk_length;
    private Float price;
    private Float fresh_factor; //0 -> not fresh | 1 -> very fresh

    private Boolean is_in_bouquet = false;

    public void set_stalk_len(float stalk_length){
        this.stalk_length = stalk_length;
    }

    public void set_price(float price){
        this.price = price;
    }

    public void set_fresh(float fresh_factor){
        this.fresh_factor = fresh_factor;
    }

    public void set_in_bouquet(boolean is_in_bouquet){
        this.is_in_bouquet = is_in_bouquet;
    }
}
