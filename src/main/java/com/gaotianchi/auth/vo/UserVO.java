package com.gaotianchi.auth.vo;

import com.gaotianchi.auth.entity.Role;
import com.gaotianchi.auth.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * @author gaotianchi
 * @since 2024/12/18 15:12
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class UserVO extends User {
    private Set<Role> roles;
}
