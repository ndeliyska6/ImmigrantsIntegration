package com.example.demo.services;

import com.example.demo.entities.Immigrant;
import com.example.demo.entities.LeavingImmigrant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ImmigrantService {
    List<Immigrant> findAllImmigrants();
    Immigrant findImmigrantById(Long id);
    Immigrant registerImmigrant(Immigrant immigrant);
//    Immigrant updateImmigrant(Long id, Immigrant Immigrant);
    List<LeavingImmigrant> updateIfLeaving(List<LeavingImmigrant> leavingImmigrants);
    void deleteImmigrant(Long id);
    List<Immigrant> findImmigrantsByRoomAndBuilding(Integer roomNumber, String buildingAddress);
}
