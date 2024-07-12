package org.acme.responses;

public class JsonResponse {
    public boolean success;
    public String message;
    public Object data;

    public JsonResponse(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public JsonResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
