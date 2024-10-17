package com.ssaffeine.ssaffeine.user.repository;

import com.ssaffeine.ssaffeine.user.domain.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Boolean existsByUsername(String username);
    Boolean existsByLoginId(String loginId);

    User findByUsername(String username);
    Optional<User> findByLoginId(String loginId);
	User findByUuid(UUID uuid);
}
