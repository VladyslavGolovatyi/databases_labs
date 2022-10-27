package ua.lviv.iot.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Airline {
    private Integer id;
    private String name;
    private Integer foundationYear;
}
