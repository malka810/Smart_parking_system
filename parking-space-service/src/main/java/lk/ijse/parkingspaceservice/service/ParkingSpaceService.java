package lk.ijse.parkingspaceservice.service;

import lk.ijse.parkingspaceservice.dto.ParkingSpaceDTO;
import java.util.List;

public interface ParkingSpaceService {
    ParkingSpaceDTO createParkingSpace(ParkingSpaceDTO parkingSpaceDTO);
    ParkingSpaceDTO updateParkingSpace(Long id, ParkingSpaceDTO parkingSpaceDTO);
    void deleteParkingSpace(Long id);
    ParkingSpaceDTO getParkingSpaceById(Long id);
    List<ParkingSpaceDTO> getAllParkingSpaces();
    List<ParkingSpaceDTO> getParkingSpacesByFilter(
            String zone, String location, String type, String size, Boolean available);
    ParkingSpaceDTO reserveParkingSpace(Long id, String reservedBy);
    ParkingSpaceDTO releaseParkingSpace(Long id);
}