package typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import typeenum.ImageTypeEnum;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhenhua zhang
 * @data 2019/12/2
 */
public class ImageTypeHandler extends BaseTypeHandler<ImageTypeEnum> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ImageTypeEnum imageTypeEnum, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, imageTypeEnum.getCode());
    }

    @Override
    public ImageTypeEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return ImageTypeEnum.getEnum(resultSet.getInt(s));
    }

    @Override
    public ImageTypeEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return ImageTypeEnum.getEnum(resultSet.getInt(i));
    }

    @Override
    public ImageTypeEnum getNullableResult(CallableStatement cs, int i) throws SQLException {
        return ImageTypeEnum.getEnum(cs.getInt(i));
    }
}
