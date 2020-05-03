package by.bsuir.onlinegallery.service;

import by.bsuir.onlinegallery.profile.UserProfile;
import by.bsuir.onlinegallery.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserProfileRepository repository;

    @Autowired
    public UserServiceImpl(UserProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<UserProfile> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<UserProfile> findAll() {
        return repository.findAllProfiles();
    }
}
