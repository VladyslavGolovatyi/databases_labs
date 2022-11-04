package ua.lviv.iot.repository;


import ua.lviv.iot.domain.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanesRepository extends JpaRepository<Plane, Integer> {
}
