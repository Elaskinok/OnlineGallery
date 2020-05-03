package by.bsuir.onlinegallery.service;

import by.bsuir.onlinegallery.profile.UserProfile;

public interface UserAuthorizationService {
    String authorize(UserProfile profile);
}
