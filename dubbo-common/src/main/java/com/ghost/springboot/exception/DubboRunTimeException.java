package com.ghost.springboot.exception;

import java.io.Serializable;

/**
 * @program dubbo-demo
 * @description:
 * @author: jackchow
 * @create: 2022/01/01 17:34
 */
public class DubboRunTimeException extends RuntimeException implements Serializable {
    public DubboRunTimeException() {
    }

    public DubboRunTimeException(String msg) {
        super(msg);
    }

    public DubboRunTimeException(Throwable cause) {
        super(cause);
    }

    public DubboRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
