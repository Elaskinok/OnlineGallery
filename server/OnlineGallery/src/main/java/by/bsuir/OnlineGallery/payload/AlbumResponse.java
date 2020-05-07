package by.bsuir.OnlineGallery.payload;

import by.bsuir.OnlineGallery.model.Image;
import by.bsuir.OnlineGallery.model.User;

import java.util.List;

public class AlbumResponse {
    private final Long id;
    private final List<Image> images;
    private final User creator;

    public AlbumResponse(Long id, List<Image> images, User creator) {
        this.id = id;
        this.images = images;
        this.creator = creator;
    }

    public Long getId() {
        return id;
    }

    public List<Image> getImages() {
        return images;
    }

    public User getCreator() {
        return creator;
    }
}
