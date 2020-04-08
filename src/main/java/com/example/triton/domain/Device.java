package com.example.triton.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long quantity;
    private Date date;

    public Device() {
    }

    @ManyToOne
    @JoinColumn(name = "list_id", nullable = false)
    private ListDevice listDevice;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ListDevice getListDevice() {
        return listDevice;
    }

    public void setListDevice(ListDevice listDevice) {
        this.listDevice = listDevice;
    }
}
