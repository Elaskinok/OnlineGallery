package by.bsuir.OnlineGallery.controller;

import by.bsuir.OnlineGallery.exception.ResourceNotFoundException;
import by.bsuir.OnlineGallery.model.User;
import by.bsuir.OnlineGallery.payload.AlbumResponse;
import by.bsuir.OnlineGallery.payload.ImageResponse;
import by.bsuir.OnlineGallery.payload.PagedResponse;
import by.bsuir.OnlineGallery.payload.UserProfile;
import by.bsuir.OnlineGallery.payload.UserSummary;
import by.bsuir.OnlineGallery.repository.UserRepository;
import by.bsuir.OnlineGallery.sercurity.CurrentUser;
import by.bsuir.OnlineGallery.sercurity.UserPrincipal;
import by.bsuir.OnlineGallery.service.AlbumService;
import by.bsuir.OnlineGallery.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static by.bsuir.OnlineGallery.service.ApplicationConstants.*;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserRepository userRepository;
    private final AlbumService albumService;
    private final ImageService imageService;

    @Autowired
    public UserController(UserRepository userRepository,
                          AlbumService albumService, ImageService imageService) {

        this.userRepository = userRepository;
        this.albumService = albumService;
        this.imageService = imageService;
    }

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
    }

    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        return new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt(), 0L, 0L);
    }

    @GetMapping("/users/{username}/albums")
    public PagedResponse<AlbumResponse> findAllPublicAlbumsByCreatedBy(@PathVariable(value = "username") String username,
                                                                       @RequestParam(
                                                                               value = "page",
                                                                               defaultValue = DEFAULT_PAGE_NUMBER) int page,
                                                                       @RequestParam(
                                                                               value = "size",
                                                                               defaultValue = DEFAULT_PAGE_SIZE) int size) {

        return albumService.findAlbumsByCreatedBy(username, page, size);
    }

    @GetMapping("/users/{username}/images")
    public PagedResponse<ImageResponse> findAllPublicImagesByUsername(@PathVariable(value = "username") String username,
                                                                      @RequestParam(
                                                                              value = "page",
                                                                              defaultValue = DEFAULT_PAGE_NUMBER) int page,
                                                                      @RequestParam(
                                                                              value = "size",
                                                                              defaultValue = DEFAULT_PAGE_SIZE) int size) {

        return imageService.findImageByUsername(username, page, size);
    }
}
