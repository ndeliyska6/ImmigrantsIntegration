package com.example.demo.repositories;

import com.example.demo.entities.Building;
import com.example.demo.entities.Room;
import com.example.demo.enums.ImmigrantStatus;
import com.example.demo.enums.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("SELECT room from Room room where room.roomStatus = :roomStatus and room.building.buildingAddress = :buildingAddress ")
    List<Room> findByBuildingAddressAndRoomStatus(String buildingAddress, RoomStatus roomStatus);

    @Query("SELECT room from Room room where room.roomNumber = :roomNumber and room.building.buildingAddress = :buildingAddress ")
    Room findByBuildingAddressAndRoomNumber(String buildingAddress, Integer roomNumber);

    @Query("SELECT room from Room room where room.roomNumber = :roomNumber ")
    Optional<Room> findByRoomNumber(Integer roomNumber);

    @Query("SELECT COUNT(r) FROM Room r WHERE r.building = :building")
    long countByBuilding(@Param("building") Building building);

    @Query("SELECT COUNT(r) FROM Room r WHERE r.building = :building AND r.roomStatus = :status")
    long countByBuildingAndRoomStatus(@Param("building") Building building, @Param("status") RoomStatus status);

    @Query("SELECT r FROM Room r JOIN FETCH r.immigrants i WHERE i.immigrantStatus = :status")
    List<Room> findAllRoomsWithAccommodatedImmigrants(@Param("status") ImmigrantStatus status);

    @Query("SELECT DISTINCT r FROM Room r JOIN FETCH r.immigrants i WHERE r.building.buildingAddress = :address AND i.immigrantStatus = :status")
    List<Room> findRoomsByAddressWithAccommodatedImmigrants(@Param("address") String address, @Param("status") ImmigrantStatus status);
}




