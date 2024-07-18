package org.acme.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonResponse {
    public boolean success;
    public String message;
    @JsonProperty("content")
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
