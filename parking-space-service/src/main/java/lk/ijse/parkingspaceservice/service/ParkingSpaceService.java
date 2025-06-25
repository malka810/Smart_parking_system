package lk.ijse.parkingspaceservice.service;

import lk.ijse.parkingspaceservice.dto.ParkingSpaceDTO;

import java.util.List;

public interface ParkingSpaceService {
    ParkingSpaceDTO saveParkingSpace(ParkingSpaceDTO parkingSpaceDTO);

    ParkingSpaceDTO updateParkingSpace(ParkingSpaceDTO parkingSpaceDTO);

    Boolean deleteParkingSpace(Long parking_id);

    ParkingSpaceDTO getParkingSpaceById(Long parking_id);

    List<ParkingSpaceDTO> getAllParkingSpaces();

    List<ParkingSpaceDTO> getParkingSpacesByZone(String zone);

    List<ParkingSpaceDTO> getParkingSpacesByLocation(String location);

    List<ParkingSpaceDTO> getParkingSpacesByType(String type);

    List<ParkingSpaceDTO> getParkingSpacesBySize(String size);

    ParkingSpaceDTO reserveParkingSpace(Long parking_id, String reservedBy);

    ParkingSpaceDTO releaseParkingSpace(Long parking_id);

    List<ParkingSpaceDTO> getAvailableSpacesInZone(String zone);

    List<ParkingSpaceDTO> getAvailableSpacesInLocation(String location);

}
