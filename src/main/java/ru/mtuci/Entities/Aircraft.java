package ru.mtuci.Entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aircraft {
    private UUID id;
    private String model;
    private int capacity;

//    public Aircraft() {}
//
//    public Aircraft(UUID id, String model, int capacity) {
//        this.id = id;
//        this.model = model;
//        this.capacity = capacity;
//    }

//    // Геттеры и сеттеры
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//    public String getModel() { return model; }
//    public void setModel(String model) { this.model = model; }
//    public int getCapacity() { return capacity; }
//    public void setCapacity(int capacity) { this.capacity = capacity; }
}
