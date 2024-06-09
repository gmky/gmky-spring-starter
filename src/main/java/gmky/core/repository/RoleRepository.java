package gmky.core.repository;

import gmky.core.api.model.FilterRoleReq;
import gmky.core.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE (:#{#req.name} IS NULL OR UPPER(r.name) LIKE UPPER(CONCAT(:#{#req.name}, '%'))) AND (:#{#req.status} IS NULL OR r.status = :#{#req.status})")
    Page<Role> filter(FilterRoleReq req, Pageable pageable);

    boolean existsByNameIgnoreCase(String name);
}
