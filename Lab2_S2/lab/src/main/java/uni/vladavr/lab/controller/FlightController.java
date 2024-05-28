package uni.vladavr.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uni.vladavr.lab.dto.CrewDTO;
import uni.vladavr.lab.dto.FlightDTO;
import uni.vladavr.lab.dto.PlaneDTO;
import uni.vladavr.lab.service.FlightService;
import uni.vladavr.lab.service.JsonParser;
import uni.vladavr.lab.service.RoleUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/flight")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService service;
    @PutMapping
    private String doPut(@RequestHeader("access-token") String token, @RequestBody FlightDTO dto) throws Exception {
        if(!RoleUtil.validateAccess(RoleUtil.getRole(token), RoleUtil.getAllowedRoles(new String[]{RoleUtil.ADMIN}))){
            return "[]";
        }
        service.update(dto);
        return JsonParser.toJsonObject(service.getAll());
    }


    @GetMapping
    private String doGet(@RequestHeader("access-token") String token, @RequestParam("field") String field, @RequestParam("value") String value) throws Exception {
        String role = RoleUtil.getRole(token);
        List<FlightDTO> dtoList = new ArrayList<>();
        if (RoleUtil.validateAccess(role, RoleUtil.getAllowedRoles(new String[]{RoleUtil.ADMIN}))) {
            switch (field) {
                case "id":
                    Optional<FlightDTO> dto = service.get(value);
                    dto.ifPresent(dtoList::add);
                    break;
                case "all":
                    dtoList = service.getAll();
                    break;
                case "delete":
                    service.delete(value);
                    dtoList = service.getAll();
                    break;
                case "ids":
                    List<String> ids = service.getIDs(value);
                    return JsonParser.toJsonObject(ids);
                default:
                    return "[]";
            }
        }
        return JsonParser.toJsonObject(dtoList);
    }

    @PostMapping
    private String doPost(@RequestHeader("access-token") String token, @RequestBody FlightDTO dto) throws Exception {
        if(!RoleUtil.validateAccess(RoleUtil.getRole(token), RoleUtil.getAllowedRoles(new String[]{RoleUtil.ADMIN}))){
            return "[]";
        }
        service.create(dto);
        return JsonParser.toJsonObject(service.getAll());
    }
}
