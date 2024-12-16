package top.soft.classoa.datasource;


import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author 86188
 * @description: TODO
 * @date 2024/11/30 14:00
 */

public class DruidDataSourceFactory extends UnpooledDataSourceFactory {
    @Override
    public DataSource getDataSource() {
        try {
            ((DruidDataSource) this.dataSource).init();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return this.dataSource;
    }
    public DruidDataSourceFactory(){
        this.dataSource = new DruidDataSource();
    }
}