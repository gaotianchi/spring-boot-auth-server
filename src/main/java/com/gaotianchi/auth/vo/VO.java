package com.gaotianchi.auth.vo;

import com.gaotianchi.auth.enums.Code;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author gaotianchi
 * @since 2024/11/23 18:44
 **/
@Setter
@Getter
public class VO<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int code;
    private String message;
    private T data;
    private Long timestamp;

    /**
     * @param code    ...
     * @param message ...
     * @param data    ...
     * @author gaotianchi
     * @since 2024/11/23 19:14
     **/
    public VO(Code code, String message, T data) {
        this.code = code.getCode();
        this.message = message;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * common restful response
     *
     * @param code response code
     * @param data response data
     * @return com.gaotianchi.auth.vo.VO<T>
     * @author gaotianchi
     * @since 2024/11/23 19:18
     **/
    public static <T> VO<T> response(Code code, T data) {
        return new VO<>(code, code.getMessage(), data);
    }

}
