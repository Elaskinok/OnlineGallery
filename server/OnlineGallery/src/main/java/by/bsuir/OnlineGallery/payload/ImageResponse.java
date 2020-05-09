package by.bsuir.OnlineGallery.payload;

public class ImageResponse {

    private final Long id;
    private final String name;
    private final Boolean isPrivate;
    private final String byteArray;

    public ImageResponse(Long id,
                         String name,
                         Boolean isPrivate,
                         String byteArray) {

        this.id = id;
        this.name = name;
        this.isPrivate = isPrivate;
        this.byteArray = byteArray;
    }

    public Long getId() {
        return id;
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
