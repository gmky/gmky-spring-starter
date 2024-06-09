package gmky.core.entity;

import gmky.core.enumeration.UserStatusEnum;
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
@Table(name = "USERS")
public class User extends AbstractAuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private UserStatusEnum status;

    @Column(name = "IS_ADMIN")
    private boolean isAdmin;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "EXPIRE_AT")
    private Instant expireAt;

    @Column(name = "DELETED_AT")
    private Instant deletedAt;

    @Column(name = "DELETED_BY")
    private String deletedBy;

    @ManyToMany
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "ROLE_USER", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles = new HashSet<>();

    public boolean addRoles(List<Role> roles) {
        return getRoles().addAll(roles);
    }

    public boolean removeRoles(List<Role> roles) {
        var roleIds = roles.stream().map(Role::getId).collect(Collectors.toSet());
        return getRoles().removeIf(item -> roleIds.contains(item.getId()));
    }
}
