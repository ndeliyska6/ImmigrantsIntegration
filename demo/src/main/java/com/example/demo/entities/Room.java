package com.example.demo.entities;


import com.example.demo.enums.RoomStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_number",unique = true)
    private Integer roomNumber;
    @Column(name = "room_capacity")
    private Integer roomCapacity;
    @ManyToOne
    @JoinColumn(name = "address", referencedColumnName = "address")
    private Building building;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Immigrant> immigrants;

    public List<Immigrant> getImmigrants() {
        return immigrants;
    }

    public void setImmigrants(List<Immigrant> immigrants) {
        this.immigrants = immigrants;
    }

    public Long getId() {
        return id;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(Integer roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    @Enumerated(value=EnumType.STRING)
    @Column(name = "status")
    private RoomStatus roomStatus;



}