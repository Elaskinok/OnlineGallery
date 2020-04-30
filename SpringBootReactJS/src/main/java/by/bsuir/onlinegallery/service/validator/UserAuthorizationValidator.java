package by.bsuir.onlinegallery.service.validator;

import by.bsuir.onlinegallery.profile.UserProfile;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import static by.bsuir.onlinegallery.service.validator.UserAuthorizationValidator.ValidationResult;
import static by.bsuir.onlinegallery.service.validator.UserAuthorizationValidator.ValidationResult.PASSWORD_NOT_MATCH;
import static by.bsuir.onlinegallery.service.validator.UserAuthorizationValidator.ValidationResult.SUCCESS;
import static by.bsuir.onlinegallery.service.validator.UserAuthorizationValidator.ValidationResult.USERNAME_NOT_EXISTED;

public interface UserAuthorizationValidator
        extends Function<UserProfile, ValidationResult> {

    static UserAuthorizationValidator isUsernameValid(List<UserProfile> profiles) {
        return profile -> profiles.stream()
                .anyMatch(p -> p.getUsername().equals(profile.getUsername())) ?
                SUCCESS : USERNAME_NOT_EXISTED;
    }

    static UserAuthorizationValidator isPasswordValid(Map<UUID, String> passwords) {
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
        USERNAME_NOT_EXISTED,
        PASSWORD_NOT_MATCH
    }
}
