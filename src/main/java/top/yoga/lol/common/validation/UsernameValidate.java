package top.yoga.lol.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用户名验证
 *
 * @author luojiayu
 */
public class UsernameValidate implements ConstraintValidator<UsernamePattern, String> {
    Pattern pattern = Pattern
            .compile("^[a-zA-Z0-9_]{1,60}$");

    @Override
    public void initialize(UsernamePattern constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null) {
            Matcher m = pattern.matcher(value);
            return m.matches();
        }
        return true;
    }
}
