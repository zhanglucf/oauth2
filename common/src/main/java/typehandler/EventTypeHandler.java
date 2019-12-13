package typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import typeenum.EventTypeEnum;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhenhua zhang
 * @data 2019/12/2
 */
public class EventTypeHandler extends BaseTypeHandler<EventTypeEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, EventTypeEnum eventTypeEnum, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, eventTypeEnum.getCode());
    }

    @Override
    public EventTypeEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return EventTypeEnum.getEnum(resultSet.getInt(s));
    }

    @Override
    public EventTypeEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return EventTypeEnum.getEnum(resultSet.getInt(i));
    }

    @Override
    public EventTypeEnum getNullableResult(CallableStatement cs, int i) throws SQLException {
        return EventTypeEnum.getEnum(cs.getInt(i));
    }
}
