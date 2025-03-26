package com.fornesb.backend_ecommerce.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Roles {
    USER,
    ADMIN,
    SELLER,
    REVIEWER,
    PROVIDER;

    @JsonCreator
    public static Roles fromString(String value) {
        return Roles.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toJson() {
        return name();
    }
}