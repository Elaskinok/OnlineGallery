package by.bsuir.OnlineGallery.payload;

public class AlbumInfoResponse {
    private final Long albumId;
    private final String albumName;
    private final String description;
    private final Boolean isPrivate;
    private final ImageResponse label;

    public AlbumInfoResponse(Long albumId,
                             String albumName,
                             String description,
                             Boolean isPrivate,
                             ImageResponse label) {

        this.albumId = albumId;
        this.albumName = albumName;
        this.description = description;
        this.isPrivate = isPrivate;
        this.label = label;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public ImageResponse getLabel() {
        return label;
    }
}
