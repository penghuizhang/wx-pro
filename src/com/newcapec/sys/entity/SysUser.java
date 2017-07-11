package com.newcapec.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
* sys_acct, 系统账号表
*
* @author guoxianwei  2017-06-07 18:50:18
*/
public class SysUser implements Serializable{

    private Long id;          //主键
    private String name;          //姓名
    private String acct;          //登录账号
    private String pwd;          //登录密码
    private String biz_no;          //商家编号
    private java.util.Date last_logon_time;          //最近一次登录时间
    private String last_logon_ip;          //最近一次登录IP
    private String state;          //账号状态(NORMAL正常；INVALID无效；BLOCKED锁定）
    private String crby;          //创建人
    private String upby;          //更新人
    private java.util.Date crtime;          //创建时间
    private java.util.Date uptime;          //更新时间


    public SysUser(String acct, String pwd) {
        this.acct = acct;
        this.pwd = pwd;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getAcct(){
        return this.acct;
    }

    public void setAcct(String acct){
        this.acct=acct;
    }

    public String getPwd(){
        return this.pwd;
    }

    public void setPwd(String pwd){
        this.pwd=pwd;
    }

    public java.util.Date getLast_logon_time(){
        return this.last_logon_time;
    }

    public void setLast_logon_time(java.util.Date last_logon_time){
        this.last_logon_time=last_logon_time;
    }

    public String getLast_logon_ip(){
        return this.last_logon_ip;
    }

    public void setLast_logon_ip(String last_logon_ip){
        this.last_logon_ip=last_logon_ip;
    }

    public String getState(){
        return this.state;
    }

    public void setState(String state){
        this.state=state;
    }

    public String getCrby(){
        return this.crby;
    }

    public void setCrby(String crby){
        this.crby=crby;
    }

    public String getUpby(){
        return this.upby;
    }

    public void setUpby(String upby){
        this.upby=upby;
    }

    public java.util.Date getCrtime(){
        return this.crtime;
    }

    public void setCrtime(java.util.Date crtime){
        this.crtime=crtime;
    }

    public java.util.Date getUptime(){
        return this.uptime;
    }

    public void setUptime(java.util.Date uptime){
        this.uptime=uptime;
    }

    public String getBiz_no() {
        return biz_no;
    }

    public void setBiz_no(String biz_no) {
        this.biz_no = biz_no;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "acct='" + acct + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
    public SysUser(){

    }

    public SysUser(Long id, String name, String acct, String pwd, String biz_no, Date last_logon_time, String last_logon_ip, String state, String crby, String upby, Date crtime, Date uptime) {
        this.id = id;
        this.name = name;
        this.acct = acct;
        this.pwd = pwd;
        this.biz_no = biz_no;
        this.last_logon_time = last_logon_time;
        this.last_logon_ip = last_logon_ip;
        this.state = state;
        this.crby = crby;
        this.upby = upby;
        this.crtime = crtime;
        this.uptime = uptime;
    }

    public SysUser(Long id, String acct, String pwd) {
        this.id = id;
        this.acct = acct;
        this.pwd = pwd;
    }
}