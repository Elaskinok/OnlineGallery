package by.bsuir.onlinegallery.service.impl;

import by.bsuir.onlinegallery.profile.UserProfile;
import by.bsuir.onlinegallery.repository.UserProfileRepository;
import by.bsuir.onlinegallery.security.ProfilePrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserProfileRepository repository;

    @Autowired
    public CustomUserDetailService(UserProfileRepository repository) {
        this.repository = repository;
    }

    /**
     * @param username is email or plain username
     * @return
     * @throws UsernameNotFoundException when user is not found by username or email
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserProfile profile = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("There is no possibility to find: " + username));

        return ProfilePrinciple.create(profile);
    }

    public UserDetails loadUserById(UUID profileId) {
        UserProfile profile = repository.findAllProfiles().stream()
                .filter(p -> p.getUserProfileId().equals(profileId))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("There is no:" + profileId));

        return ProfilePrinciple.create(profile);
    }
}
