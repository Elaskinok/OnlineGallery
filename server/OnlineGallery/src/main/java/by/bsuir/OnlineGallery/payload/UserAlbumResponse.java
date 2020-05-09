package by.bsuir.OnlineGallery.payload;

public class UserAlbumResponse {
    private final Long userId;
    private final String username;
    private final AlbumResponse albumResponse;

    public UserAlbumResponse(Long userId, String username, AlbumResponse albumResponse) {
        this.userId = userId;
        this.username = username;
        this.albumResponse = albumResponse;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public AlbumResponse getAlbumResponse() {
        return albumResponse;
    }
}
