package lk.ijse.parkingspaceservice.controller;

import jakarta.ws.rs.NotFoundException;
import lk.ijse.parkingspaceservice.dto.ParkingSpaceDTO;
import lk.ijse.parkingspaceservice.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking-space-service/api/v1/parking-space")
public class ParkingSpaceController {

    @Autowired
    private ParkingSpaceService parkingSpaceService;

    @PostMapping
    public ResponseEntity<ParkingSpaceDTO> saveParkingSpace(@RequestBody ParkingSpaceDTO parkingSpaceDTO) {
        return ResponseEntity.ok(parkingSpaceService.saveParkingSpace(parkingSpaceDTO));
    }

    @PutMapping
    public ResponseEntity<ParkingSpaceDTO> updateParkingSpace(@RequestBody ParkingSpaceDTO parkingSpaceDTO) throws NotFoundException {
        ParkingSpaceDTO updatedSpace = parkingSpaceService.updateParkingSpace(parkingSpaceDTO);
        return updatedSpace != null ? ResponseEntity.ok(updatedSpace) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{parking_id}")
    public ResponseEntity<Boolean> deleteParkingSpace(@PathVariable Long parking_id) throws NotFoundException {
        return ResponseEntity.ok(parkingSpaceService.deleteParkingSpace(parking_id));
    }

    @GetMapping("/{parking_id}")
    public ResponseEntity<ParkingSpaceDTO> getParkingSpaceById(@PathVariable Long parking_id) throws NotFoundException {
        ParkingSpaceDTO space = parkingSpaceService.getParkingSpaceById(parking_id);
        return space != null ? ResponseEntity.ok(space) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<ParkingSpaceDTO>> getAllParkingSpaces() {
        List<ParkingSpaceDTO> spaces = parkingSpaceService.getAllParkingSpaces();
        return !spaces.isEmpty() ? ResponseEntity.ok(spaces) : ResponseEntity.notFound().build();
    }

    @GetMapping("/zone/{zone}")
    public ResponseEntity<List<ParkingSpaceDTO>> getParkingSpacesByZone(@PathVariable String zone) {
        List<ParkingSpaceDTO> spaces = parkingSpaceService.getParkingSpacesByZone(zone);
        return !spaces.isEmpty() ? ResponseEntity.ok(spaces) : ResponseEntity.notFound().build();
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<ParkingSpaceDTO>> getParkingSpacesByLocation(@PathVariable String location) {
        List<ParkingSpaceDTO> spaces = parkingSpaceService.getParkingSpacesByLocation(location);
        return !spaces.isEmpty() ? ResponseEntity.ok(spaces) : ResponseEntity.notFound().build();
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<ParkingSpaceDTO>> getParkingSpacesByType(@PathVariable String type) {
        List<ParkingSpaceDTO> spaces = parkingSpaceService.getParkingSpacesByType(type);
        return !spaces.isEmpty() ? ResponseEntity.ok(spaces) : ResponseEntity.notFound().build();
    }

    @GetMapping("/size/{size}")
    public ResponseEntity<List<ParkingSpaceDTO>> getParkingSpacesBySize(@PathVariable String size) {
        List<ParkingSpaceDTO> spaces = parkingSpaceService.getParkingSpacesBySize(size);
        return !spaces.isEmpty() ? ResponseEntity.ok(spaces) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/reserve/{parking_id}")
    public ResponseEntity<ParkingSpaceDTO> reserveParkingSpace(
            @PathVariable Long parking_id,
            @RequestParam String reservedBy) throws NotFoundException {
        ParkingSpaceDTO space = parkingSpaceService.reserveParkingSpace(parking_id, reservedBy);
        return space != null ? ResponseEntity.ok(space) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/release/{parking_id}")
    public ResponseEntity<ParkingSpaceDTO> releaseParkingSpace(@PathVariable Long parking_id) throws NotFoundException {
        ParkingSpaceDTO space = parkingSpaceService.releaseParkingSpace(parking_id);
        return space != null ? ResponseEntity.ok(space) : ResponseEntity.notFound().build();
    }

    @GetMapping("/available/zone/{zone}")
    public ResponseEntity<List<ParkingSpaceDTO>> getAvailableSpacesInZone(@PathVariable String zone) {
        List<ParkingSpaceDTO> spaces = parkingSpaceService.getAvailableSpacesInZone(zone);
        return !spaces.isEmpty() ? ResponseEntity.ok(spaces) : ResponseEntity.notFound().build();
    }

    @GetMapping("/available/location/{location}")
    public ResponseEntity<List<ParkingSpaceDTO>> getAvailableSpacesInLocation(@PathVariable String location) {
        List<ParkingSpaceDTO> spaces = parkingSpaceService.getAvailableSpacesInLocation(location);
        return !spaces.isEmpty() ? ResponseEntity.ok(spaces) : ResponseEntity.notFound().build();
    }

}