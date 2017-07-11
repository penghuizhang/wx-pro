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
 * @author guoxianwei 2017-07-07 14:41:30
 */
public class SysUserAddSvlt extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sysName = req.getParameter("sysName");
        System.out.println("sysName="+sysName);
        String sysPwd = req.getParameter("sysPwd");
        SysUser user = new SysUser();
        user.setPwd(sysPwd);
        user.setAcct(sysName);

        SysUserSV sysUserSV = new SysUserSVImpl();
        int r = sysUserSV.insert(user);

        write(GsonUtil.toJson(AjaxMsg.success()),resp);

        if (r>0){
            resp.sendRedirect("/sysUserList.jsp");

        }else{
            resp.sendRedirect("/sysUserList.jsp");
        }

    }




    /**
     * 异步响应，返回操作结果
     */
    private void write(String result, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.write(result);
        out.flush();
        out.close();
    }

}
