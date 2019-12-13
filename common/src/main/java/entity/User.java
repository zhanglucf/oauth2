package entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author zhenhua zhang
 * @data 2019/12/12
 */
@Data
@Table(name = "sys_user")
public class User extends BaseEntity {

    @Column(name = "user_name")
    private String userName;

    @Column(name = "pass_word")
    private String passWord;
}
