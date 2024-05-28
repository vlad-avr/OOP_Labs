package uni.vladavr.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.vladavr.lab.dto.CrewDTO;
import uni.vladavr.lab.dto.FlightDTO;
import uni.vladavr.lab.entity.Crewmate;
import uni.vladavr.lab.entity.Flight;
import uni.vladavr.lab.mapper.CrewMapper;
import uni.vladavr.lab.mapper.FlightMapper;
import uni.vladavr.lab.repository.CrewRepo;
import uni.vladavr.lab.repository.FlightRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepo repository;


    private final FlightMapper mapper = FlightMapper.INSTANCE;
    public void update(FlightDTO dto){
        Optional<Flight> record = repository.findById(dto.getId());
        if(record.isEmpty()){
            return;
        }
        Flight obj = record.get();
        obj.setPlaneId(dto.getPlaneId());
        obj.setBrigadeId(dto.getBrigadeId());
        obj.setRaceId(dto.getRaceId());
        repository.save(obj);
    }

    public void delete(String Id){
        if(repository.existsById(Id)){
            repository.deleteById(Id);
        }
    }

    public Optional<FlightDTO> get(String Id){
        Optional<Flight> obj = repository.findById(Id);
        return obj.map(mapper::toDTO);
    }

    public List<FlightDTO> getAll(){
        List<Flight> objs = repository.findAll();
        List<FlightDTO> DTOs = new ArrayList<>();
        for (Flight o : objs){
            DTOs.add(mapper.toDTO(o));
        }
        return DTOs;
    }

    public List<String> getIDs(String idCheck){
        switch (idCheck){
            case "race" -> {
                return repository.findRaceIds().get();
            }
            case "plane" -> {
                return repository.findPlaneIds().get();
            }
            case "brigade" -> {
                return repository.findBrigadeIds().get();
            }
            default -> {
                return new ArrayList<String>();
            }
        }
    }

    public void create(FlightDTO dto){
        dto.setId(UUID.randomUUID().toString());
        Flight o = mapper.fromDTO(dto);
        repository.save(o);
    }
}
