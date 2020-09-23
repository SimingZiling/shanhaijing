package org.shanhaijing.beans.framework.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Bean {

    // bean名称
    String value() default "";

}
