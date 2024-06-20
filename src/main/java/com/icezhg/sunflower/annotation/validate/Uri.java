package com.icezhg.sunflower.annotation.validate;

import com.icezhg.sunflower.annotation.validate.constraint.UriValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by zhongjibing on 2022/12/25.
 */
@Target(FIELD)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = UriValidator.class)
public @interface Uri {

    String message() default "{jakarta.validation.constraints.Uri.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String delimiter() default ",";
}
