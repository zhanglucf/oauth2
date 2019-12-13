package typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import typeenum.MemberTypeEnum;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhenhua zhang
 * @data 2019/12/5
 */
public class MemberTypeHandler extends BaseTypeHandler<MemberTypeEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, MemberTypeEnum memberTypeEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, memberTypeEnum.getCode());
    }

    @Override
    public MemberTypeEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return MemberTypeEnum.getEnum(resultSet.getInt(s));
    }

    @Override
    public MemberTypeEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return MemberTypeEnum.getEnum(resultSet.getInt(i));
    }

    @Override
    public MemberTypeEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return MemberTypeEnum.getEnum(callableStatement.getInt(i));
    }
}
