package uni.vladavr.lab.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String login;
    private String email;
    private String password;
}
