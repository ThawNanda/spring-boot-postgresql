package com.chat.backend.repository;

import com.chat.backend.model.entity.RoleName;
import com.chat.backend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    //Optional<User> findByEmail(String username);

    @Query(value = "SELECT u FROM User u INNER JOIN u.roles r WHERE r.name=:roleName")
    List<User> findByRoleAdmin(RoleName roleName);

    Optional<User> findByUsername(String username);

    @Query(value = "SELECT u FROM User u INNER JOIN u.roles r WHERE r.name<>'ROLE_ADMIN' AND u.status='ONLINE'")
    List<User> getOnlineUsers();

    @Query(value = "SELECT u FROM User u INNER JOIN u.roles r WHERE r.name<>'ROLE_ADMIN' AND u.status='OFFLINE'")
    List<User> getOfflineUsers();
}
