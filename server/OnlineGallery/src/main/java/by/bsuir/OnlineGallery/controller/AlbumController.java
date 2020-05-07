package by.bsuir.OnlineGallery.controller;

import by.bsuir.OnlineGallery.payload.PagedResponse;
import by.bsuir.OnlineGallery.repository.AlbumRepository;
import by.bsuir.OnlineGallery.sercurity.CurrentUser;
import by.bsuir.OnlineGallery.sercurity.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {
    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumController(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @GetMapping
    public PagedResponse<?> getAlbums(@CurrentUser UserPrincipal userPrincipal) {
        return null;
    }
}
