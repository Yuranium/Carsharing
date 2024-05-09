package ru.yuriy.carsharing.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClientRole
{
    GUEST("GUEST"), CLIENT("CLIENT"), ADMIN("ADMIN");

    private final String role;
}