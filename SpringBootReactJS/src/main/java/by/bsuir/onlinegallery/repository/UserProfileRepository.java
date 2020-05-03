package by.bsuir.onlinegallery.repository;

import by.bsuir.onlinegallery.profile.UserProfile;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface UserProfileRepository {
    void add(UserProfile profile);

    Optional<UserProfile> findByUsername(String username);

    List<UserProfile> findAllProfiles();

    Map<UUID, String> findAllPasswords();
}
