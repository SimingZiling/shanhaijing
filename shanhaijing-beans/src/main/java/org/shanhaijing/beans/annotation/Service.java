package org.shanhaijing.beans.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@org.yiming.networkworkbench.framework.beans.annotation.Bean
public @interface Service {

    // bean名称
    String value() default "";

}
