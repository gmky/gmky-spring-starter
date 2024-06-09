package gmky.core.repository;

import gmky.core.entity.PrivilegeGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeGroupRepository extends JpaRepository<PrivilegeGroup, Long> {
    @Query("SELECT pg FROM PrivilegeGroup pg JOIN pg.roles role WHERE role.id = :roleId")
    Page<PrivilegeGroup> findByRoleId(Long roleId, Pageable pageable);

    @Query("SELECT pg FROM PrivilegeGroup pg WHERE :name IS NULL OR UPPER(pg.name) LIKE UPPER(CONCAT(CAST(:name AS STRING) , '%')) ")
    Page<PrivilegeGroup> searchByName(String name, Pageable pageable);

    boolean existsByNameIgnoreCase(String name);
}
