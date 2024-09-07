package com.example.demo.entities;

import java.time.LocalDate;

public class LeavingImmigrant {
    private Long id;
    private LocalDate leavingDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(LocalDate leavingDate) {
        this.leavingDate = leavingDate;
    }

    public LeavingImmigrant(Long id, LocalDate leavingDate) {
        this.id = id;
        this.leavingDate = leavingDate;
    }


}
