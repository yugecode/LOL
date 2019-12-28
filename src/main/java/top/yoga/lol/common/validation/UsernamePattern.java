package top.yoga.lol.common.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {UsernameValidate.class})
public @interface UsernamePattern {

    String regexp() default "";

    String message() default "用户名为1~60位字母,数字,下划线字符";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
