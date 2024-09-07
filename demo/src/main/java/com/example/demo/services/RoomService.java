package com.example.demo.services;

import com.example.demo.entities.Room;
import com.example.demo.enums.RoomStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {
    List<Room> findAllRooms();
    Room saveRoom(Long buildingId, Room room);
    Room findRoomById(Long id);
    Room findRoomByNumber(Integer roomNumber);
    void updateBuildingStatus(Long buildingId);
    RoomStatus checkRoomStatus(Long id);
    List<Room> getByAddressWithAccommodated(String address);
}