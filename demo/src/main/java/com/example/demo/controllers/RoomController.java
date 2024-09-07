package com.example.demo.controllers;

import com.example.demo.entities.Room;
import com.example.demo.enums.RoomStatus;
import com.example.demo.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

//    @GetMapping("/freeRooms")
//    public List<Room> getAllFreeRooms( Integer roomNumber){
//        return roomService.getAllFreeRooms();
//    }
    @GetMapping("/get")
    public ResponseEntity<List<Room>> getAllRooms() {
        return ResponseEntity.ok(roomService.findAllRooms());
    }

    @PostMapping("/save")
    public ResponseEntity<Room> createRoom(@RequestParam Long buildingId, @RequestBody Room room) {
        Room newRoom = roomService.saveRoom(buildingId, room);
        return newRoom != null ? ResponseEntity.ok(newRoom) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        Room room = roomService.findRoomById(id);
        return room != null ? ResponseEntity.ok(room) : ResponseEntity.notFound().build();
    }

    @GetMapping("/number/{roomNumber}")
    public Room getRoomByNumber(@PathVariable Integer roomNumber) {
        Room room = roomService.findRoomByNumber(roomNumber);
        return room;
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<RoomStatus> checkRoomStatus(@PathVariable Long id) {
        RoomStatus status = roomService.checkRoomStatus(id);
        return status != null ? ResponseEntity.ok(status) : ResponseEntity.notFound().build();
    }

    @GetMapping("get-by-address/{address}")
    public List<Room> getRoomsByAddress(@PathVariable String address) {
        return roomService.getByAddressWithAccommodated(address);
    }
}
