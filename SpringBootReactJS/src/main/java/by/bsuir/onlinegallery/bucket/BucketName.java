package by.bsuir.onlinegallery.bucket;

public enum BucketName {
    PROFILE_IMAGE("online-gallery-upload-bucket-234");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
