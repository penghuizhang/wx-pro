package com.newcapec.sys.service;

import com.newcapec.sys.entity.SysUser;

import java.util.List;
import java.util.Map;

/**
* sys_acct, 系统账号表SV
*
* @author guoxianwei  2017-06-07 18:50:18
*/

public interface SysUserSV {

    /**
    * 查询系统账号表
    *
    * @param acct 账号
    * @return 结果集
    */
    List<SysUser> querySysUser(String acct);

    /**
<<<<<<< .mine
     * 查询所有的用户信息
     * @return
     */
    List<SysUser> querySysAllAcct();
    /**
||||||| .r11
=======
     * 查询系统账号表
     *
     * @return 结果集
     */
    List<SysUser> querySysUser();

    /**
>>>>>>> .r14
    * 新增系统账号表
    *
    * @param SysUser
    */
    int insert(SysUser SysUser);

    /**
    * 修改系统账号表
    *
    * @param SysUser
    */
    int update(SysUser SysUser);

    /**
    * 删除一条记录
    *
    * @param id 主键
    */
    int remove(Long id);

    /**
    * 统计系统账号表数量
    * @param params 参数
    * @return 系统账号表数量
    */
    int count(Map<String, Object> params);

    /**
    * 加载系统账号表
    *
    * @param id 主键
    * @return 系统账号表
    */
    SysUser load(Long id);


    /**
     * 加载系统账号表
     *
     * @param acct 账号
     * @return 系统账号表
     */
    SysUser loadSysAcctByAcct(String acct);

}
