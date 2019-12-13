package typeenum;

import _enum.BaseEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import support.CustomException;

import static support.ResultCode.FAILURE_1004_PARAM_ERROR;

/**
 * @author zhenhua zhang
 * @data 2019/12/5
 */
public enum PowerStatusEnum implements BaseEnum {
    POWER_ON(0,"关机状态"),
    POWER_OFF(1,"开机状态");

    private Integer code;
    private String description;

    PowerStatusEnum(Integer code, String description) {
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
    public static PowerStatusEnum getEnum(Integer code) {
        if (code != 0 && code != 1  ) {
            throw new CustomException(FAILURE_1004_PARAM_ERROR.code(),
                    FAILURE_1004_PARAM_ERROR.message(), "");
        }
        PowerStatusEnum[] values = PowerStatusEnum.values();
        for (PowerStatusEnum powerStatusEnum : values) {
            if (powerStatusEnum.code == code) {
                return powerStatusEnum;
            }
        }
        return null;
    }
}
