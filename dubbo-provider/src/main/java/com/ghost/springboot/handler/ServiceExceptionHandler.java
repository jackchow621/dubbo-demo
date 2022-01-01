package com.ghost.springboot.handler;

import com.ghost.springboot.entity.DubboResponse;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @program dubbo-demo
 * @description:
 * @author: jackchow
 * @create: 2022/01/01 17:45
 */
@Aspect
@Component
@Slf4j
public class ServiceExceptionHandler {
    @Pointcut(value = "execution(* com.ghost.springboot.*.*(..))")
    private void servicePointcut() {
    }

    @Pointcut(value = "@annotation(org.springframework.transaction.annotation.Transactional)")
    private void transactionalPointcut() {
    }

    @Around("servicePointcut() && !transactionalPointcut()")
    public Object doAround(ProceedingJoinPoint pjp) {
        Object[] args = pjp.getArgs();
        try {
            return pjp.proceed();
        } catch (Exception e) {
            processException(pjp, args, e);
            return DubboResponse.error("exception occured on dubbo service side: " + e.getMessage());
        } catch (Throwable throwable) {
            processException(pjp, args, throwable);
            return DubboResponse.error("exception occured on dubbo service side: " + throwable.getMessage());
        }
    }

    @Around("servicePointcut() && transactionalPointcut()")
    public Object doTransactionalAround(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed();
        } catch (Exception e) {
            Object[] args = pjp.getArgs();
            processException(pjp, args, e);
            log.error("service with @Transactional exception occured on dubbo service side: " + e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * deal the exception
     *
     * @param joinPoint
     * @param args
     * @param throwable
     */
    private void processException(final ProceedingJoinPoint joinPoint, final Object[] args, Throwable throwable) {
        String inputParam = "";
        if (args != null && args.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (Object arg : args) {
                sb.append(",");
                sb.append(arg);
            }
            inputParam = sb.toString().substring(1);
        }
        log.error("\n method: {}\n args: {} \n message: {}", joinPoint.toLongString(), inputParam,
                Throwables.getStackTraceAsString(throwable));
    }
}
