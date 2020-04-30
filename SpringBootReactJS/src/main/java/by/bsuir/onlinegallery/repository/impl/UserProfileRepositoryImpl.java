package by.bsuir.onlinegallery.repository.impl;

import by.bsuir.onlinegallery.datastore.FakeUserProfileDataStore;
import by.bsuir.onlinegallery.profile.UserProfile;
import by.bsuir.onlinegallery.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserProfileRepositoryImpl implements UserProfileRepository {

    private final FakeUserProfileDataStore fakeUserProfileDataStore;

    @Autowired
    public UserProfileRepositoryImpl(FakeUserProfileDataStore fakeUserProfileDataStore) {
        this.fakeUserProfileDataStore = fakeUserProfileDataStore;
    }

    @Override
    public void add(UserProfile profile) {
        fakeUserProfileDataStore.uploadProfile(profile);
    }

    @Override
    public List<UserProfile> findAll() {
        return fakeUserProfileDataStore.getUserProfiles();
    }
}
