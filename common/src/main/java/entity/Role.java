package entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * @author zhenhua zhang
 * @data 2019/12/13
 */
@Data
@Table(name = "sys_role")
public class Role extends BaseEntity{

    private String name;
}
