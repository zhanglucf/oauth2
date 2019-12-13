package typeenum;

import _enum.BaseEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import support.CustomException;

import static support.ResultCode.FAILURE_1004_PARAM_ERROR;

/**
 * @author zhenhua zhang
 * @data 2019/12/2
 */
public enum BindTypeEnum implements BaseEnum {
    UN_BIND(0, "未绑定"),
    BIND(1, "已绑定");

    private int code;
    private String description;

    BindTypeEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @JsonCreator()
    public static BindTypeEnum getEnum(Integer code) {
        if (code != 0 && code != 1) {
            throw new CustomException(FAILURE_1004_PARAM_ERROR.code(),
                    FAILURE_1004_PARAM_ERROR.message(), "");
        }
        BindTypeEnum[] values = BindTypeEnum.values();
        for (BindTypeEnum bindTypeEnum : values) {
            if (bindTypeEnum.code == code) {
                return bindTypeEnum;
            }
        }
        return null;
    }
}
