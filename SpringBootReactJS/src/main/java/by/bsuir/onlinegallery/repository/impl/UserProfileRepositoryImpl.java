package by.bsuir.onlinegallery.repository.impl;

import by.bsuir.onlinegallery.datastore.FakePasswordStore;
import by.bsuir.onlinegallery.datastore.FakeUserProfileDataStore;
import by.bsuir.onlinegallery.profile.UserProfile;
import by.bsuir.onlinegallery.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserProfileRepositoryImpl implements UserProfileRepository {

    private final FakeUserProfileDataStore fakeUserProfileDataStore;
    private final FakePasswordStore fakePasswordStore;

    @Autowired
    public UserProfileRepositoryImpl(FakeUserProfileDataStore fakeUserProfileDataStore,
                                     FakePasswordStore fakePasswordStore) {

        this.fakeUserProfileDataStore = fakeUserProfileDataStore;
        this.fakePasswordStore = fakePasswordStore;
    }

    @Override
    public void add(UserProfile profile) {
        fakeUserProfileDataStore.uploadProfile(profile);
    }

    @Override
//    TODO searching by username so far
    public Optional<UserProfile> findByUsername(String username) {
        return fakeUserProfileDataStore.getUserProfiles().stream()
                .filter(profile -> profile.getUsername().equals(username))
                .findFirst();
    }

    @Override
    public List<UserProfile> findAllProfiles() {
        return fakeUserProfileDataStore.getUserProfiles();
    }

    @Override
    public Map<UUID, String> findAllPasswords() {
        return fakePasswordStore.findAll();
    }
}
