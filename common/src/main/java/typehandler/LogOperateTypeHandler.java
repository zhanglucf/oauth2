package typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import typeenum.LogOperateTypeEnum;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhenhua zhang
 * @data 2019/12/5
 */
public class LogOperateTypeHandler extends BaseTypeHandler<LogOperateTypeEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, LogOperateTypeEnum logOperateTypeEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, logOperateTypeEnum.getCode());
    }

    @Override
    public LogOperateTypeEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return LogOperateTypeEnum.getEnum(resultSet.getInt(s));
    }

    @Override
    public LogOperateTypeEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return LogOperateTypeEnum.getEnum(resultSet.getInt(i));
    }

    @Override
    public LogOperateTypeEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return LogOperateTypeEnum.getEnum(callableStatement.getInt(i));
    }
}
