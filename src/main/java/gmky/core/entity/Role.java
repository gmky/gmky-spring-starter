package gmky.core.entity;

import gmky.core.enumeration.RoleStatusEnum;
import gmky.core.enumeration.RoleTypeEnum;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "ROLES")
public class Role extends AbstractAuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ROLE_TYPE")
    @Enumerated(EnumType.STRING)
    private RoleTypeEnum type;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private RoleStatusEnum status;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PARENT_ID")
    private Long parentId;

    @Column(name = "DEACTIVATED_BY")
    private String deactivatedBy;

    @Column(name = "DEACTIVATED_AT")
    private Instant deactivatedAt;

    @ManyToMany
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "PG_ROLE", joinColumns = @JoinColumn(name = "ROLE_ID"), inverseJoinColumns = @JoinColumn(name = "PG_ID"))
    private Set<PrivilegeGroup> privilegeGroups = new HashSet<>();

    public boolean addPg(List<PrivilegeGroup> privilegeGroups) {
        return getPrivilegeGroups().addAll(privilegeGroups);
    }

    public boolean removePg(List<PrivilegeGroup> privilegeGroups) {
        var pgIds = privilegeGroups.stream().map(PrivilegeGroup::getId).collect(Collectors.toSet());
        return getPrivilegeGroups().removeIf(item -> pgIds.contains(item.getId()));
    }
}
