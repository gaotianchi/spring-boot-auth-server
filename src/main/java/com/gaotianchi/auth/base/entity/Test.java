package com.gaotianchi.auth.base.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * (Test)实体类
 *
 * @author gaotianchi
 * @since 2024-12-10 11:13:40
 */
@Data
@Builder
public class Test implements Serializable {

    @Serial
    private static final long serialVersionUID = 505017785262859411L;

    private Integer id;
    private String name;
}

