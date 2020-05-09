package by.bsuir.OnlineGallery.service;

import by.bsuir.OnlineGallery.exception.PermissionDeniedException;
import by.bsuir.OnlineGallery.exception.ResourceNotFoundException;
import by.bsuir.OnlineGallery.model.Album;
import by.bsuir.OnlineGallery.model.Image;
import by.bsuir.OnlineGallery.payload.ImageRequest;
import by.bsuir.OnlineGallery.payload.ImageResponse;
import by.bsuir.OnlineGallery.payload.PagedResponse;
import by.bsuir.OnlineGallery.payload.UserImageResponse;
import by.bsuir.OnlineGallery.repository.AlbumRepository;
import by.bsuir.OnlineGallery.repository.ImageRepository;
import by.bsuir.OnlineGallery.sercurity.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Base64;
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

    public Image addImage(ImageRequest imageRequest) {
        Image image = toImageModel(imageRequest);
        return imageRepository.save(image);
    }

    public PagedResponse<ImageResponse> findImageByUsername(String username, int page, int size) {

        return null;
    }

    public UserImageResponse findImageById(UserPrincipal userPrincipal, Long imageId) {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException("Image", "imageId", imageId));

        String decodedImage = new String(Base64.getDecoder().decode(image.getByteArray().getBytes()));
        ImageResponse imageResponse = new ImageResponse(image.getId(), image.getName(),
                image.isPrivate(), decodedImage);

        return new UserImageResponse(userPrincipal.getId(), userPrincipal.getUsername(), imageResponse);
    }

    public List<UserImageResponse> findAllUserImages(UserPrincipal userPrincipal) {
        List<Album> albums = albumRepository.findAlbumById(userPrincipal.getId());
        List<UserImageResponse> userImageResponses = new ArrayList<>();

        for (Album album : albums) {
            List<Image> images = album.getImages();

            for (Image image : images) {
                String decodedImage = new String(Base64.getDecoder().decode(image.getByteArray().getBytes()));
                ImageResponse imageResponse = new ImageResponse(image.getId(), image.getName(),
                        image.isPrivate(), decodedImage);

                userImageResponses.add(new UserImageResponse(userPrincipal.getId(),
                        userPrincipal.getUsername(), imageResponse));
            }
        }

        return userImageResponses;
    }

    @Transactional
    public Image deleteImageById(UserPrincipal userPrincipal, Long imageId) {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException("Image", "imageId", imageId));

        if (!image.getAlbum().getCreatedBy().equals(userPrincipal.getId())) {
            throw new PermissionDeniedException(userPrincipal.getUsername(), "Image",
                    "This user has no permission to delete the image");
        }

        imageRepository.deleteImageById(imageId);

        if (imageRepository.existsImageById(imageId)) {
            return null;
        }

        return image;
    }

    private Image toImageModel(ImageRequest imageRequest) {
        Image image = new Image();

        String encoded = Base64.getEncoder().encodeToString(imageRequest.getByteArray().getBytes());
        image.setByteArray(encoded);
        image.setName(imageRequest.getName());
        image.setPrivate(imageRequest.getPrivate());

        Album album = albumRepository.findById(imageRequest.getAlbumId())
                .orElseThrow(() -> new ResourceNotFoundException("Album", "albumId", imageRequest.getAlbumId()));

        image.setAlbum(album);
        return image;
    }
}
