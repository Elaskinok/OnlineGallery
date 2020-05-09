package by.bsuir.OnlineGallery.service;

import by.bsuir.OnlineGallery.exception.BadRequestException;
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
import by.bsuir.OnlineGallery.sercurity.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import static by.bsuir.OnlineGallery.service.ApplicationConstants.MAX_PAGE_SIZE;

@Service
public class AlbumService {
    private static String SORT_BY_CREATED_AT = "createdAt";

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

    public Album createAlbum(AlbumRequest albumRequest) {
        Album album = toAlbumModel(albumRequest);
        return albumRepository.save(album);
    }

    public boolean existsByAlbumName(String name) {
        return albumRepository.existsByName(name);
    }

    public PagedResponse<AlbumResponse> findAlbumsByCreatedBy(UserPrincipal userPrincipal, int pageNumber, int size) {
        String username = userPrincipal.getUsername();

        Long userId = getUserIdIfExists(username);

        Page<Album> albumPage = getAlbumPageByCreatedAt(pageNumber, size, userId);
        return getAlbumResponsePagedResponse(albumPage, false);
    }


    public PagedResponse<AlbumResponse> findAlbumsByCreatedBy(String username, int pageNumber, int size) {
        validatePageNumberAndSize(pageNumber, size);

        Long userId = getUserIdIfExists(username);

        Page<Album> albumPage = getAlbumPageByCreatedAt(pageNumber, size, userId);

        return getAlbumResponsePagedResponse(albumPage, true);
    }

    private Page<Album> getAlbumPageByCreatedAt(int pageNumber, int size, long userId) {
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.Direction.DESC, SORT_BY_CREATED_AT);
        return albumRepository.findAlbumByCreatedBy(userId, pageable);
    }

    private Long getUserIdIfExists(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        return user.getId();
    }

    private ImageResponse toImageResponse(Image image) {
        String decodedImage = new String(Base64.getDecoder().decode(image.getByteArray().getBytes()));
        return new ImageResponse(image.getId(), image.getName(),
                image.isPrivate(), decodedImage);
    }

    private Album toAlbumModel(AlbumRequest albumRequest) {
        Album album = new Album();
        album.setName(albumRequest.getName());
        album.setDescription(albumRequest.getDescription());
        album.setPrivate(albumRequest.getPrivate());
        album.setImages(Collections.emptyList());

        return album;
    }

    private void validatePageNumberAndSize(int pageNumber, int size) {
        if (pageNumber < 0) {
            throw new BadRequestException("Page number can not be less than 0");
        }

        if (size > MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size can not be more than 1");
        }
    }

    private PagedResponse<AlbumResponse> getAlbumResponsePagedResponse(Page<Album> albumPage, boolean privateFilter) {
        List<AlbumResponse> albumResponses;

        if (albumPage.getSize() == 0) {
            albumResponses = Collections.emptyList();
        } else {
            List<Album> albums = albumPage.getContent();
            albumResponses = toAlbumResponse(albums, privateFilter);
        }

        return new PagedResponse<>(albumResponses, albumPage.getNumber(), albumPage.getSize(),
                albumPage.getTotalElements(), albumPage.getTotalPages(), albumPage.isLast());
    }

    private List<AlbumResponse> toAlbumResponse(List<Album> albums, boolean privateFilter) {
        List<AlbumResponse> albumResponses = new ArrayList<>();
        if (!privateFilter) {
            for (Album album : albums) {
                List<Image> images = imageRepository.findAllByAlbum(album);

                List<ImageResponse> imageResponses = new ArrayList<>();

                for (Image image : images) {
                    ImageResponse imageResponse = toImageResponse(image);
                    imageResponses.add(imageResponse);
                }
                albumResponses.add(
                        new AlbumResponse(
                                album.getId(),
                                album.getName(),
                                album.getCreatedBy(),
                                album.isPrivate(),
                                imageResponses
                        )
                );
            }
        } else {
            for (Album album : albums) {
                if (album.isPrivate()) continue;

                List<Image> images = imageRepository.findAllByAlbum(album);
                List<ImageResponse> imageResponses = new ArrayList<>();

                for (Image image : images) {
                    if (image.isPrivate()) continue;

                    ImageResponse imageResponse = toImageResponse(image);
                    imageResponses.add(imageResponse);
                }
                albumResponses.add(
                        new AlbumResponse(
                                album.getId(),
                                album.getName(),
                                album.getCreatedBy(),
                                album.isPrivate(),
                                imageResponses
                        )
                );
            }
        }

        return albumResponses;
    }

}
