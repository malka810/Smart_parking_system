package lk.ijse.parkingspaceservice.dto;

import java.time.LocalDateTime;

public class ParkingSpaceDTO {
    private Long parking_id;
    private String zone;
    private String location;
    private boolean isAvailable;
    private String size;
    private String type;
    private Double hourlyRate;
    private LocalDateTime occupiedAt;
    private LocalDateTime releasedAt;
    private String reservedBy;

    public ParkingSpaceDTO() {}

    public ParkingSpaceDTO(Long parking_id, String zone, String location, boolean isAvailable, String size, String type, Double hourlyRate, LocalDateTime occupiedAt, LocalDateTime releasedAt, String reservedBy) {
        this.parking_id = parking_id;
        this.zone = zone;
        this.location = location;
        this.isAvailable = isAvailable;
        this.size = size;
        this.type = type;
        this.hourlyRate = hourlyRate;
        this.occupiedAt = occupiedAt;
        this.releasedAt = releasedAt;
        this.reservedBy = reservedBy;
    }

    public Long getParking_id() {
        return parking_id;
    }

    public void setParking_id(Long parking_id) {
        this.parking_id = parking_id;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public LocalDateTime getOccupiedAt() {
        return occupiedAt;
    }

    public void setOccupiedAt(LocalDateTime occupiedAt) {
        this.occupiedAt = occupiedAt;
    }

    public LocalDateTime getReleasedAt() {
        return releasedAt;
    }

    public void setReleasedAt(LocalDateTime releasedAt) {
        this.releasedAt = releasedAt;
    }

    public String getReservedBy() {
        return reservedBy;
    }

    public void setReservedBy(String reservedBy) {
        this.reservedBy = reservedBy;
    }

    @Override
    public String toString() {
        return "ParkingSpaceDTO{" +
                "parking_id=" + parking_id +
                ", zone='" + zone + '\'' +
                ", location='" + location + '\'' +
                ", isAvailable=" + isAvailable +
                ", size='" + size + '\'' +
                ", type='" + type + '\'' +
                ", hourlyRate=" + hourlyRate +
                ", occupiedAt=" + occupiedAt +
                ", releasedAt=" + releasedAt +
                ", reservedBy='" + reservedBy + '\'' +
                '}';
    }
}
