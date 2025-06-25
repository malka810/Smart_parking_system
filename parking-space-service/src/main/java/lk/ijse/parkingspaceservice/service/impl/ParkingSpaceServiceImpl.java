package lk.ijse.parkingspaceservice.service.impl;


import jakarta.ws.rs.NotFoundException;
import lk.ijse.parkingspaceservice.dto.ParkingSpaceDTO;
import lk.ijse.parkingspaceservice.entity.ParkingSpace;
import lk.ijse.parkingspaceservice.repo.ParkingSpaceRepository;
import lk.ijse.parkingspaceservice.service.ParkingSpaceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ParkingSpaceServiceImpl implements ParkingSpaceService {

    @Autowired
    ParkingSpaceRepository parkingSpaceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ParkingSpaceDTO saveParkingSpace(ParkingSpaceDTO parkingSpaceDTO) {
        ParkingSpace parkingSpace = modelMapper.map(parkingSpaceDTO, ParkingSpace.class);
        parkingSpace = parkingSpaceRepository.save(parkingSpace);
        return modelMapper.map(parkingSpace, ParkingSpaceDTO.class);
    }

    @Override
    public ParkingSpaceDTO updateParkingSpace(ParkingSpaceDTO parkingSpaceDTO) {
        ParkingSpace existingSpace = parkingSpaceRepository.findById(parkingSpaceDTO.getParking_id())
                .orElseThrow(() -> new NotFoundException("Parking space not found"));

        modelMapper.map(parkingSpaceDTO, existingSpace);
        ParkingSpace updatedSpace = parkingSpaceRepository.save(existingSpace);
        return modelMapper.map(updatedSpace, ParkingSpaceDTO.class);
    }

    @Override
    public Boolean deleteParkingSpace(Long parking_id) {
        if (!parkingSpaceRepository.existsById(parking_id)) {
            throw new NotFoundException("Parking space not found");
        }
        parkingSpaceRepository.deleteById(parking_id);
        return true;
    }

    @Override
    public ParkingSpaceDTO getParkingSpaceById(Long parking_id) {
        ParkingSpace parkingSpace = parkingSpaceRepository.findById(parking_id)
                .orElseThrow(() -> new NotFoundException("Parking space not found"));
        return modelMapper.map(parkingSpace, ParkingSpaceDTO.class);
    }

    @Override
    public List<ParkingSpaceDTO> getAllParkingSpaces() {
        return parkingSpaceRepository.findAll().stream()
                .map(space -> modelMapper.map(space, ParkingSpaceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ParkingSpaceDTO> getParkingSpacesByZone(String zone) {
        return parkingSpaceRepository.findByZone(zone).stream()
                .map(space -> modelMapper.map(space, ParkingSpaceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ParkingSpaceDTO> getParkingSpacesByLocation(String location) {
        return parkingSpaceRepository.findByLocation(location).stream()
                .map(space -> modelMapper.map(space, ParkingSpaceDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public List<ParkingSpaceDTO> getParkingSpacesByType(String type) {
        List<ParkingSpace> spaces = parkingSpaceRepository.findByType(type);
        if (spaces.isEmpty()) {
            throw new NotFoundException("No parking spaces found of type: " + type);
        }
        return spaces.stream()
                .map(space -> modelMapper.map(space, ParkingSpaceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ParkingSpaceDTO> getParkingSpacesBySize(String size) {
        List<ParkingSpace> spaces = parkingSpaceRepository.findBySize(size);
        if (spaces.isEmpty()) {
            throw new NotFoundException("No parking spaces found with size: " + size);
        }
        return spaces.stream()
                .map(space -> modelMapper.map(space, ParkingSpaceDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public ParkingSpaceDTO reserveParkingSpace(Long parking_id, String reservedBy) {
        ParkingSpace parkingSpace = parkingSpaceRepository.findById(parking_id)
                .orElseThrow(() -> new NotFoundException("Parking space not found with ID: " + parking_id));

        parkingSpace.setAvailable(false);
        parkingSpace.setReservedBy(reservedBy);
        parkingSpace = parkingSpaceRepository.save(parkingSpace);

        return modelMapper.map(parkingSpace, ParkingSpaceDTO.class);
    }

    @Override
    public ParkingSpaceDTO releaseParkingSpace(Long parking_id) {
        ParkingSpace parkingSpace = parkingSpaceRepository.findById(parking_id)
                .orElseThrow(() -> new NotFoundException("Parking space not found with ID: " + parking_id));

        parkingSpace.setAvailable(true);
        parkingSpace.setReservedBy(null);
        parkingSpace = parkingSpaceRepository.save(parkingSpace);

        return modelMapper.map(parkingSpace, ParkingSpaceDTO.class);
    }

    @Override
    public List<ParkingSpaceDTO> getAvailableSpacesInZone(String zone) {
        List<ParkingSpace> spaces = parkingSpaceRepository.findAvailableSpacesInZone(zone);
        if (spaces.isEmpty()) {
            throw new NotFoundException("No available parking spaces found in zone: " + zone);
        }
        return spaces.stream()
                .map(space -> modelMapper.map(space, ParkingSpaceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ParkingSpaceDTO> getAvailableSpacesInLocation(String location) {
        List<ParkingSpace> spaces = parkingSpaceRepository.findAvailableSpacesInLocation(location);
        if (spaces.isEmpty()) {
            throw new NotFoundException("No available parking spaces found at location: " + location);
        }
        return spaces.stream()
                .map(space -> modelMapper.map(space, ParkingSpaceDTO.class))
                .collect(Collectors.toList());
    }

    }

