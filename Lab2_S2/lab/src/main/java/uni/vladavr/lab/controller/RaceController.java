package uni.vladavr.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uni.vladavr.lab.dto.PlaneDTO;
import uni.vladavr.lab.dto.RaceDTO;
import uni.vladavr.lab.service.JsonParser;
import uni.vladavr.lab.service.RaceService;
import uni.vladavr.lab.service.RoleUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/race")
@RequiredArgsConstructor
public class RaceController {
    private final RaceService service;
    @PutMapping
    private String doPut(@RequestHeader("access-token") String token, @RequestBody RaceDTO dto) throws Exception {
        if(!RoleUtil.validateAccess(RoleUtil.getRole(token), RoleUtil.getAllowedRoles(new String[]{RoleUtil.ADMIN, RoleUtil.DISPATCH}))){
            return "[]";
        }
        service.update(dto);
        return JsonParser.toJsonObject(service.getAll());
    }

    @GetMapping
    private String doGet(@RequestHeader("access-token") String token, @RequestParam("field") String field, @RequestParam("value") String value) throws Exception {
        String role = RoleUtil.getRole(token);
        List<RaceDTO> dtoList = new ArrayList<>();
        if(RoleUtil.validateAccess(role, RoleUtil.getAllowedRoles(new String[]{RoleUtil.USER, RoleUtil.DISPATCH, RoleUtil.ADMIN}))) {
            switch (field) {
                case "departure":
                    dtoList = service.getByDeparture(value);
                    break;
                case "arrival":
                    dtoList = service.getByArrival(value);
                    break;
                case "id":
                    Optional<RaceDTO> dto = service.get(value);
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
    private String doPost(@RequestHeader("access-token") String token, @RequestBody RaceDTO dto) throws Exception {
        if(!RoleUtil.validateAccess(RoleUtil.getRole(token), RoleUtil.getAllowedRoles(new String[]{RoleUtil.ADMIN, RoleUtil.DISPATCH}))){
            return "[]";
        }
        service.create(dto);
        return JsonParser.toJsonObject(service.getAll());
    }
}
