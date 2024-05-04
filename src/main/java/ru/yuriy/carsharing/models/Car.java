package ru.yuriy.carsharing.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "Cars")
public class Car
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "img")
    private String img;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}