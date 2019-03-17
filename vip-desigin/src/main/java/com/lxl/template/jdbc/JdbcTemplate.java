package com.lxl.template.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public abstract class JdbcTemplate {
    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public List<?> executeQuery(String sql, RowMapper<?> rowMapper, Object[] values){
        try {
        	//1、获取连接
            Connection connection = this.getConnection();
            //2、创建语句集
            PreparedStatement pstm = this.creatPreparedStatement(connection, sql);
            //3、执行语句集
            ResultSet rs = this.executeQuery(pstm,values);            //4、处理结果集
            List<?> result = this.parseResultSet(rs,rowMapper);
            //5、关闭结果集
            this.closeResultSet(rs);
            //6、关闭语句集
            this.closeStatement(pstm);
            //7、关闭连接
            this.closeConnection(connection);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    protected void closeConnection(Connection conn) throws Exception {
        //数据库连接池，我们不是关闭
        conn.close();
    }

    protected void closeStatement(PreparedStatement pstm) throws Exception {
        pstm.close();
    }

    protected void closeResultSet(ResultSet rs) throws Exception {
        rs.close();
    }

	private List<?> parseResultSet(ResultSet rs, RowMapper<?> rowMapper) throws SQLException, Exception {
		List<Object> result = new ArrayList<Object>();
        int rowNum = 1;
        while (rs.next()){
            result.add(rowMapper.mapRow(rs,rowNum ++));
        }
        return result;
	}

	private ResultSet executeQuery(PreparedStatement pstm, Object[] values) throws SQLException {
		for (int i = 0; i < values.length; i++) {
            pstm.setObject(i,values[i]);
        }
		return pstm.executeQuery();
	}

	private PreparedStatement creatPreparedStatement(Connection connection,
			String sql) throws SQLException {
		return connection.prepareStatement(sql);
	}

	private Connection getConnection() throws SQLException {
		return this.dataSource.getConnection();
	}

}
