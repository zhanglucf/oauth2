package typeenum;

import _enum.BaseEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import support.CustomException;

import static support.ResultCode.FAILURE_1004_PARAM_ERROR;

/**
 * @author zhenhua zhang
 * @data 2019/12/2
 */
public enum MessageStatusEnum implements BaseEnum {
    UNREAD(0, "未读"),
    READ(1, "已读");

    private int code;
    private String description;

    MessageStatusEnum(int code, String description) {
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
    public static MessageStatusEnum getEnum(Integer code) {
        if (code != 0 && code != 1) {
            throw new CustomException(FAILURE_1004_PARAM_ERROR.code(),
                    FAILURE_1004_PARAM_ERROR.message(), "");
        }
        MessageStatusEnum[] values = MessageStatusEnum.values();
        for (MessageStatusEnum messageStatusEnum : values) {
            if (messageStatusEnum.code == code) {
                return messageStatusEnum;
            }
        }
        return null;
    }
}
