package ru.yuriy.carsharing.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClientRole
{
    GUEST("ROLE_GUEST"), CLIENT("ROLE_CLIENT"), ADMIN("ROLE_ADMIN");

    private final String role;
}