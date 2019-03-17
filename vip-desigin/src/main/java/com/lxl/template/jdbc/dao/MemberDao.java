package com.lxl.template.jdbc.dao;


import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import com.lxl.template.jdbc.JdbcTemplate;
import com.lxl.template.jdbc.Member;
import com.lxl.template.jdbc.RowMapper;

public class MemberDao extends JdbcTemplate {
    public MemberDao(DataSource dataSource) {
        super(dataSource);
    }

    
    public List<?> selectAll(){
        String sql = "select * from t_member";
        return executeQuery(sql, new RowMapper<Member>() {
			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws Exception {
				Member member = new Member();
                //字段过多，原型模式
				member.setUsername(rs.getString("username"));
                member.setPassword(rs.getString("password"));
                member.setAge(rs.getInt("age"));
                member.setAddr(rs.getString("addr"));
                return member;
			}
		}, null);
    }
   /* public List<?> selectAll(){
        String sql = "select * from t_member";
        return super.executeQuery(sql, new RowMapper<Member>() {
            public Member mapRow(ResultSet rs, int rowNum) throws Exception {
                Member member = new Member();
                //字段过多，原型模式
                member.setUsername(rs.getString("username"));
                member.setPassword(rs.getString("password"));
                member.setAge(rs.getInt("age"));
                member.setAddr(rs.getString("addr"));
                return member;
            }
        },null);
    }*/
}
