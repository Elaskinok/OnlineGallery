package by.bsuir.onlinegallery.service;

import by.bsuir.onlinegallery.profile.UserProfile;
import by.bsuir.onlinegallery.repository.UserProfileRepository;
import by.bsuir.onlinegallery.service.validator.UserAuthorizationValidator;
import by.bsuir.onlinegallery.service.validator.UserAuthorizationValidator.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static by.bsuir.onlinegallery.service.validator.UserAuthorizationValidator.isPasswordMatched;

@Service
public class UserAuthorizationServiceImpl implements UserAuthorizationService {
    private final UserProfileRepository repository;

    @Autowired
    public UserAuthorizationServiceImpl(UserProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public String authorize(UserProfile profile) {

        ValidationResult result = UserAuthorizationValidator
                .isUsernameExisted(repository.findAllProfiles())
                .and(isPasswordMatched(repository.findAllPasswords()))
                .apply(profile);

        return result.toString();
    }
}
