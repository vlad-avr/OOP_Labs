package uni.vladavr.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uni.vladavr.lab.dto.RaceDTO;
import uni.vladavr.lab.service.RaceService;

@RestController
@RequestMapping(value = "/race")
@RequiredArgsConstructor
public class RaceController {
    private final RaceService service;
    @PutMapping
    private void doPut(@RequestBody RaceDTO raceDTO){

    }

    @GetMapping
    private void doGet(){

    }

    @PostMapping
    private void doPost(@RequestBody RaceDTO raceDTO){

    }
}
