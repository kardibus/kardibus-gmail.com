package com.example.triton.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ListDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long number;

    @OneToMany(mappedBy = "listDevice", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Device> device = new HashSet<Device>();

    public ListDevice(){}

    public ListDevice(String name, Long number) {
        this.name=name;
        this.number=number;
    }

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

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
