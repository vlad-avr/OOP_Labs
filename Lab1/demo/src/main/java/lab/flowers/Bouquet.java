package lab.flowers;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "bouquet_db")
public class Bouquet {
    @OneToMany(fetch = FetchType.EAGER)
    private List<Flower> flowers;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
}
