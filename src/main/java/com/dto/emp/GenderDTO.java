package com.dto.emp;

public class GenderDTO {
    private Long id;
    private String name;

    // Constructor vacío
    public GenderDTO() {
    }

    // Constructor con todos los campos
    public GenderDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}