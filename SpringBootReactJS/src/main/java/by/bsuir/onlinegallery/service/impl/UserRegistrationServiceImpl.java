package by.bsuir.onlinegallery.service.impl;

import by.bsuir.onlinegallery.profile.UserProfile;
import by.bsuir.onlinegallery.repository.UserProfileRepository;
import by.bsuir.onlinegallery.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static by.bsuir.onlinegallery.service.validator.UserRegistrationValidator.ValidationResult;
import static by.bsuir.onlinegallery.service.validator.UserRegistrationValidator.ValidationResult.SUCCESS;
import static by.bsuir.onlinegallery.service.validator.UserRegistrationValidator.isUsernameExisted;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final UserProfileRepository repository;

    @Autowired
    public UserRegistrationServiceImpl(UserProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public String add(UserProfile profile) {
        //Using combinator pattern
        ValidationResult result = isUsernameExisted(repository.findAllProfiles()).apply(profile);
        if (result == SUCCESS) {
            repository.add(profile);
        }
        return result.toString();
    }
}
