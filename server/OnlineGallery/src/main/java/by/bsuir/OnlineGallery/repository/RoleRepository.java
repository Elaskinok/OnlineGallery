package by.bsuir.OnlineGallery.repository;

import by.bsuir.OnlineGallery.model.Role;
import by.bsuir.OnlineGallery.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
