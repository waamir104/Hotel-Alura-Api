package dev.waamir.hotelaluraapi.domain.enumeration;

public enum Roles {
    GUEST("GUEST"),
    EMPLOYEE("EMPLOYEE"),
    ADMIN("ADMIN");

    private final String name;

    Roles(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
