package com.example.demo.services.impls;

import com.example.demo.entities.Building;
import com.example.demo.entities.Room;
import com.example.demo.enums.RoomStatus;
import com.example.demo.repositories.BuildingRepository;
import com.example.demo.repositories.RoomRepository;
import com.example.demo.services.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Building> findAllBuildings(){
        return buildingRepository.findAll();
    }

    @Override
    public Building saveBuilding(Building building) {
        validateCoordinates(building.getLatitude(), building.getLongitude());
        return buildingRepository.save(building);
    }

    private void validateCoordinates(Double latitude, Double longitude) {
        if (latitude == null || longitude == null) {
            throw new IllegalArgumentException("Latitude and Longitude cannot be null");
        }
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90 degrees");
        }
        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180 degrees");
        }
    }

    @Override
    public Building findBuildingById(Long id){
        return buildingRepository.findById(id).orElse(null);
    }

    @Override
    public Building findBuildingByAddress(String address){
        Optional<Building> building = buildingRepository.findByBuildingAddress(address);
        return building.orElse(null);
    }

    @Override
    public List<Room> checkAvailableRoomsByBuildingAddress(String address){
        return roomRepository.findByBuildingAddressAndRoomStatus(address, RoomStatus.FREE);
    }

}

