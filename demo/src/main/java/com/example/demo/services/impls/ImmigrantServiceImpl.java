package com.example.demo.services.impls;


import com.example.demo.entities.Immigrant;
import com.example.demo.entities.LeavingImmigrant;
import com.example.demo.entities.Room;
import com.example.demo.enums.ImmigrantStatus;
import com.example.demo.enums.RoomStatus;
import com.example.demo.repositories.BuildingRepository;
import com.example.demo.repositories.ImmigrantRepository;
import com.example.demo.repositories.RoomRepository;
import com.example.demo.services.ImmigrantService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ImmigrantServiceImpl implements ImmigrantService {

    @Autowired
    private ImmigrantRepository immigrantRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomServiceImpl roomService;
    private BuildingRepository buildingRepository;

    @Override
    public List<Immigrant> findAllImmigrants() {
        return immigrantRepository.findAll();
    }

    @Override
    public Immigrant findImmigrantById(Long id) {
        return immigrantRepository.findById(id).orElse(null);
    }

    @Override
    public Immigrant registerImmigrant(Immigrant immigrant) {
        String immigrantBuildingAddress = immigrant.getRoom().getBuilding().getBuildingAddress();
        Integer immigrantRoomNumber = immigrant.getRoom().getRoomNumber();
        Room room = roomRepository.findByBuildingAddressAndRoomNumber(immigrantBuildingAddress, immigrantRoomNumber);
        room.setRoomCapacity(room.getRoomCapacity() - 1);
        if (room.getRoomCapacity() == 0) {
            room.setRoomStatus(RoomStatus.OCCUPIED);
        }
        immigrant.setRoom(room);
        roomRepository.save(room);
        roomService.updateBuildingStatus(room.getBuilding().getId());
        immigrant.setImmigrantStatus(ImmigrantStatus.ACCOMMODATED);
        return immigrantRepository.save(immigrant);
    }

    @Override
    public List<LeavingImmigrant> updateIfLeaving(List<LeavingImmigrant> leavingImmigrant) {
        leavingImmigrant.forEach(immigrant -> {
            Immigrant existingImmigrant = immigrantRepository.findById(immigrant.getId()).orElseThrow(() ->
                    new EntityNotFoundException("Immigrant not found with ID: " + immigrant.getId()));

            existingImmigrant.setLeavingDate(immigrant.getLeavingDate());
            existingImmigrant.setImmigrantStatus(ImmigrantStatus.LEFT);

            Room room = existingImmigrant.getRoom();
            room.setRoomCapacity(room.getRoomCapacity() + 1);

            // If the room was marked as occupied, check if it's now free
            if (room.getRoomStatus() == RoomStatus.OCCUPIED) {
                room.setRoomStatus(RoomStatus.FREE);
            }
            roomService.updateBuildingStatus(room.getBuilding().getId());
            roomRepository.save(room);
            immigrantRepository.save(existingImmigrant);
        });
        // Check if the immigrant with the given ID exists

        return leavingImmigrant;
    }

    @Override
    public void deleteImmigrant(Long id) {
        Room room = immigrantRepository.findById(id).orElse(new Immigrant()).getRoom();
        room.setRoomCapacity(room.getRoomCapacity() + 1);
        roomRepository.save(room);
        immigrantRepository.deleteById(id);


    }

    @Override
    public List<Immigrant> findImmigrantsByRoomAndBuilding(Integer roomNumber, String buildingAddress) {
        Room room = roomRepository.findByBuildingAddressAndRoomNumber(buildingAddress, roomNumber);
        return room != null ? room.getImmigrants() : null;
    }
}
