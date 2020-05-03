package by.bsuir.onlinegallery.service.validator;

import by.bsuir.onlinegallery.profile.UserProfile;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import static by.bsuir.onlinegallery.service.validator.UserAuthorizationValidator.*;
import static by.bsuir.onlinegallery.service.validator.UserAuthorizationValidator.ValidationResult.*;

public interface UserAuthorizationValidator
        extends Function<UserProfile, ValidationResult> {

    static UserAuthorizationValidator isUsernameExisted(List<UserProfile> profiles) {
        return profile -> profiles.stream()
                .anyMatch(p -> p.getUsername().equals(profile.getUsername())) ?
                SUCCESS : USERNAME_NOT_EXIST;
    }

    static UserAuthorizationValidator isPasswordMatched(Map<UUID, String> passwords) {
        return profile -> passwords.containsKey(profile.getUserProfileId()) &&
                passwords.get(profile.getUserProfileId()).equals(profile.getPassword()) ?
                SUCCESS : PASSWORD_NOT_MATCH;
    }

    default UserAuthorizationValidator and (UserAuthorizationValidator other) {
        return profile -> {
            ValidationResult result = this.apply(profile);
            return result.equals(SUCCESS) ? other.apply(profile) : result;
        };
    }

    enum ValidationResult {
        SUCCESS,
        USERNAME_NOT_EXIST,
        PASSWORD_NOT_MATCH
    }
}
