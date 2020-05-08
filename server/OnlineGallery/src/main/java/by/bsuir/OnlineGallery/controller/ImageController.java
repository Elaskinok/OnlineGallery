package by.bsuir.OnlineGallery.controller;

import by.bsuir.OnlineGallery.model.Image;
import by.bsuir.OnlineGallery.payload.ApiResponse;
import by.bsuir.OnlineGallery.payload.ImageRequest;
import by.bsuir.OnlineGallery.payload.UserImageResponse;
import by.bsuir.OnlineGallery.sercurity.CurrentUser;
import by.bsuir.OnlineGallery.sercurity.UserPrincipal;
import by.bsuir.OnlineGallery.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/image/{imageId}")
    @PreAuthorize("hasRole('USER')")
    public UserImageResponse findImageById(@CurrentUser UserPrincipal userPrincipal,
                                          @PathVariable Long imageId) {
        return imageService.findImageById(userPrincipal, imageId);
    }

    @GetMapping("/all-user-images")
    @PreAuthorize("hasRole('USER')")
    public List<UserImageResponse> findAllUserImages(@CurrentUser UserPrincipal userPrincipal) {
        return imageService.findAllUserImages(userPrincipal);
    }

    @PostMapping("/add-new-image")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addImage(@Valid @RequestBody ImageRequest imageRequest) {
        Image image = imageService.addImage(imageRequest);

//        TODO: add validation before return
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{imageId}")
                .buildAndExpand(image.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Image has been saved successfully!"));
    }
}
