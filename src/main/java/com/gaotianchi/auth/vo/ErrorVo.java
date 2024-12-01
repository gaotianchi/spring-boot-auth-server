package com.gaotianchi.auth.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author gaotianchi
 * @since 2024/12/1 14:06
 **/
@Data
@Builder
public class ErrorVo {
    private Date timestamp;
    private Integer status;
    private String message;
    private String path;
}
