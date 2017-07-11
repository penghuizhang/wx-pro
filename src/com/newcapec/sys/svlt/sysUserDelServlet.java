package com.newcapec.sys.svlt;

import com.newcapec.global.utils.GsonUtil;
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
public class sysUserDelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long ids = Long.valueOf(request.getParameter("ids"));
        SysUserSV sysUserSV = new SysUserSVImpl();
        int r = sysUserSV.remove(ids);
        write(GsonUtil.toJson(AjaxMsg.success()),response);
        if (r>0){
            response.sendRedirect("/sysUserList.jsp");
        }else{

        }
    }

    private void write(String s, HttpServletResponse response) {
        try {
            PrintWriter out= response.getWriter();
            out.write(s);
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
