package uni.vladavr.lab.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uni.vladavr.lab.dto.FlightDTO;
import uni.vladavr.lab.dto.RaceDTO;
import uni.vladavr.lab.entity.Flight;
import uni.vladavr.lab.entity.Race;

@Mapper
public interface RaceMapper {
    RaceMapper INSTANCE = Mappers.getMapper(RaceMapper.class);

    RaceDTO toDTO(Race obj);
    Race fromDTO(RaceDTO dto);
}
