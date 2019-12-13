package typeenum;

import _enum.BaseEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import support.CustomException;

import static support.ResultCode.FAILURE_1004_PARAM_ERROR;

/**
 * @author zhenhua zhang
 * @data 2019/12/5
 */
public enum ImageTypeEnum implements BaseEnum {
    KVM(0, "????待确认！");

    private int code;
    private String description;

    ImageTypeEnum(int code, String description) {
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
    public static ImageTypeEnum getEnum(Integer code) {
        if (code != 0 ) {
            throw new CustomException(FAILURE_1004_PARAM_ERROR.code(),
                    FAILURE_1004_PARAM_ERROR.message(), "");
        }
        ImageTypeEnum[] values = ImageTypeEnum.values();
        for (ImageTypeEnum imageTypeEnum : values) {
            if (imageTypeEnum.code == code) {
                return imageTypeEnum;
            }
        }
        return null;
    }
}
