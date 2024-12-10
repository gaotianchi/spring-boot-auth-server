package com.gaotianchi.auth.exception;

import com.gaotianchi.auth.enums.Code;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;


@EqualsAndHashCode(callSuper = true)
@Data
public class SQLException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final int code;
    private final String message;
    private final String details;

    public SQLException(Code code) {
        super(code.getMessage());
        this.code = code.getCode();
        this.message = code.getMessage();
        this.details = null;
    }

    public SQLException(Code code, String details) {
        super(code.getMessage());
        this.code = code.getCode();
        this.message = code.getMessage();
        this.details = details;
    }
}
