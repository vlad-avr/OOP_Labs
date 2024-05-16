package uni.vladavr.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uni.vladavr.lab.dto.PlaneDTO;
import uni.vladavr.lab.service.FlightService;
import uni.vladavr.lab.service.PlaneService;

@RestController
@RequestMapping(value = "/plane")
@RequiredArgsConstructor
public class PlaneController {
    private final PlaneService service;

    @PutMapping
    private void doPut(@RequestBody PlaneDTO planeDTO){

    }

    @GetMapping
    private void doGet(){

    }

    @PostMapping
    private void doPost(@RequestBody PlaneDTO planeDTO){

    }
}
