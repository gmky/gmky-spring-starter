package gmky.core.entity;

import gmky.core.enumeration.PrivilegeGroupTypeEnum;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "PRIVILEGE_GROUP")
public class PrivilegeGroup extends AbstractAuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PG_TYPE")
    @Enumerated(EnumType.STRING)
    private PrivilegeGroupTypeEnum type;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany
    @JoinTable(name = "PG_ITEM", joinColumns = @JoinColumn(name = "PG_ID"), inverseJoinColumns = @JoinColumn(name = "PRIVILEGE_ID"))
    private Set<Privilege> privileges = new HashSet<>();

    @ManyToMany(mappedBy = "privilegeGroups")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Role> roles = new HashSet<>();

    public boolean addPrivileges(List<Privilege> privileges) {
        return getPrivileges().addAll(privileges);
    }

    public boolean removePrivileges(List<Privilege> privileges) {
        var privilegeIds = privileges.stream().map(Privilege::getId).collect(Collectors.toSet());
        return getPrivileges().removeIf(item -> privilegeIds.contains(item.getId()));
    }
}
