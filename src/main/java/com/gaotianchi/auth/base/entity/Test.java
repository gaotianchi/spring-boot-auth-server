package com.gaotianchi.auth.base.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author gaotianchi
 * @since 2024-12-10 11:13:40
 */
@Data
@Builder
public class Test implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
}

