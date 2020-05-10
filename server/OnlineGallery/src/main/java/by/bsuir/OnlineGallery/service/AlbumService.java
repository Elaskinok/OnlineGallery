package by.bsuir.OnlineGallery.service;

import by.bsuir.OnlineGallery.exception.BadRequestException;
import by.bsuir.OnlineGallery.exception.PermissionDeniedException;
import by.bsuir.OnlineGallery.exception.ResourceNotFoundException;
import by.bsuir.OnlineGallery.model.Album;
import by.bsuir.OnlineGallery.model.Image;
import by.bsuir.OnlineGallery.model.User;
import by.bsuir.OnlineGallery.payload.AlbumInfoResponse;
import by.bsuir.OnlineGallery.payload.AlbumRequest;
import by.bsuir.OnlineGallery.payload.AlbumResponse;
import by.bsuir.OnlineGallery.payload.ImageResponse;
import by.bsuir.OnlineGallery.payload.PagedResponse;
import by.bsuir.OnlineGallery.payload.UserAlbumResponse;
import by.bsuir.OnlineGallery.repository.AlbumRepository;
import by.bsuir.OnlineGallery.repository.UserRepository;
import by.bsuir.OnlineGallery.sercurity.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static by.bsuir.OnlineGallery.service.ApplicationConstants.MAX_PAGE_SIZE;

@Service
public class AlbumService {
    private static String SORT_BY_CREATED_AT = "createdAt";

    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository,
                        UserRepository userRepository) {

        this.albumRepository = albumRepository;
        this.userRepository = userRepository;
    }

    public Album createAlbum(AlbumRequest albumRequest) {
        Album album = toAlbumModel(albumRequest);
        return albumRepository.save(album);
    }

    public boolean existsByAlbumName(String name) {
        return albumRepository.existsByName(name);
    }

    public List<AlbumInfoResponse> findAlbumsByUsername(UserPrincipal userPrincipal, String username) {
        Long userId = getUserIdIfExists(username);
        boolean privateFilter = true;

        if (userPrincipal != null) {
            if (Objects.equals(userPrincipal.getId(), userId)) {
                privateFilter = false;
            }
        }

        List<Album> albums = albumRepository.findAlbumByCreatedBy(userId);
        List<AlbumInfoResponse> albumInfoResponses = new ArrayList<>();
        for (Album album : albums) {
            if (album.isPrivate() && privateFilter) continue;

            ImageResponse imageResponse = null;
            if (album.getImages().size() > 0) {
                Optional<Image> firstImage;

                if (privateFilter) {
                    firstImage = album.getImages().stream()
                            .filter(im -> !im.isPrivate())
                            .findFirst();
                } else {
                    firstImage = album.getImages().stream().findFirst();
                }

                if (firstImage.isPresent()) {
                    imageResponse = toImageResponse(firstImage.get());
                }
            }
            albumInfoResponses.add(new AlbumInfoResponse(album.getId(), album.getName(), album.getDescription(),
                    album.isPrivate(), imageResponse));
        }

        return albumInfoResponses;
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

    @Transactional
    public Album deleteUserAlbumById(UserPrincipal userPrincipal, Long albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new ResourceNotFoundException("Album", "albumId", albumId));

        if (!userPrincipal.getId().equals(album.getCreatedBy())) {
            throw new PermissionDeniedException(userPrincipal.getUsername(), "Album",
                    "This user has no permission to delete the album");
        }

        albumRepository.deleteAlbumById(albumId);

        if (albumRepository.existsById(albumId)) {
            return null;
        }

        return album;
    }

    public UserAlbumResponse findUserAlbumById(UserPrincipal userPrincipal, Long albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new ResourceNotFoundException("Album", "albumId", albumId));

        if (!Objects.equals(album.getCreatedBy(), userPrincipal.getId())) {
            throw new PermissionDeniedException(userPrincipal.getUsername(), "Album",
                    "This user has no permission for the Album");
        }

        AlbumResponse albumResponse = toAlbumResponse(album, false);

        return new UserAlbumResponse(userPrincipal.getId(), userPrincipal.getUsername(), albumResponse);
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
        return new ImageResponse(image.getId(), image.getAlbum().getId(), image.getName(),
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

    private List<AlbumResponse> toAlbumResponseList(List<Album> albums, boolean privateFilter) {
        List<AlbumResponse> albumResponses = new ArrayList<>();
        for (Album album : albums) {
            AlbumResponse albumResponse = toAlbumResponse(album, privateFilter);
            albumResponses.add(albumResponse);
        }

        return albumResponses;
    }

    private PagedResponse<AlbumResponse> getAlbumResponsePagedResponse(Page<Album> albumPage, boolean privateFilter) {
        List<AlbumResponse> albumResponses;

        if (albumPage.getSize() == 0) {
            albumResponses = Collections.emptyList();
        } else {
            List<Album> albums = albumPage.getContent();
            albumResponses = toAlbumResponseList(albums, privateFilter);
        }

        return new PagedResponse<>(albumResponses, albumPage.getNumber(), albumPage.getSize(),
                albumPage.getTotalElements(), albumPage.getTotalPages(), albumPage.isLast());
    }

    private AlbumResponse toAlbumResponse(Album album, boolean privateFilter) {
        AlbumResponse albumResponse;

        if (!privateFilter) {
            List<Image> images = album.getImages();

            List<ImageResponse> imageResponses = new ArrayList<>();
            for (Image image : images) {
                ImageResponse imageResponse = toImageResponse(image);
                imageResponses.add(imageResponse);
            }

            albumResponse = new AlbumResponse(album.getId(), album.getName(),
                    album.getCreatedBy(), album.isPrivate(), imageResponses);
        } else {
            List<Image> images = album.getImages();
            List<ImageResponse> imageResponses = new ArrayList<>();

            for (Image image : images) {
                if (image.isPrivate()) continue;

                ImageResponse imageResponse = toImageResponse(image);
                imageResponses.add(imageResponse);
            }
            albumResponse = new AlbumResponse(album.getId(), album.getName(),
                    album.getCreatedBy(), album.isPrivate(), imageResponses);
        }

        return albumResponse;
    }

    public List<ImageResponse> findUserAlbumContentById(UserPrincipal userPrincipal, String username, Long albumId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        boolean privateFilter = true;

        if (userPrincipal != null) {
            if (Objects.equals(user.getId(), userPrincipal.getId()) && userPrincipal.getUsername().equals(username)) {
                privateFilter = false;
            }
        }

        Album foundAlbum = albumRepository.findAlbumById(albumId)
                .orElseThrow(() -> new ResourceNotFoundException("Album", "albumId", albumId));

        List<ImageResponse> imageResponses = new ArrayList<>();
        if (privateFilter) {
            if (foundAlbum.isPrivate()) {
                throw new BadRequestException("The user has no permission for the album content");
            }

            List<Image> images = foundAlbum.getImages();
            for (Image image : images) {
                if (image.isPrivate()) continue;

                ImageResponse imageResponse = toImageResponse(image);
                imageResponses.add(imageResponse);
            }
        } else {
            List<Image> images = foundAlbum.getImages();
            for (Image image : images) {
                ImageResponse imageResponse = toImageResponse(image);
                imageResponses.add(imageResponse);
            }
        }

        return imageResponses;
    }
}
