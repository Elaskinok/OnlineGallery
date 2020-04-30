package by.bsuir.onlinegallery.datastore;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FakePasswordStore {
    private final Map<UUID, String> passwords = new HashMap<>();

    public String getByUUID(UUID uuid) {
        return passwords.entrySet().stream()
                .filter(k -> k.getKey().equals(uuid))
                .findFirst()
                .map(Map.Entry::getValue)
                .get();
    }

    public void savePassword(UUID uuid, String password) {
        passwords.computeIfAbsent(uuid, v -> password);
    }

    public List<String> findAll() {
        return new ArrayList<>(passwords.values());
    }
}
