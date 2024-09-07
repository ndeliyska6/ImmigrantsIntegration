package com.example.demo.services.impls;

import com.example.demo.entities.Building;
import com.example.demo.entities.Room;
import com.example.demo.enums.BuildingStatus;
import com.example.demo.enums.ImmigrantStatus;
import com.example.demo.enums.RoomStatus;
import com.example.demo.repositories.BuildingRepository;
import com.example.demo.repositories.RoomRepository;
import com.example.demo.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Override
    public List<Room> findAllRooms() {
        return roomRepository.findAllRoomsWithAccommodatedImmigrants(ImmigrantStatus.ACCOMMODATED);
    }

    @Override
    public Room saveRoom(Long buildingId, Room room) {
        Building building = buildingRepository.findById(buildingId).orElse(null);
        if (building != null) {
            room.setBuilding(building);
            Room savedRoom = roomRepository.save(room);

            // Update the building status after saving the room
            updateBuildingStatus(buildingId);

            return savedRoom;
        }
        return null;
    }

    @Override
    public void updateBuildingStatus(Long buildingId) {
        Building building = buildingRepository.findById(buildingId).orElse(null);

        long totalRooms = roomRepository.countByBuilding(building);
        long occupiedRooms = roomRepository.countByBuildingAndRoomStatus(building, RoomStatus.OCCUPIED);

        if (occupiedRooms == totalRooms) {
            building.setBuildingStatus(BuildingStatus.FULL); // All rooms are occupied
        } else {
            building.setBuildingStatus(BuildingStatus.AVAILABLE);
        }

        buildingRepository.save(building);
    }

    @Override
    public Room findRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    @Override
    public Room findRoomByNumber(Integer roomNumber) {
        return roomRepository.findByRoomNumber(roomNumber).orElse(null);
    }

    @Override
    public RoomStatus checkRoomStatus(Long id) {
        Room room = findRoomById(id);
        return room != null ? room.getRoomStatus() : null;
    }

    @Override
    public List<Room> getByAddressWithAccommodated(String address) {
        return roomRepository.findRoomsByAddressWithAccommodatedImmigrants(address, ImmigrantStatus.ACCOMMODATED);
    }


}
