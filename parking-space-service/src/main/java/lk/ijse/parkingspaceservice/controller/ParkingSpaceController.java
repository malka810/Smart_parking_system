package lk.ijse.parkingspaceservice.controller;

import lk.ijse.parkingspaceservice.dto.ParkingSpaceDTO;
import lk.ijse.parkingspaceservice.service.ParkingSpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parking-spaces")
public class ParkingSpaceController {

    @Autowired
    private final ParkingSpaceService parkingSpaceService;

    public ParkingSpaceController(ParkingSpaceService parkingSpaceService) {
        this.parkingSpaceService = parkingSpaceService;
    }

    @PostMapping
    public ResponseEntity<ParkingSpaceDTO> createParkingSpace(@RequestBody ParkingSpaceDTO parkingSpaceDTO) {
        return ResponseEntity.ok(parkingSpaceService.createParkingSpace(parkingSpaceDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingSpaceDTO> updateParkingSpace(
            @PathVariable Long id,
            @RequestBody ParkingSpaceDTO parkingSpaceDTO) {
        return ResponseEntity.ok(parkingSpaceService.updateParkingSpace(id, parkingSpaceDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParkingSpace(@PathVariable Long id) {
        parkingSpaceService.deleteParkingSpace(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpaceDTO> getParkingSpaceById(@PathVariable Long id) {
        return ResponseEntity.ok(parkingSpaceService.getParkingSpaceById(id));
    }

    @GetMapping
    public ResponseEntity<List<ParkingSpaceDTO>> getAllParkingSpaces() {
        return ResponseEntity.ok(parkingSpaceService.getAllParkingSpaces());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ParkingSpaceDTO>> getParkingSpacesByFilter(
            @RequestParam(required = false) String zone,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String size,
            @RequestParam(required = false) Boolean available) {
        return ResponseEntity.ok(parkingSpaceService.getParkingSpacesByFilter(
                zone, location, type, size, available));
    }

    @PatchMapping("/{id}/reserve")
    public ResponseEntity<ParkingSpaceDTO> reserveParkingSpace(
            @PathVariable Long id,
            @RequestParam String reservedBy) {
        return ResponseEntity.ok(parkingSpaceService.reserveParkingSpace(id, reservedBy));
    }

    @PatchMapping("/{id}/release")
    public ResponseEntity<ParkingSpaceDTO> releaseParkingSpace(@PathVariable Long id) {
        return ResponseEntity.ok(parkingSpaceService.releaseParkingSpace(id));
    }
}