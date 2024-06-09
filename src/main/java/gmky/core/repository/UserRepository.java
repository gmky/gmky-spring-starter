package gmky.core.repository;

import gmky.core.api.model.FilterUserReq;
import gmky.core.entity.User;
import gmky.core.repository.projection.PrivilegeRO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = """
        SELECT u FROM User u
           WHERE (:#{#req.username} IS NULL OR u.username LIKE CONCAT(:#{#req.getUsername()}, '%'))
           AND (:#{#req.fullName} IS NULL OR u.fullName LIKE CONCAT(:#{#req.fullName}, '%') )
           AND (:#{#req.statuses} IS NULL OR u.status IN :#{#req.statuses})
    """)
    Page<User> filterUser(FilterUserReq req, Pageable pageable);

    Optional<User> findByUsernameIgnoreCase(String username);

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT u FROM User u WHERE u.id <> :currentUserId AND u.id = :userId")
    Optional<User> findByIdIgnoreCurrentUser(Long currentUserId, Long userId);

    @Query(nativeQuery = true, value = """
            SELECT p.id as id, p.resource_code as resourceCode, p.action as action FROM privileges p
                 LEFT JOIN pg_item pi ON p.id = pi.privilege_id
                 LEFT JOIN pg_role pr ON pi.pg_id = pr.pg_id
                 LEFT JOIN role_user ru ON ru.role_id = pr.role_id
                 LEFT JOIN users u ON u.id = ru.user_id
            WHERE u.id = :userId""")
    Set<PrivilegeRO> getAllUserPrivileges(@Param("userId") Long userId);
}
