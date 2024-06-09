package gmky.core.entity;

import gmky.core.enumeration.ActionEnum;
import gmky.core.enumeration.ResourceCodeEnum;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "PRIVILEGES")
public class Privilege extends AbstractAuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "RESOURCE_CODE")
    @Enumerated(EnumType.STRING)
    private ResourceCodeEnum resourceCode;

    @Column(name = "ACTION")
    @Enumerated(EnumType.STRING)
    private ActionEnum action;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany(mappedBy = "privileges")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<PrivilegeGroup> privilegeGroups = new HashSet<>();
}
