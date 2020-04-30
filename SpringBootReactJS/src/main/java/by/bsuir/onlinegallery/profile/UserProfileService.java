package by.bsuir.onlinegallery.profile;

import by.bsuir.onlinegallery.bucket.BucketName;
import by.bsuir.onlinegallery.filestore.FileStore;
import by.bsuir.onlinegallery.repository.impl.UserProfileRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
public class UserProfileService {

    private final UserProfileRepositoryImpl userProfileRepositoryImpl;
    private final FileStore fileStore;

    @Autowired
    public UserProfileService(UserProfileRepositoryImpl userProfileRepositoryImpl, FileStore fileStore) {
        this.userProfileRepositoryImpl = userProfileRepositoryImpl;
        this.fileStore = fileStore;
    }

    public List<UserProfile> getUserProfiles() {
        return userProfileRepositoryImpl.getUserProfiles();
    }

    public void saveProfile(UserProfile profile) {
        userProfileRepositoryImpl.saveProfile(profile);
    }

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        isFileEmpty(file);
        isImage(file);
        UserProfile user = getUserProfileOrThrow(userProfileId);
        Map<String, String> metadata = extractMetadata(file);

        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getUserProfileId());
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
        try {
            fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
            user.setUserProfileImageLink(filename);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public byte[] downloadUserProfileImage(UUID userProfileId) {
        UserProfile user = getUserProfileOrThrow(userProfileId);
        String path = String.format("%s/%s",
                BucketName.PROFILE_IMAGE.getBucketName(),
                user.getUserProfileId());

        return user.getUserProfileImageLink()
                .map(key -> fileStore.download(path, key))
                .orElse(new byte[0]);
    }

    private Map<String, String> extractMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        return metadata;
    }

    private UserProfile getUserProfileOrThrow(UUID userProfileId) {
        return userProfileRepositoryImpl
                .getUserProfiles()
                .stream()
                .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User profile %s not found", userProfileId)));
    }

    private void isImage(MultipartFile file) {
        if (!Arrays.asList(
                IMAGE_JPEG.getMimeType(),
                IMAGE_PNG.getMimeType(),
                IMAGE_GIF.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("File must be an image [" + file.getContentType() + "]");
        }
    }

    private void isFileEmpty(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file[" + file.getSize() + "]");
        }
    }
}
