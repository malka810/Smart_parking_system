package lk.ijse.parkingspaceservice.service.impl;

import jakarta.ws.rs.NotFoundException;
import lk.ijse.parkingspaceservice.dto.ParkingSpaceDTO;
import lk.ijse.parkingspaceservice.entity.ParkingSpace;
import lk.ijse.parkingspaceservice.repo.ParkingSpaceRepository;
import lk.ijse.parkingspaceservice.service.ParkingSpaceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ParkingSpaceServiceImpl implements ParkingSpaceService {

    private final ParkingSpaceRepository parkingSpaceRepository;
    private final ModelMapper modelMapper;

    @Autowired // Optional in newer Spring versions
    public ParkingSpaceServiceImpl(ParkingSpaceRepository parkingSpaceRepository, ModelMapper modelMapper) {
        this.parkingSpaceRepository = parkingSpaceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ParkingSpaceDTO createParkingSpace(ParkingSpaceDTO parkingSpaceDTO) {
        ParkingSpace parkingSpace = modelMapper.map(parkingSpaceDTO, ParkingSpace.class);
        parkingSpace.setAvailable(true); // Ensure new spaces are available by default
        return modelMapper.map(parkingSpaceRepository.save(parkingSpace), ParkingSpaceDTO.class);
    }

    @Override
    public ParkingSpaceDTO updateParkingSpace(Long id, ParkingSpaceDTO parkingSpaceDTO) {
        ParkingSpace existingSpace = parkingSpaceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Parking space not found with id: " + id));

        modelMapper.map(parkingSpaceDTO, existingSpace);
        existingSpace.setParking_id(id); // Ensure ID doesn't get overwritten
        return modelMapper.map(parkingSpaceRepository.save(existingSpace), ParkingSpaceDTO.class);
    }

    @Override
    public void deleteParkingSpace(Long id) {
        if (!parkingSpaceRepository.existsById(id)) {
            throw new NotFoundException("Parking space not found with id: " + id);
        }
        parkingSpaceRepository.deleteById(id);
    }

    @Override
    public ParkingSpaceDTO getParkingSpaceById(Long id) {
        return modelMapper.map(
                parkingSpaceRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Parking space not found with id: " + id)),
                ParkingSpaceDTO.class);
    }

    @Override
    public List<ParkingSpaceDTO> getAllParkingSpaces() {
        return parkingSpaceRepository.findAll().stream()
                .map(space -> modelMapper.map(space, ParkingSpaceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ParkingSpaceDTO> getParkingSpacesByFilter(
            String zone, String location, String type, String size, Boolean available) {

        return parkingSpaceRepository.findByFilters(zone, location, type, size, available).stream()
                .map(space -> modelMapper.map(space, ParkingSpaceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ParkingSpaceDTO reserveParkingSpace(Long id, String reservedBy) {
        ParkingSpace parkingSpace = parkingSpaceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Parking space not found with id: " + id));

        if (!parkingSpace.isAvailable()) {
            throw new IllegalStateException("Parking space is already reserved");
        }

        parkingSpace.setAvailable(false);
        parkingSpace.setReservedBy(reservedBy);
        parkingSpace.setOccupiedAt(LocalDateTime.now());

        return modelMapper.map(parkingSpaceRepository.save(parkingSpace), ParkingSpaceDTO.class);
    }

    @Override
    public ParkingSpaceDTO releaseParkingSpace(Long id) {
        ParkingSpace parkingSpace = parkingSpaceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Parking space not found with id: " + id));

        if (parkingSpace.isAvailable()) {
            throw new IllegalStateException("Parking space is already available");
        }

        parkingSpace.setAvailable(true);
        parkingSpace.setReservedBy(null);
        parkingSpace.setReleasedAt(LocalDateTime.now());

        return modelMapper.map(parkingSpaceRepository.save(parkingSpace), ParkingSpaceDTO.class);
    }
}