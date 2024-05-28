package uni.vladavr.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.vladavr.lab.dto.BrigadeDTO;
import uni.vladavr.lab.dto.CrewDTO;
import uni.vladavr.lab.entity.Brigade;
import uni.vladavr.lab.entity.Crewmate;
import uni.vladavr.lab.mapper.BrigadeMapper;
import uni.vladavr.lab.mapper.CrewMapper;
import uni.vladavr.lab.repository.CrewRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CrewService {
    private final CrewRepo repository;

    private final CrewMapper mapper = CrewMapper.INSTANCE;
    public void update(CrewDTO dto){
        Optional<Crewmate> record = repository.findById(dto.getId());
        if(record.isEmpty()){
            return;
        }
        Crewmate obj = record.get();
        obj.setName(dto.getName());
        obj.setQualification(dto.getQualification());
        obj.setBrigadeId(dto.getBrigadeId());
        repository.save(obj);
    }

    public void delete(String Id){
        if(repository.existsById(Id)){
            repository.deleteById(Id);
        }
    }

    public Optional<CrewDTO> get(String Id){
        Optional<Crewmate> obj = repository.findById(Id);
        return obj.map(mapper::toDTO);
    }

    public List<CrewDTO> getAll(){
        List<Crewmate> objs = repository.findAll();
        List<CrewDTO> DTOs = new ArrayList<>();
        for (Crewmate o : objs){
            DTOs.add(mapper.toDTO(o));
        }
        return DTOs;
    }

    public List<CrewDTO> getByName(String name){
        Optional<List<Crewmate>> objs = repository.findByName(name);
        if(objs.isEmpty()){
            return new ArrayList<>();
        }
        List<CrewDTO> DTOs = new ArrayList<>();
        for (Crewmate o : objs.get()){
            DTOs.add(mapper.toDTO(o));
        }
        return DTOs;
    }

    public List<CrewDTO> getByQualification(String qual){
        Optional<List<Crewmate>> objs = repository.findByQualification(qual);
        if(objs.isEmpty()){
            return new ArrayList<>();
        }
        List<CrewDTO> DTOs = new ArrayList<>();
        for (Crewmate o : objs.get()){
            DTOs.add(mapper.toDTO(o));
        }
        return DTOs;
    }

    public List<String> getBrigadeIds(){
        Optional<List<String>> objs = repository.getBrigadeIds();
        return objs.orElseGet(ArrayList::new);
    }

    public void create(CrewDTO dto){
        dto.setId(UUID.randomUUID().toString());
        Crewmate o = mapper.fromDTO(dto);
        repository.save(o);
    }
}
