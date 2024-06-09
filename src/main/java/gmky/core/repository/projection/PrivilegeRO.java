package gmky.core.repository.projection;


import gmky.core.enumeration.ActionEnum;
import gmky.core.enumeration.ResourceCodeEnum;

public interface PrivilegeRO {
    Long getId();

    ResourceCodeEnum getResourceCode();

    ActionEnum getAction();
}
