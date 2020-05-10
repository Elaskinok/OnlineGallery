package by.bsuir.OnlineGallery.controller;

import by.bsuir.OnlineGallery.model.Album;
import by.bsuir.OnlineGallery.payload.AlbumRequest;
import by.bsuir.OnlineGallery.payload.AlbumResponse;
import by.bsuir.OnlineGallery.payload.ApiResponse;
import by.bsuir.OnlineGallery.payload.PagedResponse;
import by.bsuir.OnlineGallery.payload.UserAlbumResponse;
import by.bsuir.OnlineGallery.sercurity.CurrentUser;
import by.bsuir.OnlineGallery.sercurity.UserPrincipal;
import by.bsuir.OnlineGallery.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static by.bsuir.OnlineGallery.service.ApplicationConstants.DEFAULT_PAGE_NUMBER;
import static by.bsuir.OnlineGallery.service.ApplicationConstants.DEFAULT_PAGE_SIZE;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {
    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/album/{albumId}")
    @PreAuthorize("hasRole('USER')")
    public UserAlbumResponse findUserAlbumById(@CurrentUser UserPrincipal userPrincipal,
                                               @PathVariable Long albumId) {

        return albumService.findUserAlbumById(userPrincipal, albumId);
    }

    @GetMapping("/all-user-albums")
    @PreAuthorize("hasRole('USER')")
    public PagedResponse<AlbumResponse> findUserAlbums(@CurrentUser UserPrincipal userPrincipal,
                                                       @RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NUMBER) int page,
                                                       @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) int size) {

        return albumService.findAlbumsByCreatedBy(userPrincipal, page, size);
    }

    @PostMapping("/create-new-album")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createAlbum(@Valid @RequestBody AlbumRequest albumRequest) {
        if (albumService.existsByAlbumName(albumRequest.getName())) {
            return new ResponseEntity<>(new ApiResponse(false, "Album is already existed!"),
                    HttpStatus.BAD_REQUEST);
        }

        Album savedAlbum = albumService.createAlbum(albumRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{albumId}")
                .buildAndExpand(savedAlbum.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Album has been created successfully"));
    }

    @PutMapping("/toggle-private/{albumId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> toggleAlbumPrivacy(@CurrentUser UserPrincipal userPrincipal,
                                                @PathVariable(value = "albumId") Long albumId) {

        boolean toggled = albumService.toggleAlbumPrivacyById(userPrincipal, albumId);

        if(!toggled) {
                return new ResponseEntity<>(new ApiResponse(false, "Can not toggle!"),
                        HttpStatus.BAD_REQUEST);
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{albumId}")
                .buildAndExpand(albumId).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Album privacy has been changed successfully"));
    }

    @DeleteMapping("/delete-album/{albumId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteAlbumById(@CurrentUser UserPrincipal userPrincipal,
                                             @PathVariable Long albumId) {
        Album album = albumService.deleteUserAlbumById(userPrincipal, albumId);

        if (album == null) {
            return new ResponseEntity<>(
                    new ApiResponse(false,
                            "No possibility to delete the album"),
                    HttpStatus.BAD_REQUEST);
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{albumId}")
                .buildAndExpand(album.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Album has been deleted successfully"));
    }

}
