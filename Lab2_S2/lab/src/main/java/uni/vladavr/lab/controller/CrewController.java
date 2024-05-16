package uni.vladavr.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uni.vladavr.lab.dto.CrewDTO;
import uni.vladavr.lab.service.CrewService;

@RestController
@RequestMapping(value = "/crew")
@RequiredArgsConstructor
public class CrewController {
    private final CrewService service;
    @PutMapping
    private void doPut(@RequestBody CrewDTO crewDTO){

    }

    @GetMapping
    private void doGet(){

    }

    @PostMapping
    private void doPost(@RequestBody CrewDTO crewDTO){

    }
}
