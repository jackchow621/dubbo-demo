package com.ghost.springboot.annotation;

import java.lang.annotation.*;

/**
 * 注解：在方法或者类方法执行之前织入tranceid
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AddTranceId {

}
