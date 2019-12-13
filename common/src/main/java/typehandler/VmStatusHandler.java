package typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import typeenum.VmStatusEnum;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhenhua zhang
 * @data 2019/12/2
 */
public class VmStatusHandler extends BaseTypeHandler<VmStatusEnum> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, VmStatusEnum vmStatusEnum, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, vmStatusEnum.getCode());
    }

    @Override
    public VmStatusEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return VmStatusEnum.getEnum(resultSet.getInt(s));
    }

    @Override
    public VmStatusEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return VmStatusEnum.getEnum(resultSet.getInt(i));
    }

    @Override
    public VmStatusEnum getNullableResult(CallableStatement cs, int i) throws SQLException {
        return VmStatusEnum.getEnum(cs.getInt(i));
    }
}
