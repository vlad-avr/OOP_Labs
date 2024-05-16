package uni.vladavr.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uni.vladavr.lab.dto.FlightDTO;
import uni.vladavr.lab.dto.PlaneDTO;
import uni.vladavr.lab.service.FlightService;

@RestController
@RequestMapping(value = "/flight")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService service;
    @PutMapping
    private void doPut(@RequestBody FlightDTO flightDTO){

    }

    @GetMapping
    private void doGet(){

    }

    @PostMapping
    private void doPost(@RequestBody FlightDTO flightDTO){

    }
}
