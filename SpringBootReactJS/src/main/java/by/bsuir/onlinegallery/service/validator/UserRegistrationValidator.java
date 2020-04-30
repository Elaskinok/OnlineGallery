package by.bsuir.onlinegallery.service.validator;

import by.bsuir.onlinegallery.profile.UserProfile;

import java.util.List;
import java.util.function.Function;

import static by.bsuir.onlinegallery.service.validator.UserRegistrationValidator.*;
import static by.bsuir.onlinegallery.service.validator.UserRegistrationValidator.ValidationResult.*;

public interface UserRegistrationValidator extends Function<UserProfile, ValidationResult> {

    static UserRegistrationValidator isUsernameExisted(List<UserProfile> profiles) {
        return profile -> profiles.stream()
                .noneMatch(p -> p.getUsername().equals(profile)) ?
                SUCCESS : USERNAME_ALREADY_EXISTS;
    }

    enum ValidationResult {
        SUCCESS,
        USERNAME_ALREADY_EXISTS
    }
}
