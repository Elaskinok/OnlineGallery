package by.bsuir.OnlineGallery.payload;

public class ImageResponse {
    private final Long id;
    private final String name;
    private final String byteArray;
    private final Boolean isPrivate;

    public ImageResponse(Long id, String name, String byteArray, Boolean isPrivate) {
        this.id = id;
        this.name = name;
        this.byteArray = byteArray;
        this.isPrivate = isPrivate;
    }

    public Long getId() {
        return id;
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
}
