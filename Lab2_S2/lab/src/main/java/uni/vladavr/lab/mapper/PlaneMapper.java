package uni.vladavr.lab.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uni.vladavr.lab.dto.FlightDTO;
import uni.vladavr.lab.dto.PlaneDTO;
import uni.vladavr.lab.entity.Flight;
import uni.vladavr.lab.entity.Plane;

@Mapper
public interface PlaneMapper {
    PlaneMapper INSTANCE = Mappers.getMapper(PlaneMapper.class);

    PlaneDTO toDTO(Plane obj);
    Plane fromDTO(PlaneDTO dto);
}
