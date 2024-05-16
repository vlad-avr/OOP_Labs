package uni.vladavr.lab.dto;

import lombok.Data;

@Data
public class RaceDTO {
    private String Id;
    private String departurePlace;
    private String arrivalPlace;
    private String departureTime;
    private String arrivalTime;
    private int passengers;
    private double luggageWeight;
}
