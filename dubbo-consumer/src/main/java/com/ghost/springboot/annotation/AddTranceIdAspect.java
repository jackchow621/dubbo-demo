package com.ghost.springboot.annotation;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.ghost.springboot.util.ContextUtil;
import com.ghost.springboot.util.UnsafetyIdUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @program dubbo-demo
 * @description:
 * @author: jackchow
 * @create: 2021/11/06 14:22
 */
@Aspect
@Component
public class AddTranceIdAspect {

    @Pointcut("@annotation(com.ghost.springboot.annotation.AddTranceId)")
    public void point() {

    }

    @Around("point()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        boolean hadflag = StringUtils.isBlank(ContextUtil.getTraceId());
        if (hadflag) {
            ContextUtil.setTranceId(UnsafetyIdUtil.genUuid());
        }
        try {
            return joinPoint.proceed();
        } finally {
            if (hadflag) {
                ContextUtil.clear();
            }
        }

    }

}
