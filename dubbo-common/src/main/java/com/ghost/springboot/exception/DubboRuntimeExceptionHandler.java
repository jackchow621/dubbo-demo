package com.ghost.springboot.exception;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @program dubbo-demo
 * @description:
 * @author: jackchow
 * @create: 2022/01/01 17:36
 */
@Aspect
@Component
@Slf4j
public class DubboRuntimeExceptionHandler {
    /**
     * service层的RuntimeException统一处理器
     * 可以将RuntimeException分装成RpcRuntimeException抛给调用端处理 或自行处理
     *
     * @param exception
     */
    @AfterThrowing(throwing = "exception", pointcut = "execution(* com.ghost.springboot.*.*(..))")
    public void afterThrow(Throwable exception) {
        if (exception instanceof RuntimeException) {
            log.error("DemoRpcRuntimeExceptionHandler side->exception occured: " + exception.getMessage(),
                    exception);
            throw new DubboRunTimeException(exception);
        }
    }

}
