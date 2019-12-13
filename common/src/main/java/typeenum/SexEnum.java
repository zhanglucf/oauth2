package typeenum;

import _enum.BaseEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import support.CustomException;

import static support.ResultCode.FAILURE_1004_PARAM_ERROR;

/**
 * @author zhenhua zhang
 * @data 2019/11/29
 */
public enum SexEnum implements BaseEnum {
    UN_KNOWN(0, "未知的性别"),
    BOY(1, "男性"),
    GIRL(2, "女性");

    private Integer code;
    private String description;

    SexEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @JsonCreator()
    public static SexEnum getEnum(Integer code) {
        if (code != 0 && code != 1 && code != 2 ) {
            throw new CustomException(FAILURE_1004_PARAM_ERROR.code(),
                    FAILURE_1004_PARAM_ERROR.message(), "");
        }
        SexEnum[] values = SexEnum.values();
        for (SexEnum sexEnum : values) {
            if (sexEnum.code == code) {
                return sexEnum;
            }
        }
        return null;
    }

}
