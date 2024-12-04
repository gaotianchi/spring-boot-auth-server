package com.gaotianchi.auth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author gaotianchi
 * @since 2024/11/23 18:58
 **/
@Getter
@AllArgsConstructor
public enum Code {
    // 通用
    SUCCESS(0, "Success"),
    FAIL(1, "Operation failed."),

    // SQL 模块
    SQL_INSERT_ERROR(1001, "SQL insert operation failed."),
    SQL_UPDATE_ERROR(1002, "SQL update operation failed."),
    SQL_DELETE_ERROR(1003, "SQL delete operation failed."),
    SQL_SELECT_ERROR(1004, "SQL select operation failed."),

    // 授权模块
    AUTH_TOKEN_EXPIRED(2001, "Authentication token expired."),
    AUTH_UNAUTHORIZED(2002, "Unauthorized access."),
    AUTH_INVALID_TOKEN(2003, "Invalid token."),
    AUTH_ACCESS_DENIED(2004, "Access denied."),

    // 网络模块
    NETWORK_TIMEOUT(3001, "Network timeout."),
    NETWORK_UNAVAILABLE(3002, "Network unavailable."),

    // 业务逻辑模块
    INVALID_PARAMETER(4001, "Invalid parameter."),

    // TODO: 第三方服务
    DATABASE_CONNECTION_ERROR(5001, "Database connection error."),
    DATABASE_INITIALIZATION_FAILED(5002, "Database initialization failed."),

    // 未知错误
    UNKNOWN_ERROR(9999, "Unknown error.");

    private final int code;
    private final String message;
}
