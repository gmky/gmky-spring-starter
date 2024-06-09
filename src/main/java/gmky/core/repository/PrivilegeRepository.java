package gmky.core.repository;

import gmky.core.entity.Privilege;
import gmky.core.enumeration.ActionEnum;
import gmky.core.enumeration.ResourceCodeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    @Query("SELECT p FROM Privilege p JOIN p.privilegeGroups pg WHERE pg.id = :pgId")
    Page<Privilege> findByPgId(Long pgId, Pageable pageable);

    @Query("SELECT p FROM Privilege p WHERE (:action IS NULL OR p.action = :action) AND (:resourceCode IS NULL OR p.resourceCode = :resourceCode)")
    Page<Privilege> searchByActionAndResourceCode(ActionEnum action, ResourceCodeEnum resourceCode, Pageable pageable);
}
