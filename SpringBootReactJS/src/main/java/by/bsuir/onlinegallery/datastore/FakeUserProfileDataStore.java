package by.bsuir.onlinegallery.datastore;

import by.bsuir.onlinegallery.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {

    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.fromString("adb17c03-1b3d-4140-bb7a-a1ca18a24bdd"), "janetjones", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("02e76c8b-c52f-4c12-9bd1-4a0934943aea"), "antoniojunior", null));
    }

    public List<UserProfile> getUserProfiles() {
        return USER_PROFILES;
    }
}
