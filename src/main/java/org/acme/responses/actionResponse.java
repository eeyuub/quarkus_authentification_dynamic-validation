package org.acme.responses;


public enum actionResponse {
    SUCCESS("Successefully Action"),
    FAILURE("Failure"),
    ERROR("Error"),
    LOGIN("Login Succeed");
    public final String label;

    private actionResponse(String label) {
        this.label = label;
    }
}
