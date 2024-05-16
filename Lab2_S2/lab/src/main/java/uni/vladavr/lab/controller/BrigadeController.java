package uni.vladavr.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uni.vladavr.lab.dto.BrigadeDTO;
import uni.vladavr.lab.service.BrigadeService;

@RestController
@RequestMapping(value = "/brigade")
@RequiredArgsConstructor
public class BrigadeController {
    private final BrigadeService service;

    @PutMapping
    private void doPut(@RequestBody BrigadeDTO brigadeDTO){

    }

    @GetMapping
    private void doGet(){

    }

    @PostMapping
    private void doPost(@RequestBody BrigadeDTO brigadeDTO){

    }
}
