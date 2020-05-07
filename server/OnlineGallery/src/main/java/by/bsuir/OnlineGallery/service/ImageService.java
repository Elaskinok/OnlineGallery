package by.bsuir.OnlineGallery.service;

import by.bsuir.OnlineGallery.exception.ResourceNotFoundException;
import by.bsuir.OnlineGallery.model.Album;
import by.bsuir.OnlineGallery.model.Image;
import by.bsuir.OnlineGallery.payload.ImageRequest;
import by.bsuir.OnlineGallery.payload.ImageResponse;
import by.bsuir.OnlineGallery.payload.UserImageResponse;
import by.bsuir.OnlineGallery.repository.AlbumRepository;
import by.bsuir.OnlineGallery.repository.ImageRepository;
import by.bsuir.OnlineGallery.sercurity.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final AlbumRepository albumRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository, AlbumRepository albumRepository) {
        this.imageRepository = imageRepository;
        this.albumRepository = albumRepository;
    }

    public List<Image> addImages(List<ImageRequest> imageRequests, Album album) {
        return Collections.emptyList();
    }

    public Image addImage(ImageRequest imageRequest) {
        Image image = toImageModel(imageRequest);
        return imageRepository.save(image);
    }

    public UserImageResponse getImageById(UserPrincipal userPrincipal, Long imageId) {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException("Image", "imageId", imageId));
        ImageResponse imageResponse = new ImageResponse(image.getId(), image.getName(),
                Arrays.toString(image.getByteArray()), image.isPrivate());

        return new UserImageResponse(userPrincipal.getId(), imageResponse);
    }

    private Image toImageModel(ImageRequest imageRequest) {
        Image image = new Image();

        image.setByteArray(imageRequest.getByteArray().getBytes());
        image.setName(imageRequest.getName());
        image.setPrivate(imageRequest.getPrivate());

        Album album = albumRepository.findById(imageRequest.getAlbumId())
                .orElseThrow(() -> new ResourceNotFoundException("Album", "albumId", imageRequest.getAlbumId()));

        image.setAlbum(album);
        return image;
    }

}
