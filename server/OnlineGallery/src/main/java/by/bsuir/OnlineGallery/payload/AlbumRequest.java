package by.bsuir.OnlineGallery.payload;

import java.util.List;

public class AlbumRequest {

    private final String name;
    private final String description;
    private final Boolean isPrivate;
    private final List<ImageRequest> images;

    public AlbumRequest(String name,
                        String description,
                        Boolean isPrivate,
                        List<ImageRequest> images) {

        this.name = name;
        this.description = description;
        this.isPrivate = isPrivate;
        this.images = images;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<ImageRequest> getImages() {
        return images;
    }

}
