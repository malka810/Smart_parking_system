package lk.ijse.parkingspaceservice.repo;

import jakarta.persistence.QueryHint;
import lk.ijse.parkingspaceservice.entity.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {

    Collection<Object> findByZone(String zone);

    Collection<Object> findByLocation(String location);

    List<ParkingSpace> findByType(String type);

    List<ParkingSpace> findBySize(String size);

    @QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
    @Query("SELECT p FROM ParkingSpace p WHERE p.zone = :zone AND p.isAvailable = true")
    List<ParkingSpace> findAvailableSpacesInZone(@Param("zone") String zone);


    @QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
    @Query("SELECT p FROM ParkingSpace p WHERE p.location = :location AND p.isAvailable = true")
    List<ParkingSpace> findAvailableSpacesInLocation(@Param("location") String location);


}
