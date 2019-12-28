package top.yoga.lol.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 密码验证
 *
 * @author luojiayu
 * @version 1.0
 * @date: 2019/12/28 14:25
 */
public class PasswordValidate implements ConstraintValidator<PasswordPattern, String> {

    @Override
    public void initialize(PasswordPattern constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null) {
            //1、长度：8~16
            //2、组成：大写字母、小写字母、数字、特殊符号：{}!@#￥%&*()_
            //3、规则：以字母开头，必须包含以上四种规则中的三种
            if (value.length() < 8 || value.length() > 16) {
                return false;
            }
            int upper = 0;
            int lower = 0;
            int num = 0;
            int sign = 0;
            char[] arr = value.toCharArray();
            for (char c : arr) {
                if (isNumber(c)) {
                    num = 1;
                } else if (isUpper(c)) {
                    upper = 1;
                } else if (isLowwer(c)) {
                    lower = 1;
                } else if (isSign(c)) {
                    sign = 1;
                } else {
                    //非规定字符
                    return false;
                }
            }
            //匹配数
            return upper + lower + num + sign >= 3;
        }
        return true;
    }

    /**
     * 数字
     *
     * @param c
     * @return
     */
    public static boolean isNumber(char c) {
        int v = c;
        return v >= 48 && v <= 57;
    }

    /**
     * 大写
     *
     * @param c
     * @return
     */
    public static boolean isUpper(char c) {
        int v = c;
        return v >= 65 && v <= 90;
    }

    /**
     * 小写
     *
     * @param c
     * @return
     */
    public static boolean isLowwer(char c) {
        int v = c;
        return v >= 97 && v <= 122;
    }

    /**
     * 是否为符号
     *
     * @param c
     * @return
     */
    public static boolean isSign(char c) {
        char[] arr = "{}!@#￥%&*()_".toCharArray();
        for (char c1 : arr) {
            if (c == c1) {
                return true;
            }
        }
        return false;
    }
}