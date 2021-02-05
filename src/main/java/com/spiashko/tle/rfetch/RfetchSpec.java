package com.spiashko.tle.rfetch;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface RfetchSpec {

    String requestParamName() default "include";

}
