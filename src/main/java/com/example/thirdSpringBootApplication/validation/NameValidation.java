package com.example.thirdSpringBootApplication.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

import javax.validation.Constraint;
import javax.validation.Payload;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StudentValidation.class)
@Documented
public @interface NameValidation {

	public String message() default "Student name is not allowed";

	public Class<?>[] groups() default {};

	public Class<? extends Payload>[] payload() default {};

}
