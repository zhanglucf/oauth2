package _enum;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 *  公共枚举类接口
 * @author zhenhua zhang
 * @data 2019/11/29
 */
public interface BaseEnum {

    @JsonValue
    Integer getCode();

    String getDescription();

}
