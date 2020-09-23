package org.shanhaijing.beans.framework.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@org.yiming.networkworkbench.framework.beans.annotation.Bean
public @interface Controller {

    String value() default "";

}
