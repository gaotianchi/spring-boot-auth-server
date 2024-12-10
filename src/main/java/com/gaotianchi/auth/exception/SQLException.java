package com.gaotianchi.auth.exception;

import com.gaotianchi.auth.enums.Code;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * DAO层通用异常类，用于捕获和包装数据库操作相关的错误。
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SQLException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final int code;           // 错误码
    private final String details;     // 额外的上下文信息
    private final String sqlState;    // SQL状态码（用于数据库错误识别）
    private final String sqlErrorMessage; // SQL错误消息

    /**
     * 构造方法：使用错误码枚举创建异常。
     *
     * @param code 错误码枚举
     */
    public SQLException(Code code) {
        super(code.getMessage());
        this.code = code.getCode();
        this.details = null;
        this.sqlState = null;
        this.sqlErrorMessage = null;
    }

    /**
     * 构造方法：使用错误码枚举和附加详情创建异常。
     *
     * @param code    错误码枚举
     * @param details 额外的上下文信息（例如出错SQL语句、表名等）
     */
    public SQLException(Code code, String details) {
        super(code.getMessage());
        this.code = code.getCode();
        this.details = details;
        this.sqlState = null;
        this.sqlErrorMessage = null;
    }

    /**
     * 构造方法：使用错误码枚举、附加详情和数据库错误信息创建异常。
     *
     * @param code            错误码枚举
     * @param details         额外的上下文信息
     * @param sqlState        数据库SQL状态码
     * @param sqlErrorMessage 数据库错误信息
     */
    public SQLException(Code code, String details, String sqlState, String sqlErrorMessage) {
        super(code.getMessage());
        this.code = code.getCode();
        this.details = details;
        this.sqlState = sqlState;
        this.sqlErrorMessage = sqlErrorMessage;
    }

    /**
     * 构造方法：包含原始异常，用于异常链传递。
     *
     * @param code    错误码枚举
     * @param details 额外的上下文信息
     * @param cause   原始异常
     */
    public SQLException(Code code, String details, Throwable cause) {
        super(code.getMessage(), cause);
        this.code = code.getCode();
        this.details = details;
        this.sqlState = null;
        this.sqlErrorMessage = null;
    }

    /**
     * 构造方法：包含原始异常和SQL错误信息，用于捕获数据库操作异常。
     *
     * @param code            错误码枚举
     * @param details         额外的上下文信息
     * @param sqlState        数据库SQL状态码
     * @param sqlErrorMessage 数据库错误信息
     * @param cause           原始异常
     */
    public SQLException(Code code, String details, String sqlState, String sqlErrorMessage, Throwable cause) {
        super(code.getMessage(), cause);
        this.code = code.getCode();
        this.details = details;
        this.sqlState = sqlState;
        this.sqlErrorMessage = sqlErrorMessage;
    }
}
