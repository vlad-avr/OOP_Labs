package uni.vladavr.lab.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="planes")
public class Plane {
    @jakarta.persistence.Id
    @Column(name = "id")
    private String Id;
    @Column(name = "model")
    private String model;
    @Column(name = "passenger_seats")
    private int passengerSeats;
    @Column(name = "max_luggage_weight")
    private double maxLuggage;
    @Column(name = "max_flight_in_mins")
    private int maxFlightInMins;
}
