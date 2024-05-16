package uni.vladavr.lab.dto;


import lombok.Data;
import uni.vladavr.lab.entity.Crewmate;
@Data
public class CrewDTO {
    private String Id;
    private String name;
    private Crewmate.Qualification qualification;
    private String brigadeId;
}
