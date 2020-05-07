package by.bsuir.OnlineGallery.controller;

import by.bsuir.OnlineGallery.model.Album;
import by.bsuir.OnlineGallery.payload.AlbumRequest;
import by.bsuir.OnlineGallery.payload.ApiResponse;
import by.bsuir.OnlineGallery.payload.PagedResponse;
import by.bsuir.OnlineGallery.sercurity.CurrentUser;
import by.bsuir.OnlineGallery.sercurity.UserPrincipal;
import by.bsuir.OnlineGallery.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {
    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public PagedResponse<?> getAlbums(@CurrentUser UserPrincipal userPrincipal) {
        return albumService.findAlbumsCreatedBy(userPrincipal.getUsername());
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
}
