package ru.yuriy.carsharing.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CarDrive
{
    FRONT_WHEEL("передний"), REAR_WHEEL("задний"), FULL("полный");

    private final String drive;
}