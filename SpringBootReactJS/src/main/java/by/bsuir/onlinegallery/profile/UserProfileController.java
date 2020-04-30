package by.bsuir.onlinegallery.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user-profile")
@CrossOrigin("*")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping
    public List<UserProfile> getUserProfiles() {
        return userProfileService.getUserProfiles();
    }

    @PostMapping(
            path = "{userProfileId}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadUserProfileImage(@PathVariable("userProfileId") UUID userProfileId,
                                       @RequestParam("file") MultipartFile file) {
        userProfileService.uploadUserProfileImage(userProfileId, file);
    }

    /*todo make it POST with MediaType*/
    @GetMapping(
            path = "registration/user"
/*            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE*/
    )
    public void registerUser(/*@RequestParam("username") String username,
                             @RequestParam("password") String password*/) {

        UUID uuid = UUID.randomUUID();
        UserProfile profile1 = new UserProfile(uuid, "username1000", null);
        userProfileService.saveProfile(profile1);

        uuid = UUID.randomUUID();
        UserProfile profile2 = new UserProfile(uuid, "username2000", null);
        userProfileService.saveProfile(profile2);

        uuid = UUID.randomUUID();
        UserProfile profile3 = new UserProfile(uuid, "username3000", null);
        userProfileService.saveProfile(profile3);

        List<UserProfile> userProfiles = userProfileService.getUserProfiles();

        for (UserProfile profile : userProfiles) {
            System.out.println(profile.getUserProfileId() + " " + profile.getUsername());
        }
    }

    @GetMapping("{userProfileId}/image/download")
    public byte[] downloadUserProfileImage(@PathVariable("userProfileId") UUID userProfileId) {
        System.out.println(userProfileId);
        return userProfileService.downloadUserProfileImage(userProfileId);
    }
}
