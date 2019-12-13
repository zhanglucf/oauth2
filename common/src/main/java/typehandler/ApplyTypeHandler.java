package typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import typeenum.ApplyTypeEnum;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhenhua zhang
 * @data 2019/12/2
 */
public class ApplyTypeHandler extends BaseTypeHandler<ApplyTypeEnum> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ApplyTypeEnum applyTypeEnum, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, applyTypeEnum.getCode());
    }

    @Override
    public ApplyTypeEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return ApplyTypeEnum.getEnum(resultSet.getInt(s));
    }

    @Override
    public ApplyTypeEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return ApplyTypeEnum.getEnum(resultSet.getInt(i));
    }

    @Override
    public ApplyTypeEnum getNullableResult(CallableStatement cs, int i) throws SQLException {
        return ApplyTypeEnum.getEnum(cs.getInt(i));
    }
}
