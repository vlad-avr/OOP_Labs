package uni.vladavr.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.vladavr.lab.dto.PlaneDTO;
import uni.vladavr.lab.dto.RaceDTO;
import uni.vladavr.lab.entity.Plane;
import uni.vladavr.lab.entity.Race;
import uni.vladavr.lab.mapper.PlaneMapper;
import uni.vladavr.lab.mapper.RaceMapper;
import uni.vladavr.lab.repository.PlaneRepo;
import uni.vladavr.lab.repository.RaceRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RaceService {
    private final RaceRepo repository;
    private final RaceMapper mapper = RaceMapper.INSTANCE;
    public void update(RaceDTO dto){
        Optional<Race> record = repository.findById(dto.getId());
        if(record.isEmpty()){
            return;
        }
        Race obj = record.get();
        obj.setArrivalPlace(dto.getArrivalPlace());
        obj.setArrivalTime(dto.getArrivalTime());
        obj.setDeparturePlace(dto.getDeparturePlace());
        obj.setDepartureTime(dto.getDepartureTime());
        obj.setPassengers(dto.getPassengers());
        obj.setLuggageWeight(dto.getLuggageWeight());
        repository.save(obj);
    }

    public void delete(String Id){
        if(repository.existsById(Id)){
            repository.deleteById(Id);
            repository.cascadeDelete(Id);
        }
    }

    public Optional<RaceDTO> get(String Id){
        Optional<Race> obj = repository.findById(Id);
        return obj.map(mapper::toDTO);
    }

    public List<RaceDTO> getAll(){
        List<Race> objs = repository.findAll();
        List<RaceDTO> DTOs = new ArrayList<>();
        for (Race o : objs){
            DTOs.add(mapper.toDTO(o));
        }
        return DTOs;
    }

    public List<RaceDTO> getByDeparture(String name){
        Optional<List<Race>> objs = repository.findByDeparturePlace(name);
        if(objs.isEmpty()){
            return new ArrayList<>();
        }
        List<RaceDTO> DTOs = new ArrayList<>();
        for (Race o : objs.get()){
            DTOs.add(mapper.toDTO(o));
        }
        return DTOs;
    }

    public List<RaceDTO> getByArrival(String name){
        Optional<List<Race>> objs = repository.findByArrivalPlace(name);
        if(objs.isEmpty()){
            return new ArrayList<>();
        }
        List<RaceDTO> DTOs = new ArrayList<>();
        for (Race o : objs.get()){
            DTOs.add(mapper.toDTO(o));
        }
        return DTOs;
    }

    public void create(RaceDTO dto){
        dto.setId(UUID.randomUUID().toString());
        Race o = mapper.fromDTO(dto);
        repository.save(o);
    }
}
