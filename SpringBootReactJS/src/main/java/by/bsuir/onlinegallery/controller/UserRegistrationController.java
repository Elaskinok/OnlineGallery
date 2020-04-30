package by.bsuir.onlinegallery.controller;

import by.bsuir.onlinegallery.profile.UserProfile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/registration")
public class UserRegistrationController {
    /*todo make it POST with MediaType*/
    @GetMapping(
            path = "registration/user"
/*            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE*/
    )
    public void registerUser(/*@RequestParam("username") String username,
                             @RequestParam("password") String password*/) {

//        UUID uuid = UUID.randomUUID();
//        UserProfile profile1 = new UserProfile(uuid, "username1000", null);
//        userProfileService.saveProfile(profile1);
//
//        uuid = UUID.randomUUID();
//        UserProfile profile2 = new UserProfile(uuid, "username2000", null);
//        userProfileService.saveProfile(profile2);
//
//        uuid = UUID.randomUUID();
//        UserProfile profile3 = new UserProfile(uuid, "username3000", null);
//        userProfileService.saveProfile(profile3);
//
//        List<UserProfile> userProfiles = userProfileService.getUserProfiles();
//
//        for (UserProfile profile : userProfiles) {
//            System.out.println(profile.getUserProfileId() + " " + profile.getUsername());
//        }
    }
}
