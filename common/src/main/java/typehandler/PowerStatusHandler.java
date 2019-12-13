package typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import typeenum.PowerStatusEnum;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhenhua zhang
 * @data 2019/11/29
 */
//@MappedJdbcTypes(JdbcType.TINYINT)
//@MappedTypes(value = PowerStatusEnum.class)
public class PowerStatusHandler extends BaseTypeHandler<PowerStatusEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, PowerStatusEnum powerStatusEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, powerStatusEnum.getCode());
    }

    @Override
    public PowerStatusEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {

        return PowerStatusEnum.getEnum(resultSet.getInt(s));
    }

    @Override
    public PowerStatusEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return PowerStatusEnum.getEnum(resultSet.getInt(i));
    }

    @Override
    public PowerStatusEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return PowerStatusEnum.getEnum(callableStatement.getInt(i));
    }
}
