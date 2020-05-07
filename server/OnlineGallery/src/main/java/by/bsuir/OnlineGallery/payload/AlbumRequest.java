package by.bsuir.OnlineGallery.payload;

import by.bsuir.OnlineGallery.model.Image;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class AlbumRequest {
    @NotBlank
    @Size(max = 50)
    private final String name;

    @Size(max = 255)
    private final String description;

    @Valid
    private final List<ImageRequest> images;

    public AlbumRequest(@NotBlank @Size(max = 50) String name,
                        @Size(max = 255) String description,
                        @Valid List<ImageRequest> images) {

        this.name = name;
        this.description = description;
        this.images = images;
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
