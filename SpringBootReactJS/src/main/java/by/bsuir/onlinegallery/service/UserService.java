package by.bsuir.onlinegallery.service;

import by.bsuir.onlinegallery.profile.UserProfile;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserProfile> findAll();

    Optional<UserProfile> findById(Integer id);
}
