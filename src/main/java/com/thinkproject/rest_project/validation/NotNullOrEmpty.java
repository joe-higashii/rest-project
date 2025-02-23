//NotNullOrEmpty.java
package com.thinkproject.rest_project.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotNullOrEmptyValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNullOrEmpty {
    String message() default "Field must not be null or empty";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

