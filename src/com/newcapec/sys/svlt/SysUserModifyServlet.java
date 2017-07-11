package com.newcapec.sys.svlt;

import com.newcapec.global.utils.GsonUtil;
import com.newcapec.sys.entity.SysUser;
import com.newcapec.sys.service.SysUserSV;
import com.newcapec.sys.service.SysUserSVImpl;
import com.newcapec.utils.AjaxMsg;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by kylin on 2017/7/10.
 */

public class SysUserModifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String acct = request.getParameter("acct");
                String pwd = request.getParameter("pwd");
                Long id = Long.valueOf(request.getParameter("id"));
                SysUser sysUser = new SysUser();
                sysUser.setPwd(pwd);
                sysUser.setAcct(acct);
                sysUser.setId(id);

                SysUserSV sysUserSV = new SysUserSVImpl();
                int r= sysUserSV.update(sysUser);
        write(GsonUtil.toJson(AjaxMsg.success()),response);
        if (r>0){
            response.sendRedirect("/sysUserList.jsp");
        }else{
            response.sendRedirect("/sysUserList.jsp");
        }
    }

    private void write(String s, HttpServletResponse response) {
        try {
            PrintWriter out= response.getWriter();
            out.write(s);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }


}
