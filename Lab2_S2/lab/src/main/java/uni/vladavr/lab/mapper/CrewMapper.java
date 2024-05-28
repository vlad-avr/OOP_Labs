package uni.vladavr.lab.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uni.vladavr.lab.dto.BrigadeDTO;
import uni.vladavr.lab.dto.CrewDTO;
import uni.vladavr.lab.entity.Brigade;
import uni.vladavr.lab.entity.Crewmate;

@Mapper
public interface CrewMapper {
    CrewMapper INSTANCE = Mappers.getMapper(CrewMapper.class);

    CrewDTO toDTO(Crewmate obj);
    Crewmate fromDTO(CrewDTO dto);
}
