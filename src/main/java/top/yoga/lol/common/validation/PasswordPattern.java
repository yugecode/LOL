package top.yoga.lol.common.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {PasswordValidate.class})
public @interface PasswordPattern {

    String regexp() default "";

    String message() default "密码为8~16位字符,同时包含数字和字母以及部分特殊符号!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
