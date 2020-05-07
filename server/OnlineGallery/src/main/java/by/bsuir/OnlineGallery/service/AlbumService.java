package by.bsuir.OnlineGallery.service;

import by.bsuir.OnlineGallery.exception.ResourceNotFoundException;
import by.bsuir.OnlineGallery.model.Album;
import by.bsuir.OnlineGallery.model.Image;
import by.bsuir.OnlineGallery.model.User;
import by.bsuir.OnlineGallery.payload.AlbumRequest;
import by.bsuir.OnlineGallery.payload.PagedResponse;
import by.bsuir.OnlineGallery.repository.AlbumRepository;
import by.bsuir.OnlineGallery.repository.ImageRepository;
import by.bsuir.OnlineGallery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository,
                        ImageRepository imageRepository,
                        UserRepository userRepository) {

        this.albumRepository = albumRepository;
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
    }

    public PagedResponse<Album> findAlbumsCreatedBy(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        List<PagedResponse<Album>> pagedResponseList = new ArrayList<>();

        List<Album> albums = albumRepository.findAlbumById(user.getId());

        for (Album album : albums) {
            List<Image> images = imageRepository.findAllByAlbum(album);
            System.out.println(images);
        }
        System.out.println(albums);
        return null;
    }

    public Album createAlbum(AlbumRequest albumRequest) {
        return null;
    }
}