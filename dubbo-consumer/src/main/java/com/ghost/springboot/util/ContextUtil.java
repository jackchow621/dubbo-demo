package com.ghost.springboot.util;

import com.ghost.springboot.annotation.GlobalContext;
import org.slf4j.MDC;

/**
 * @program dubbo-demo
 * @description:
 * @author: jackchow
 * @create: 2021/11/28 13:42
 */
public class ContextUtil {
    public static final String REQUEST_CONTEXT = "request-context";
    public static final String TRACE_ID = "PtxId";
    public static ThreadLocal<GlobalContext> currentThreadLocal = ThreadLocal.withInitial(() -> {
        return new GlobalContext();
    });

    public ContextUtil() {
    }

    public static GlobalContext getCurrentContext() {
        return (GlobalContext) currentThreadLocal.get();
    }

    public static void setCurrentContext(GlobalContext globalContext) {
        currentThreadLocal.set(globalContext);
        String traceId = globalContext.getTraceId();
        if (traceId != null && traceId.length() > 0 && MDC.get("PtxId") == null) {
            MDC.put("PtxId", traceId);
        }

    }

    public static void clear() {
        MDC.clear();
        currentThreadLocal.remove();
    }

    public static void setTranceId(String traceId) {
        MDC.put("PtxId", traceId);
        getCurrentContext().setTraceId(traceId);
    }

    public static String getTraceId() {
        return MDC.get("PtxId");
    }

    public static String getClientHost() {
        GlobalContext context = getCurrentContext();
        return context != null ? context.getClientHost() : null;
    }

    public static String getAndCheckTenantId() {
        String tenantId = getTenantId();
        if (tenantId == null) {
            throw new RuntimeException("租户Id不能为空");
        } else {
            return tenantId;
        }
    }

    public static String getTenantId() {
        GlobalContext context = getCurrentContext();
        return context != null ? context.getTenantId() : null;
    }

    public static String getOperationPlatformId() {
        GlobalContext context = getCurrentContext();
        return context != null ? context.getOperationPlatformId() : null;
    }
}



