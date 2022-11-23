package ua.lviv.iot.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Flight {
    private Integer id;
    private Integer srcAirportId;
    private Integer destAirportId;
    private Integer planeId;
}
