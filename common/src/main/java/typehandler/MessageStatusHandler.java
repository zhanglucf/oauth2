package typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import typeenum.MessageStatusEnum;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhenhua zhang
 * @data 2019/11/29
 */
//@MappedJdbcTypes(JdbcType.TINYINT)
//@MappedTypes(value = SexEnum.class)
public class MessageStatusHandler extends BaseTypeHandler<MessageStatusEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, MessageStatusEnum messageStatusEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, messageStatusEnum.getCode());
    }

    @Override
    public MessageStatusEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return MessageStatusEnum.getEnum(resultSet.getInt(s));
    }

    @Override
    public MessageStatusEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return MessageStatusEnum.getEnum(resultSet.getInt(i));
    }

    @Override
    public MessageStatusEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return MessageStatusEnum.getEnum(callableStatement.getInt(i));
    }
}
