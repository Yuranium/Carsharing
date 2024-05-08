package ru.yuriy.carsharing.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.yuriy.carsharing.enums.CarDrive;

@Getter
@Setter
@Entity
@ToString
@Table(name = "car")
public class Car
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client owner;

    @Column(name = "image")
    private String image;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price_per_min")
    private double price_per_min;

    @Enumerated(EnumType.STRING)
    @Column(name = "drive")
    private CarDrive carDrive;

    @Column(name = "engine_capacity")
    private double engine_capacity;

    @Column(name = "engine_power")
    private double engine_power;

    @Column(name = "seat_heating")
    private boolean seat_heating;
}