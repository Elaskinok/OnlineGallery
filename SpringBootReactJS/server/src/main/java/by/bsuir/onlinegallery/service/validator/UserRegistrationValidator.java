package by.bsuir.onlinegallery.service.validator;

import by.bsuir.onlinegallery.profile.UserProfile;

import java.util.List;
import java.util.function.Function;

import static by.bsuir.onlinegallery.service.validator.UserRegistrationValidator.*;
import static by.bsuir.onlinegallery.service.validator.UserRegistrationValidator.ValidationResult.*;

public interface UserRegistrationValidator extends Function<UserProfile, ValidationResult> {

    static UserRegistrationValidator isUsernameExisted(List<UserProfile> profiles) {
        return profile -> profiles.stream()
                .noneMatch(p -> p.getUsername().equals(profile.getUsername())) ?
                SUCCESS : USERNAME_ALREADY_EXISTS;
    }

    default UserRegistrationValidator and (UserRegistrationValidator other) {
        return profile -> {
            ValidationResult result = this.apply(profile);
            return result.equals(SUCCESS) ? other.apply(profile) : result;
        };
    }

    enum ValidationResult {
        SUCCESS,
        USERNAME_ALREADY_EXISTS
    }
}
