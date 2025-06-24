package lk.ijse.vehicleservice.service;

import lk.ijse.vehicleservice.dto.VehicleDTO;

import java.util.List;

public interface VehicleService {
    String registerVehicle(VehicleDTO vehicleDTO);

    VehicleDTO getVehicleById(Long id);

    VehicleDTO getVehicleByLicensePlate(String licensePlate);

    VehicleDTO updateVehicle(VehicleDTO vehicleDTO);

    Boolean deleteVehicle(Long id);

    List<VehicleDTO> getVehiclesByUser(Long id);

    String updateParkingStatus(Long id, boolean isParked);
}
