package dev.waamir.hotelaluraapi.application.enumeration;

public enum VerificationType {
    ACCOUNT("ACCOUNT");

    private final String type;

    VerificationType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
