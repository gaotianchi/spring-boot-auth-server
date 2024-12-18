package com.gaotianchi.auth.rest;

import com.gaotianchi.auth.vo.VO;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gaotianchi
 * @since 2024/12/18 16:40
 **/
@RestController
@RequestMapping("roles-and-permissions")
public class RoleAndPermissionRest {

    @PutMapping("user-roles")
    public VO<Void> handleUpdateUserRolesRequest(

    ) {
        return VO.success(null);
    }
}
