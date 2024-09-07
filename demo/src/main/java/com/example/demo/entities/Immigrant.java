package com.example.demo.entities;

import com.example.demo.enums.ImmigrantStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Immigrant {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "nationality")
    private String nationality;
    @Column(name = "gender")
    private String gender;
    @Column(name = "age")
    private Integer age;
    @Column(name = "entry_date")
    private LocalDate entryDate;
    @Column(name = "leaving_date", nullable = true)
    private LocalDate leavingDate;
    @ManyToOne
    @JoinColumn(name = "room_number", referencedColumnName = "room_number")
    @JsonBackReference
    private Room room;

    @Enumerated(value=EnumType.STRING)
    @Column(name = "status")
    private ImmigrantStatus immigrantStatus;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDate getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(LocalDate leavingDate) {
        this.leavingDate = leavingDate;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public ImmigrantStatus getImmigrantStatus() {
        return immigrantStatus;
    }

    public void setImmigrantStatus(ImmigrantStatus ImmigrantStatus) {
        this.immigrantStatus = ImmigrantStatus;
    }


}
