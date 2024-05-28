package uni.vladavr.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;
import uni.vladavr.lab.dto.BrigadeDTO;
import uni.vladavr.lab.entity.Brigade;
import uni.vladavr.lab.entity.Crewmate;
import uni.vladavr.lab.mapper.BrigadeMapper;
import uni.vladavr.lab.repository.BrigadeRepo;
import uni.vladavr.lab.repository.CrewRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class BrigadeService {
    private final BrigadeRepo repository;
    private final BrigadeMapper mapper = BrigadeMapper.INSTANCE;
    public void update(BrigadeDTO brigadeDTO){
        Optional<Brigade> brigadeRecord = repository.findById(brigadeDTO.getId());
        if(brigadeRecord.isEmpty()){
            return;
        }
        Brigade brigade = brigadeRecord.get();
        brigade.setName(brigadeDTO.getName());
        brigade.setStaticCrew(brigadeDTO.isStaticCrew());
        repository.save(brigade);
    }

    public void delete(String Id){
        if(repository.existsById(Id)){
            repository.deleteById(Id);
            repository.cascadeUpdate(Id);
            repository.cascadeDelete(Id);
        }
    }

    public Optional<BrigadeDTO> get(String Id){
        Optional<Brigade> brigade = repository.findById(Id);
        return brigade.map(mapper::toDTO);
    }

    public List<BrigadeDTO> getAll(){
        List<Brigade> brigades = repository.findAll();
        List<BrigadeDTO> DTOs = new ArrayList<>();
        for (Brigade brigade : brigades){
            DTOs.add(mapper.toDTO(brigade));
        }
        return DTOs;
    }

    public List<BrigadeDTO> getByName(String name){
        Optional<List<Brigade>> brigades = repository.findByName(name);
        if(brigades.isEmpty()){
            return new ArrayList<>();
        }
        List<BrigadeDTO> DTOs = new ArrayList<>();
        for (Brigade brigade : brigades.get()){
            DTOs.add(mapper.toDTO(brigade));
        }
        return DTOs;
    }

    public void create(BrigadeDTO dto){
        dto.setId(UUID.randomUUID().toString());
        Brigade brigade = mapper.fromDTO(dto);
        repository.save(brigade);
    }
}
