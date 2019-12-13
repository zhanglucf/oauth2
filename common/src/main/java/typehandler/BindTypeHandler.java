package typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import typeenum.BindTypeEnum;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhenhua zhang
 * @data 2019/12/2
 */
public class BindTypeHandler extends BaseTypeHandler<BindTypeEnum> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, BindTypeEnum bindTypeEnum, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, bindTypeEnum.getCode());
    }

    @Override
    public BindTypeEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return BindTypeEnum.getEnum(resultSet.getInt(s));
    }

    @Override
    public BindTypeEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return BindTypeEnum.getEnum(resultSet.getInt(i));
    }

    @Override
    public BindTypeEnum getNullableResult(CallableStatement cs, int i) throws SQLException {
        return BindTypeEnum.getEnum(cs.getInt(i));
    }
}
