package com.ghost.springboot.annotation;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @program dubbo-demo
 * @description:
 * @author: jackchow
 * @create: 2021/11/29 20:16
 */
@Data
@NoArgsConstructor
@ToString
public class GlobalContext {
    private String traceId;
    private String env;
    private String group;
    private String clientIp;
    private String clientHost;
    private String userId;
    private String entId;
    private String tenantId;
}
