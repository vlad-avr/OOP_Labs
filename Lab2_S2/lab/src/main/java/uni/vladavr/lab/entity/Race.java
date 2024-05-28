package uni.vladavr.lab.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="races")
public class Race {
    @jakarta.persistence.Id
    @Column(name = "id")
    private String Id;
    @Column(name = "departure_place")
    private String departurePlace;
    @Column(name = "arrival_place")
    private String arrivalPlace;
    @Column(name = "departure_time")
    private String departureTime;
    @Column(name = "arrival_time")
    private String arrivalTime;
    @Column(name = "passengers")
    private int passengers;
    @Column(name = "luggage_weight")
    private double luggageWeight;
}
