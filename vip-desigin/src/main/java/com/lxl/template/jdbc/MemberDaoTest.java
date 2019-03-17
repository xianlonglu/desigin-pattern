package com.lxl.template.jdbc;

import java.util.List;

import com.lxl.template.jdbc.dao.MemberDao;

public class MemberDaoTest {

    public static void main(String[] args) {
        MemberDao memberDao = new MemberDao(null);
        List<?> result = memberDao.selectAll();
        System.out.println(result);
    }
}
