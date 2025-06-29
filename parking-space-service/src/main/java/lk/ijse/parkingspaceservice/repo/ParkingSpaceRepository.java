package lk.ijse.parkingspaceservice.repo;

import jakarta.persistence.QueryHint;
import lk.ijse.parkingspaceservice.entity.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {


    @Query("SELECT p FROM ParkingSpace p WHERE " +
            "(:zone IS NULL OR p.zone = :zone) AND " +
            "(:location IS NULL OR p.location = :location) AND " +
            "(:type IS NULL OR p.type = :type) AND " +
            "(:size IS NULL OR p.size = :size) AND " +
            "(:available IS NULL OR p.isAvailable = :available)")
    List<ParkingSpace> findByFilters(
            @Param("zone") String zone,
            @Param("location") String location,
            @Param("type") String type,
            @Param("size") String size,
            @Param("available") Boolean available);
}
