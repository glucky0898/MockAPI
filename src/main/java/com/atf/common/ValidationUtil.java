package com.atf.common;

import javax.validation.*;
import java.util.Locale;
import java.util.Set;

/**
 * @ClassName: VlidationUtil
 * @Description: 校验工具类
 * @author lzx
 * @date 2017-9-20 上午12:46:37
 */
public class ValidationUtil {

    //private static Logger logger = LoggerFactory.getLogger(ValidationUtil.class);

    private static Validator validator;

    static {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        MessageInterpolator interpolator = new LocalizedMessageInterpolator(vf.getMessageInterpolator(), Locale.CHINA);
        validator = vf.usingContext().messageInterpolator(interpolator).getValidator();
    }


    /**
     * @throws ValidationException
     * @throws ValidationException
     * @Description: 校验方法
     * @param t 将要校验的对象
     * @throws ValidationException
     * void
     * @throws
     */
    public static <T> void validate(T t){
        Set<ConstraintViolation<T>> set =  validator.validate(t);
        if(set.size() > 0){
            StringBuilder validateError = new StringBuilder();
            for(ConstraintViolation<T> cv : set){
                validateError.append(cv.getPropertyPath().toString()).append(" ").append(cv.getMessage()).append(", ");
            }
            String errorString = validateError.substring(0, validateError.length() - 2);
            throw new ValidationException(errorString);
        }
    }

}