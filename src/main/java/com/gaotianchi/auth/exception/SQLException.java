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

    /**
     * 构造方法：使用错误码枚举创建异常。
     *
     * @param code 错误码枚举
     */
    public SQLException(Code code) {
        super(code.getMessage());
        this.code = code.getCode();
        this.details = null;
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
    }
}
