package com.example.demo.controllers;

import com.example.demo.entities.Immigrant;
import com.example.demo.entities.LeavingImmigrant;
import com.example.demo.services.ImmigrantService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/immigrant")
public class ImmigrantController {
    @Autowired
    private ImmigrantService immigrantService;

    @GetMapping
    public ResponseEntity<List<Immigrant>> getAllImmigrants() {
        return ResponseEntity.ok(immigrantService.findAllImmigrants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Immigrant> getImmigrantById(@PathVariable Long id) {
        Immigrant immigrant = immigrantService.findImmigrantById(id);
        return immigrant != null ? ResponseEntity.ok(immigrant) : ResponseEntity.notFound().build();
    }
//http://localhost:8080/immigrant/leaving-date
    @PostMapping("/register")
    public ResponseEntity<Immigrant> registerImmigrant(@RequestBody Immigrant immigrant) {
        try {
            Immigrant registeredImmigrant = immigrantService.registerImmigrant(immigrant);
            return ResponseEntity.ok(registeredImmigrant);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/leaving-date")
    public List<LeavingImmigrant> updateLeavingDate(
            @RequestBody List<LeavingImmigrant> leavingImmigrants) {
        return immigrantService.updateIfLeaving(leavingImmigrants);
    }

    @GetMapping("/room")
    public ResponseEntity<List<Immigrant>> getImmigrantsByRoomAndBuilding(
            @RequestParam Integer roomNumber,
            @RequestParam String buildingAddress) {
        List<Immigrant> immigrants = immigrantService.findImmigrantsByRoomAndBuilding(roomNumber, buildingAddress);
        return immigrants != null ? ResponseEntity.ok(immigrants) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImmigrant(@PathVariable Long id) {
        immigrantService.deleteImmigrant(id);
        return ResponseEntity.noContent().build();
    }
}
