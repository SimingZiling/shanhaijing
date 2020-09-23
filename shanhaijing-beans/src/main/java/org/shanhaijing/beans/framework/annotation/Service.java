package org.shanhaijing.beans.framework.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Bean
public @interface Service {

    // bean名称
    String value() default "";

}
