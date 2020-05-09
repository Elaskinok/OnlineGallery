package by.bsuir.OnlineGallery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class PermissionDeniedException extends RuntimeException {
    private final String username;
    private final String resourceName;
    private final String reason;

    public PermissionDeniedException(String username, String resourceName, String reason) {
        super(String.format("The resource[%s] forbidden for user[%s], reason: %s",
                resourceName, username, reason));

        this.username = username;
        this.resourceName = resourceName;
        this.reason = reason;
    }

    public String getUsername() {
        return username;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getReason() {
        return reason;
    }
}
