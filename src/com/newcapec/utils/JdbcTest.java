package com.newcapec.utils;

import com.newcapec.sys.entity.SysUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author guoxianwei 2017-07-07 16:12:30
 */
public class JdbcTest {

    private Connection con = null;

    @Before
    public void setUp() throws Exception {
        try {
            con = JdbcUtils.openConnection();
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @After
    public void tearDown() throws Exception {
        try {
            JdbcUtils.closeConnection();
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testQueryBeanListConnectionStringClassOfT() {
        String sql = "SELECT * FROM sys_user";
        try {
            List<SysUser> emList = JdbcUtils.queryBeanList(con, sql, SysUser.class);
            print(emList);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testQueryBeanListConnectionStringClassOfTObjectArray() {
        String sql = "SELECT * FROM sys_user t WHERE t.salary > ? and T.JOB_ID = ?";
        try {
            List<SysUser> emList = JdbcUtils.queryBeanList(con, sql, SysUser.class, 5000, "ST_MAN");
            print(emList);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testQueryBeanListConnectionStringIResultSetCallOfTObjectArray() {
        String sql = "SELECT * FROM sys_user t WHERE t.acct = ?";
        try {
            List<SysUser> emList = JdbcUtils.queryBeanList(con, sql, new JdbcUtils.IResultSetCall<SysUser>() {
                public SysUser invoke(ResultSet rs) throws SQLException {
                    SysUser sysUser = new SysUser();
                    sysUser.setAcct(rs.getString("acct"));
                    sysUser.setId(rs.getLong("id"));
                    sysUser.setCrtime(rs.getDate("crtime"));
                    return sysUser;
                }
            }, "gxw" );
            print(emList);
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testQueryObjectListConnectionStringClassOfT() {
        String sql = "SELECT email FROM sys_user t";
        try {
            List<String> lists = JdbcUtils.queryObjectList(con, sql, String.class);
            print(lists);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testQueryObjectListConnectionStringClassOfTObjectArray() {
        String sql = "SELECT salary FROM sys_user t WHERE t.salary > ? and T.JOB_ID = ?";
        try {
            List<Double> lists = JdbcUtils.queryObjectList(con, sql, Double.class, 2000, "ST_MAN");
            print(lists);
        } catch (Exception e) {
            fail(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void testQueryBeanConnectionStringClassOfT() {
        String sql = "SELECT * FROM sys_user t WHERE t.employee_id in (120)";
        try {
            SysUser emp = JdbcUtils.queryBean(con, sql, SysUser.class);
            print(emp);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testQueryBeanConnectionStringClassOfTObjectArray() {
        String sql = "SELECT * FROM sys_user t WHERE t.employee_id = ?";
        try {
            SysUser emp = JdbcUtils.queryBean(con, sql, SysUser.class, 120);
            print(emp);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testQueryObjectConnectionStringClassOfT() {
        String sql = "SELECT email FROM sys_user t WHERE t.employee_id in (120)";
        try {
            String s = JdbcUtils.queryObject(con, sql, String.class);
            print(s);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testQueryObjectConnectionStringClassOfTObjectArray() {
        String sql = "SELECT salary FROM sys_user t WHERE t.employee_id in (?)";
        try {
            Double d = JdbcUtils.queryObject(con, sql, Double.class, 12);
            print(d);
        } catch (Exception e) {
            fail(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void testExecuteConnectionStringObjectArray() {
        String sql = "UPDATE sys_user t SET t.salary =? WHERE t.employee_id =?";
        try {
            con.setAutoCommit(false);
            int count = JdbcUtils.execute(con, sql, 20000, 120);
            Assert.assertTrue(count == 1);
            sql = "SELECT t.salary FROM sys_user t WHERE t.employee_id =?";
            Double d = JdbcUtils.queryObject(con, sql, Double.class, 120);
            Assert.assertTrue(d - 20000 == 0);
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        } finally {
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testQueryMapList() {
        String sql = "SELECT first_name, last_name, salary FROM sys_user t WHERE t.salary > ? and T.JOB_ID = ?";
        try {
            List<Map<String, Object>> lists = JdbcUtils.queryMapList(con, sql, 3000, "ST_MAN");
            print(lists);
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }


    @Test
    public void testExecuteAsBatch() {
        try {
            List<String> sqlList = new ArrayList<String>();
            sqlList.add("UPDATE sm_user t SET t.password = 'ok' WHERE t.row_id = '232s43' ");
            sqlList.add("UPDATE sm_user t SET t.password = 'ok' WHERE t.row_id = '232f42' ");
            sqlList.add("UPDATE sm_user t SET t.password = 'ok' WHERE t.row_id = '23g2423' ");
            sqlList.add("UPDATE sm_user t SET t.password = 'ok' WHERE t.row_id = '232434s' ");
            JdbcUtils.executeAsBatch(con, sqlList);
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void testExecuteAsBatchForPre() {
        try {
            JdbcUtils.executeAsBatch(con, "UPDATE sys_user t SET t.first_name = ? WHERE t.last_name = ? ", new Object[][]{
                    {"ok", "235jklsd"}, {"no", "jg4ti324"}, {"no1", "111"}, {"no2", "32423"}});
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    private void print(Object obj) {
        if (obj instanceof List) {
            List list = (List) obj;
            for (Object o : list) {
                if (o instanceof Map) {
                    Map<String, Object> map = (Map<String, Object>) o;
                    Set<String> set = map.keySet();
                    for (String key : set) {
                        Object value = map.get(key);
                        System.out.print(key + ":" + value + "\t");
                    }
                    System.out.println();
                } else {
                    System.out.println(o);
                }
            }
            System.out.println("???????????????????" + list.size());
        } else {
            System.out.println(obj);
        }
    }

    public void fail(String exception){
        System.out.printf(exception);
    }
}
