package uni.vladavr.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uni.vladavr.lab.dto.BrigadeDTO;
import uni.vladavr.lab.dto.CrewDTO;
import uni.vladavr.lab.service.CrewService;
import uni.vladavr.lab.service.JsonParser;
import uni.vladavr.lab.service.RoleUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/crew")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")
public class CrewController {
    private final CrewService service;
    @PutMapping
    private String doPut(@RequestHeader("access-token") String token, @RequestBody CrewDTO dto) throws Exception {
        if(!RoleUtil.validateAccess(RoleUtil.getRole(token), RoleUtil.getAllowedRoles(new String[]{RoleUtil.ADMIN, RoleUtil.DISPATCH}))){
            return "[]";
        }
        service.update(dto);
        return JsonParser.toJsonObject(service.getAll());
    }

    @GetMapping
    private String doGet(@RequestHeader("access-token") String token, @RequestParam("field") String field, @RequestParam("value") String value) throws Exception {
        String role = RoleUtil.getRole(token);
        List<CrewDTO> dtoList = new ArrayList<>();
        if(RoleUtil.validateAccess(role, RoleUtil.getAllowedRoles(new String[]{RoleUtil.USER, RoleUtil.DISPATCH, RoleUtil.ADMIN}))) {
            switch (field) {
                case "name":
                    dtoList = service.getByName(value);
                    break;
                case "qualification":
                    dtoList = service.getByQualification(value);
                    break;
                case "id":
                    Optional<CrewDTO> dto = service.get(value);
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
    private String doPost(@RequestHeader("access-token") String token, @RequestBody CrewDTO dto) throws Exception {
        if(!RoleUtil.validateAccess(RoleUtil.getRole(token), RoleUtil.getAllowedRoles(new String[]{RoleUtil.ADMIN, RoleUtil.DISPATCH}))){
            return "[]";
        }
        service.create(dto);
        return JsonParser.toJsonObject(service.getAll());
    }
}
