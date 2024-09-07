package com.example.demo.services;

import com.example.demo.entities.Building;
import com.example.demo.entities.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BuildingService {
    List<Building> findAllBuildings();
    Building saveBuilding(Building building);
    Building findBuildingById(Long id);
    Building findBuildingByAddress(String address);
    List<Room> checkAvailableRoomsByBuildingAddress(String address);

}
