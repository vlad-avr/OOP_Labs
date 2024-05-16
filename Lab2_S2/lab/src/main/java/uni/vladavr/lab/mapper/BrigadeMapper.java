package uni.vladavr.lab.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uni.vladavr.lab.dto.BrigadeDTO;
import uni.vladavr.lab.entity.Brigade;

@Mapper
public interface BrigadeMapper {
    BrigadeMapper INSTANCE = Mappers.getMapper(BrigadeMapper.class);

    BrigadeDTO toDTO(Brigade brigade);
    Brigade fromDTO(BrigadeDTO dto);
}
