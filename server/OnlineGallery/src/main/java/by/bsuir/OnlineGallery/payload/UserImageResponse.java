package by.bsuir.OnlineGallery.payload;

public class UserImageResponse {
    private final Long userId;
    private final ImageResponse imageResponse;

    public UserImageResponse(Long userId, ImageResponse imageResponse) {
        this.userId = userId;
        this.imageResponse = imageResponse;
    }

    public Long getUserId() {
        return userId;
    }

    public ImageResponse getImageResponse() {
        return imageResponse;
    }
}
