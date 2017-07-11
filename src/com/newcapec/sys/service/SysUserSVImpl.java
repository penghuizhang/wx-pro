package com.newcapec.sys.service;

import com.newcapec.sys.dao.SysUserDao;
import com.newcapec.sys.entity.SysUser;
import com.newcapec.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

/**
 * @author guoxianwei 2017-07-07 18:38:30
 */
public class SysUserSVImpl implements SysUserSV {

    private SysUserDao sysUserDao = new SysUserDao();

    @Override
    public List<SysUser> querySysUser(String acct) {
        return sysUserDao.querySysUser(acct);
    }

    /**
     * 查询系统账号表
     *
     * @return 结果集
     */
    @Override

    public List<SysUser> querySysAllAcct() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<SysUser> sysUsers = new ArrayList<>();
        try {
            connection = JdbcUtils.openConnection();
            String sql = "select id, acct,pwd from sys_user";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                SysUser sysUser = new SysUser();
                sysUser.setId(resultSet.getLong(1));
                sysUser.setAcct(resultSet.getString(2));
                sysUser.setPwd(resultSet.getString(3));
                sysUsers.add(sysUser);
                System.out.println(sysUser.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                JdbcUtils.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    return sysUsers;
    }

    public List<SysUser> querySysUser() {
        return sysUserDao.querySysUser();
    }

    @Override
    public int insert(SysUser SysUser) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int r = -1;
        try {
            connection = JdbcUtils.openConnection();
            String sql = "insert into sys_user(acct,pwd) values(?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,SysUser.getAcct());
            preparedStatement.setString(2,SysUser.getPwd());
            r = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return r;
    }


    public int update(SysUser SysUser) {
        int r = -1;
        Connection con=null;
        PreparedStatement stmt=null;
        try {
            con = JdbcUtils.openConnection();
            String sql = "update sys_user set acct=?,pwd=? where id=?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1,SysUser.getAcct());
            stmt.setString(2,SysUser.getPwd());
            stmt.setLong(3, Math.toIntExact(SysUser.getId()));
            r = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                JdbcUtils.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return r;
    }

    @Override
    public int remove(Long id) {
        int r = -1;
        sysUserDao.remove(id);
        Connection connection = null;
        PreparedStatement preparedStatement=null;

        String sql = "delete from sys_user where id ="+id;

        try {
            connection = JdbcUtils.openConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                JdbcUtils.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return r;
    }

    @Override
    public int count(Map<String, Object> params) {
        return 0;
    }

    @Override
    public SysUser load(Long id) {
        return null;
    }

    @Override
    public SysUser loadSysAcctByAcct(String acct) {
        return sysUserDao.loadSysAcctByAcct(acct);
    }
}
