package by.bsuir.OnlineGallery.payload;

import java.util.List;

public class AlbumResponse {

    private final Long albumId;
    private final String albumName;
    private final Long creatorId;
    private final Boolean isPrivate;
    private final List<ImageResponse> images;

    public AlbumResponse(Long albumId,
                         String albumName,
                         Long creatorId,
                         Boolean isPrivate,
                         List<ImageResponse> images) {

        this.albumId = albumId;
        this.albumName = albumName;
        this.creatorId = creatorId;
        this.isPrivate = isPrivate;
        this.images = images;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public List<ImageResponse> getImages() {
        return images;
    }
}
