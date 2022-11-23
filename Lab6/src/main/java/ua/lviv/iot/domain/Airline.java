package ua.lviv.iot.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "airlines", schema = "golovatyi")
public class Airline {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "foundation_year")
    private Integer foundationYear;

    @OneToMany(mappedBy = "airline")
    @ToString.Exclude
    @JsonIgnore
    private List<Plane> planes;

    @ManyToMany
    @JoinTable(name = "base_airports", schema = "golovatyi", joinColumns = @JoinColumn(name = "airline_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "airport_id", referencedColumnName = "id", nullable = false))
    @ToString.Exclude
    private List<Airport> baseAirports;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Airline airline = (Airline) o;
        return id != null && Objects.equals(id, airline.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
