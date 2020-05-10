package by.bsuir.OnlineGallery.payload;

public class ImageResponse {

    private final Long id;
    private final Long albumId;
    private final String name;
    private final Boolean isPrivate;
    private final String byteArray;

    public ImageResponse(Long id,
                         Long albumId, String name,
                         Boolean isPrivate,
                         String byteArray) {

        this.id = id;
        this.albumId = albumId;
        this.name = name;
        this.isPrivate = isPrivate;
        this.byteArray = byteArray;
    }

    public Long getId() {
        return id;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public String getName() {
        return name;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public String getByteArray() {
        return byteArray;
    }

}
