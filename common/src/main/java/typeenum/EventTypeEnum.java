package typeenum;

import _enum.BaseEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import support.CustomException;

import static support.ResultCode.FAILURE_1004_PARAM_ERROR;

/**
 * @author zhenhua zhang
 * @data 2019/12/2
 */
public enum EventTypeEnum implements BaseEnum {
    BIND_ACCOUNT(0, "绑定账号"),
    UN_BIND_ACCOUNT(1,"绑定账号"),
    POWER_ON(2,"云盒开机"),
    POWER_OFF(3,"云盒关机");

    private int code;
    private String description;

    EventTypeEnum(int code, String description) {
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
    public static EventTypeEnum getEnum(Integer code) {
        if (code != 0 && code != 1 && code != 2 && code != 3) {
            throw new CustomException(FAILURE_1004_PARAM_ERROR.code(),
                    FAILURE_1004_PARAM_ERROR.message(), "");
        }
        EventTypeEnum[] values = EventTypeEnum.values();
        for (EventTypeEnum eventTypeEnum : values) {
            if (eventTypeEnum.code == code) {
                return eventTypeEnum;
            }
        }
        return null;
    }
}
