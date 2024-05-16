package uni.vladavr.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.vladavr.lab.repository.CrewRepo;

@Service
@RequiredArgsConstructor
public class CrewService {
    private final CrewRepo repository;

}
