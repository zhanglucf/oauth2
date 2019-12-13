package _annotation;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhenhua zhang
 * @data 2019/12/4
 */
public class PhoneNoValidator implements ConstraintValidator<PhoneNo, String> {
    private final static String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
    private boolean required =false;

    @Override
    public void initialize(PhoneNo constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    //2、逻辑处理
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (required) {
            return PhoneNoValidator.isPhone(value);
        } else {
            if (StringUtils.isEmpty(value)) {
                return true;
            } else {
                return PhoneNoValidator.isPhone(value);
            }
        }
    }

    public static boolean isPhone(String phone) {
        if (StringUtils.isEmpty(phone)) {
            return false;
        }
        if (phone.length() != 11) {
            return false;
        } else {
            return Pattern.compile(regex).matcher(phone).matches();
        }
    }
}
