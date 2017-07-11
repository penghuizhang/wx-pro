package com.newcapec.sys.dao;

import com.newcapec.sys.entity.SysUser;
import com.newcapec.utils.JdbcUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * @author guoxianwei 2017-07-07 18:39:30
 */
public class SysUserDao {

    public static final String insertSysUserSQL = "INSERT INTO `wx_cms`.`sys_user` (`name`, `acct`, `pwd`, `biz_no`, `last_logon_time`, `last_logon_ip`, `state`, `crby`, `upby`, `crtime`, `uptime`) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String updateSysUserSQL = "update `wx_cms`.`sys_user` set pwd=?,upby=?,uptime=? where id = ?";

    public static final String deleteSysUserSQL = "delete from `wx_cms`.`sys_user` where id = ?";


    public SysUser loadSysAcctByAcct(String acct) {
        Connection con = null;
        try {
            con = JdbcUtils.openConnection();
            SysUser sysUser = JdbcUtils.queryBean(con, "select * from sys_user where acct=?", SysUser.class, acct);
            return sysUser;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new SysUser();
    }

    public List<SysUser> querySysUser(String acct) {
        List<SysUser> sysUserList = null;
        try {
            Connection con = JdbcUtils.openConnection();
            sysUserList = JdbcUtils.queryBeanList(con, "select * from sys_user where acct = ? ", SysUser.class, acct);
            return sysUserList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (sysUserList != null) {
            return sysUserList;
        }
        return Collections.emptyList();
    }

    public List<SysUser> querySysUser() {
        List<SysUser> sysUserList = null;
        try {
            Connection con = JdbcUtils.openConnection();
            sysUserList = JdbcUtils.queryBeanList(con, "select * from sys_user  ", SysUser.class);
            return sysUserList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (sysUserList != null) {
            return sysUserList;
        }
        return Collections.emptyList();
    }

    public void insert(SysUser sysUser) {
        Connection con = null;
        try {
            con = JdbcUtils.openConnection();
            JdbcUtils.execute(con, insertSysUserSQL, new Object[]{null, sysUser.getAcct(), sysUser.getPwd(), null, null, null, 1, sysUser.getCrby(), sysUser.getUpby(), sysUser.getCrtime(), sysUser.getUptime()});
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(SysUser sysUser) {
        Connection con = null;
        try {
            con = JdbcUtils.openConnection();
            JdbcUtils.execute(con, updateSysUserSQL, new Object[]{sysUser.getPwd(),sysUser.getId(), sysUser.getUpby(), sysUser.getUptime()});
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(Long id) {
        Connection con = null;
        try {
            con = JdbcUtils.openConnection();
            JdbcUtils.execute(con, deleteSysUserSQL, new Object[]{id});
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
