package by.bsuir.onlinegallery.repository;

import by.bsuir.onlinegallery.profile.UserProfile;

import java.util.List;

public interface UserProfileRepository {
    void add(UserProfile profile);

    List<UserProfile> findAll();
}
