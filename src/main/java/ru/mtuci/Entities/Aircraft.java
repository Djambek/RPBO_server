package ru.mtuci.Entities;

public class Aircraft {
    private Long id;
    private String model;
    private int capacity;

    public Aircraft() {}

    public Aircraft(Long id, String model, int capacity) {
        this.id = id;
        this.model = model;
        this.capacity = capacity;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
}
