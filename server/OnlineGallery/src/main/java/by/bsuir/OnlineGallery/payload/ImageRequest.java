package by.bsuir.OnlineGallery.payload;

public class ImageRequest {

    private final String name;
    private final String byteArray;
    private final Boolean isPrivate;

    public ImageRequest(String name, String byteArray, Boolean isPrivate) {
        this.name = name;
        this.byteArray = byteArray;
        this.isPrivate = isPrivate;
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
