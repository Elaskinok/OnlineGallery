package by.bsuir.onlinegallery.profile;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class UserProfile {

    private final UUID userProfileId;
    private final String username;
    private final String password;
    private String userProfileImageLink; // S3 key

    public UserProfile(UUID userProfileId, String username, String password, String userProfileImageLink) {
        this.userProfileId = userProfileId;
        this.username = username;
        this.password = password;
        this.userProfileImageLink = userProfileImageLink;
    }

    public UUID getUserProfileId() {
        return userProfileId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Optional<String> getUserProfileImageLink() {
        return Optional.ofNullable(userProfileImageLink);
    }

    public void setUserProfileImageLink(String userProfileImageLink) {
        this.userProfileImageLink = userProfileImageLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserProfile that = (UserProfile) o;

        if (!Objects.equals(userProfileId, that.userProfileId))
            return false;
        if (!Objects.equals(username, that.username)) return false;
        return Objects.equals(userProfileImageLink, that.userProfileImageLink);
    }

    @Override
    public int hashCode() {
        int result = userProfileId != null ? userProfileId.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (userProfileImageLink != null ? userProfileImageLink.hashCode() : 0);
        return result;
    }
}
