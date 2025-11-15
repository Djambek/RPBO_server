package ru.mtuci.Entities;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Table(name ="aircrafts")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String model;
    private int capacity;

    public Aircraft(String model, int capacity) {
        this.model = model;
        this.capacity = capacity;
    }

}
