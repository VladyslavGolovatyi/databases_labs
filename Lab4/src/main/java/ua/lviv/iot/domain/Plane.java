package ua.lviv.iot.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Plane {
    private Integer id;
    private Integer airlineId;
    private Integer maxPassengers;
}
