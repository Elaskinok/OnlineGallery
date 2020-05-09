package by.bsuir.OnlineGallery.payload;

public class UserImageResponse {
    private final Long userId;
    private final String username;
    private final ImageResponse imageResponse;

    public UserImageResponse(Long userId, String username, ImageResponse imageResponse) {
        this.userId = userId;
        this.username = username;
        this.imageResponse = imageResponse;
    }

    public Long getUserId() {
        return userId;
    }

    public ImageResponse getImageResponse() {
        return imageResponse;
    }
}
