package by.bsuir.OnlineGallery.payload;

import java.util.List;

public class AlbumResponse {
    private final Long albumId;
    private final String albumName;
    private final Long creatorId;
    private final List<ImageResponse> images;

    public AlbumResponse(Long albumId, String albumName, List<ImageResponse> images, Long creatorId) {
        this.albumId = albumId;
        this.albumName = albumName;
        this.images = images;
        this.creatorId = creatorId;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public List<ImageResponse> getImages() {
        return images;
    }

    public Long getCreatorId() {
        return creatorId;
    }
}
