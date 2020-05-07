package by.bsuir.OnlineGallery.service;

import by.bsuir.OnlineGallery.exception.ResourceNotFoundException;
import by.bsuir.OnlineGallery.model.Album;
import by.bsuir.OnlineGallery.model.Image;
import by.bsuir.OnlineGallery.model.User;
import by.bsuir.OnlineGallery.payload.AlbumRequest;
import by.bsuir.OnlineGallery.payload.AlbumResponse;
import by.bsuir.OnlineGallery.payload.ImageResponse;
import by.bsuir.OnlineGallery.payload.PagedResponse;
import by.bsuir.OnlineGallery.repository.AlbumRepository;
import by.bsuir.OnlineGallery.repository.ImageRepository;
import by.bsuir.OnlineGallery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

    public PagedResponse<AlbumResponse> findAlbumsCreatedBy(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        List<Album> albums = albumRepository.findAlbumById(user.getId());

        List<AlbumResponse> albumResponses = toAlbumResponse(albums);
        return new PagedResponse<>(albumResponses, 1, albumResponses.size(), 1, true);
    }

    public Album createAlbum(AlbumRequest albumRequest) {
        Album album = toAlbumModel(albumRequest);
        return albumRepository.save(album);
    }

    public boolean existsByAlbumName(String name) {
        return albumRepository.existsByName(name);
    }

    private Album toAlbumModel(AlbumRequest albumRequest) {
        Album album = new Album();
        album.setName(albumRequest.getName());
        album.setDescription(albumRequest.getDescription());
        album.setPrivate(albumRequest.getPrivate());
        album.setImages(Collections.emptyList());

        return album;
    }

    private List<AlbumResponse> toAlbumResponse(List<Album> albums) {
        List<AlbumResponse> albumResponses = new ArrayList<>();
        for (Album album : albums) {
            List<Image> images = imageRepository.findAllByAlbum(album);

            List<ImageResponse> imageResponses = new ArrayList<>();
            for (Image image : images) {
//                TODO: make a byte-string conversion
                ImageResponse imageResponse = new ImageResponse(image.getId(),
                        image.getName(), Arrays.toString(image.getByteArray()), image.isPrivate());
                imageResponses.add(imageResponse);
            }
            albumResponses.add(new AlbumResponse(album.getId(), album.getName(), imageResponses, album.getCreatedBy()));
        }

        return albumResponses;
    }
}
