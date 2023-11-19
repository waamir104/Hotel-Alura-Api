package dev.waamir.hotelaluraapi.application.enumeration;

public enum RoleType {
    GUEST("GUEST"),
    ADMIN("ADMIN"),
    WORKER("WORKER");

    private final String type;

    RoleType(String type) {
        this.type = type;
    }

    public String get() {
        return this.type;
    }
}
