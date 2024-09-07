package com.example.demo.controllers;


import com.example.demo.entities.Building;
import com.example.demo.entities.Room;
import com.example.demo.services.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/building")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;

    @GetMapping
    public ResponseEntity<List<Building>> getAllBuildings() {
        return ResponseEntity.ok(buildingService.findAllBuildings());
    }

    @PostMapping
    public ResponseEntity<Building> createBuilding(@RequestBody Building building) {
        Building newBuilding = buildingService.saveBuilding(building);
        return ResponseEntity.ok(newBuilding);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Building> getBuildingById(@PathVariable Long id) {
        Building building = buildingService.findBuildingById(id);
        return building != null ? ResponseEntity.ok(building) : ResponseEntity.notFound().build();
    }

    @GetMapping("/address/{address}")
    public ResponseEntity<Building> getBuildingByAddress(@PathVariable String address) {
        Building building = buildingService.findBuildingByAddress(address);
        return building != null ? ResponseEntity.ok(building) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{address}/rooms/available")
    public ResponseEntity<List<Room>> getAvailableRooms(@PathVariable String address) {
        return ResponseEntity.ok(buildingService.checkAvailableRoomsByBuildingAddress(address));
    }
}
