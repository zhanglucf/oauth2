package entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * @author zhenhua zhang
 * @data 2019/12/12
 */
@Data
@Table(name = "role")
public class MyRole extends BaseEntity {
    private Long userId;
    private String role;
}
