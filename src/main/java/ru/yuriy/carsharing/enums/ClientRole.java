package ru.yuriy.carsharing.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClientRole
{
    GUEST("ROLE_GUEST"), USER("ROLE_USER"), ADMIN("ROLE_ADMIN");

    private final String role;
}