package dto;

import entity.User;
import lombok.Data;

import java.util.List;

/**
 * @author zhenhua zhang
 * @data 2019/12/13
 */
@Data
public class UserDto extends User {

    private List<String> roleDescList;
}
