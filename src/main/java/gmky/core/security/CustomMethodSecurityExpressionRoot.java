package gmky.core.security;

import gmky.core.enumeration.ActionEnum;
import gmky.core.enumeration.ResourceCodeEnum;
import gmky.core.repository.UserRepository;
import gmky.core.repository.projection.PrivilegeRO;
import gmky.core.utils.SecurityUtil;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {
    private final UserRepository userRepository;
    private Object filterObject;
    private Object returnObject;

    public CustomMethodSecurityExpressionRoot(Authentication authentication, UserRepository userRepository) {
        super(authentication);
        this.userRepository = userRepository;
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }

    public boolean checkPermission(ResourceCodeEnum resourceCode, ActionEnum[] requiredPrivileges) {
        var userId = SecurityUtil.getCurrentUserId();
        var assignedPrivileges = userRepository.getAllUserPrivileges(userId);
        var matchedPrivileges = getMatchedResourceCodePrivileges(assignedPrivileges, resourceCode);
        var finalActions = getFinalAssignedAction(matchedPrivileges);
        return finalActions.containsAll(Arrays.asList(requiredPrivileges));
    }

    private Set<PrivilegeRO> getMatchedResourceCodePrivileges(Set<PrivilegeRO> privileges, ResourceCodeEnum resourceCode) {
        return privileges.stream()
                .filter(item -> item.getResourceCode() == resourceCode)
                .collect(Collectors.toSet());
    }

    private Set<ActionEnum> getFinalAssignedAction(Set<PrivilegeRO> privileges) {
        return privileges.stream()
                .map(PrivilegeRO::getAction)
                .collect(Collectors.toSet());
    }
}
