package typeenum;

import _enum.BaseEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import support.CustomException;

import static support.ResultCode.FAILURE_1004_PARAM_ERROR;

/**
 * @author zhenhua zhang
 * @data 2019/12/5
 */
public enum ApplyTypeEnum implements BaseEnum {
    VM(0, "桌面"),
    VM_GROUP(1, "桌面池");

    private int code;
    private String description;

    ApplyTypeEnum(int code, String description) {
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
    public static ApplyTypeEnum getEnum(Integer code) {
        if (code != 0 && code != 1) {
            throw new CustomException(FAILURE_1004_PARAM_ERROR.code(),
                    FAILURE_1004_PARAM_ERROR.message(), "");
        }
        ApplyTypeEnum[] values = ApplyTypeEnum.values();
        for (ApplyTypeEnum applyTypeEnum : values) {
            if (applyTypeEnum.code == code) {
                return applyTypeEnum;
            }
        }
        return null;
    }
}
