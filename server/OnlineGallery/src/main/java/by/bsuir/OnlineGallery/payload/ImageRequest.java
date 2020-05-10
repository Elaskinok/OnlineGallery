package by.bsuir.OnlineGallery.payload;

public class ImageRequest {

    private final String name;
    private final String byteArray;
    private final Boolean isPrivate;
    private final Long albumId;

    public ImageRequest(String name,
                        String byteArray,
                        Boolean isPrivate,
                        Long albumId) {

        this.name = name;
        this.byteArray = byteArray;
        this.isPrivate = isPrivate;
        this.albumId = albumId;
    }

    public String getName() {
        return name;
    }

    public String getByteArray() {
        return byteArray;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public Long getAlbumId() {
        return albumId;
    }
}
