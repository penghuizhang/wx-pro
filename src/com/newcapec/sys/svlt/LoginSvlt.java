package com.newcapec.sys.svlt;

import com.newcapec.global.utils.GsonUtil;
import com.newcapec.global.utils.MD5Util;
import com.newcapec.global.utils.RCUtil;
import com.newcapec.sys.entity.SysUser;
import com.newcapec.sys.service.SysUserSV;
import com.newcapec.sys.service.SysUserSVImpl;
import com.newcapec.utils.AjaxMsg;
import org.apache.commons.lang.StringUtils;
import org.patchca.utils.encoder.EncoderHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author guoxianwei 2017-07-07 14:44:30
 */
public class LoginSvlt extends HttpServlet {
    /**
     * 以GET协议请求
     */
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opt = req.getParameter("opt");
        if ("code".equalsIgnoreCase(opt)) {
            code(req, resp);
        }else if("index".equalsIgnoreCase(opt)){
            index(req,resp);
        }
    }

    /**
     * 以POST协议请求
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String opt = req.getParameter("opt");
        if ("log".equalsIgnoreCase(opt)) {
            login(req, resp);
        }
    }
    /**
     * 登录
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("content-type", "text/html;charset=UTF-8");
        String acct = req.getParameter("acct");
        String pwd = req.getParameter("pwd");
        String code = req.getParameter("code");
        if (StringUtils.isEmpty(code)) {
            write(GsonUtil.toJson(AjaxMsg.fail("请输入验证码")), resp);
        } else {
            String token = (String) req.getSession().getAttribute("captchaToken");
            if (!code.equalsIgnoreCase(token)) {
                write(GsonUtil.toJson(AjaxMsg.fail("验证码输入错误")), resp);
            }
        }
        if (acct != null && pwd != null) {
            SysUserSV sysUserSV = new SysUserSVImpl();
            SysUser sysUser = sysUserSV.loadSysAcctByAcct(acct);
            if (sysUser != null && acct.equals(sysUser.getAcct())) {
                //String md5 =  =
                if (pwd.equals(sysUser.getPwd())) {
                    req.getSession().setAttribute("SYS_USER", sysUser);
                    write(GsonUtil.toJson(AjaxMsg.success()), resp);
                } else {
                    write(GsonUtil.toJson(AjaxMsg.fail("登录账号或密码错误")), resp);
                }
            } else {
                write(GsonUtil.toJson(AjaxMsg.fail("登录账号或密码错误")), resp);
            }
        } else {
            write(GsonUtil.toJson(AjaxMsg.fail("登录账号或密码错误")), resp);
        }
    }

    /**
     * 首页
     */
    public void index(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/index.jsp");
    }

    /**
     * 验证码
     */
    public void code(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            session = request.getSession();
        }
        setResponseHeaders(response);
        String token = EncoderHelper.getChallangeAndWriteImage(RCUtil.getInstance(), "png", response.getOutputStream());
        session.setAttribute("captchaToken", token);
    }

    protected void setResponseHeaders(HttpServletResponse response) {
        response.setContentType("image/png");
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        long time = System.currentTimeMillis();
        response.setDateHeader("Last-Modified", time);
        response.setDateHeader("Date", time);
        response.setDateHeader("Expires", time);
    }

    public void write(String message, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.write(message);
        out.flush();
        out.close();
    }
}
