package com.gaotianchi.auth.vo;

import com.gaotianchi.auth.entity.Permission;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author gaotianchi
 * @since 2024/12/19 8:56
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class PermissionVO extends Permission {
}
