package com.gaotianchi.auth.vo;

import com.gaotianchi.auth.entity.Permission;
import com.gaotianchi.auth.entity.Role;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * @author gaotianchi
 * @since 2024/12/18 15:41
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class RoleVO extends Role {
    private Set<Permission> permissions;
}
