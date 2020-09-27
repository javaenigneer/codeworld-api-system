package com.codeworld.fc.common.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Component
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FcEndPoint {
    @AliasFor(annotation = Component.class)
    String value() default "";
}
