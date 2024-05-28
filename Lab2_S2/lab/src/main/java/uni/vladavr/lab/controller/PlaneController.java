package uni.vladavr.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uni.vladavr.lab.dto.CrewDTO;
import uni.vladavr.lab.dto.PlaneDTO;
import uni.vladavr.lab.service.FlightService;
import uni.vladavr.lab.service.JsonParser;
import uni.vladavr.lab.service.PlaneService;
import uni.vladavr.lab.service.RoleUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/plane")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")
public class PlaneController {
    private final PlaneService service;

    @PutMapping
    private String doPut(@RequestHeader("access-token") String token, @RequestBody PlaneDTO dto) throws Exception {
        if(!RoleUtil.validateAccess(RoleUtil.getRole(token), RoleUtil.getAllowedRoles(new String[]{RoleUtil.ADMIN, RoleUtil.DISPATCH}))){
            return "[]";
        }
        service.update(dto);
        return JsonParser.toJsonObject(service.getAll());
    }

    @GetMapping
    private String doGet(@RequestHeader("access-token") String token, @RequestParam("field") String field, @RequestParam("value") String value) throws Exception {
        String role = RoleUtil.getRole(token);
        List<PlaneDTO> dtoList = new ArrayList<>();
        if(RoleUtil.validateAccess(role, RoleUtil.getAllowedRoles(new String[]{RoleUtil.USER, RoleUtil.DISPATCH, RoleUtil.ADMIN}))) {
            switch (field) {
                case "model":
                    dtoList = service.getByModel(value);
                    break;
                case "id":
                    Optional<PlaneDTO> dto = service.get(value);
                    dto.ifPresent(dtoList::add);
                    break;
                case "all":
                    dtoList = service.getAll();
                    break;
                default:
                    if (RoleUtil.validateAccess(role, RoleUtil.getAllowedRoles(new String[]{RoleUtil.DISPATCH, RoleUtil.ADMIN}))) {
                        switch (field) {
                            case "delete":
                                service.delete(value);
                                dtoList = service.getAll();
                                break;
                            default:
                                return "[]";
                        }
                    } else {
                        return "[]";
                    }
            }
        }
        return JsonParser.toJsonObject(dtoList);
    }

    @PostMapping
    private String doPost(@RequestHeader("access-token") String token, @RequestBody PlaneDTO dto) throws Exception {
        if(!RoleUtil.validateAccess(RoleUtil.getRole(token), RoleUtil.getAllowedRoles(new String[]{RoleUtil.ADMIN, RoleUtil.DISPATCH}))){
            return "[]";
        }
        service.create(dto);
        return JsonParser.toJsonObject(service.getAll());
    }
}
