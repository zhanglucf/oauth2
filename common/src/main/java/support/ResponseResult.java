package support;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
//@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseResult {
}
