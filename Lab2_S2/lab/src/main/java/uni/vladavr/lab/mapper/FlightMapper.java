package uni.vladavr.lab.mapper;

import org.mapstruct.factory.Mappers;
import uni.vladavr.lab.dto.CrewDTO;
import uni.vladavr.lab.dto.FlightDTO;
import uni.vladavr.lab.entity.Crewmate;
import uni.vladavr.lab.entity.Flight;

public interface FlightMapper {
    FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);

    FlightDTO toDTO(Flight obj);
    Flight fromDTO(FlightDTO dto);
}
