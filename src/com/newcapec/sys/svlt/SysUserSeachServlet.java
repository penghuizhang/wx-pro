package com.newcapec.sys.svlt;

import com.newcapec.sys.entity.SysUser;
import com.newcapec.sys.service.SysUserSV;
import com.newcapec.sys.service.SysUserSVImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by kylin on 2017/7/10.
 */

public class SysUserSeachServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SysUserSV sysUserSV = new SysUserSVImpl();

        List<SysUser> sysUserList = sysUserSV.querySysAllAcct();

        System.out.println(sysUserList.toString());
        request.setAttribute("sysUserList",sysUserList);
        System.out.println(sysUserList.toString());
        //request.getRequestDispatcher("/sys/sysUserSeachServlet").forward(request,response);
        request.getRequestDispatcher("/sysUserList.jsp").forward(request,response);
    }
}
