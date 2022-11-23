package ua.lviv.iot.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "planes", schema = "golovatyi")
public class Plane {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "airline_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Airline airline;
    @Basic
    @Column(name = "max_passengers")
    private Integer maxPassengers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Plane plane = (Plane) o;
        return id != null && Objects.equals(id, plane.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
