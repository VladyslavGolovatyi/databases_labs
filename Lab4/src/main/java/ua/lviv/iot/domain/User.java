package ua.lviv.iot.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private Integer id;
    private String fullName;
    private String email;
    private String password;
}
