package com.newcapec.wx.svlt;

import com.newcapec.wx.utils.SignUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by kylin on 2017/7/9.
 */
public class WxSvlt extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String timestamp = req.getParameter("timestamp");
        String signature = req.getParameter("signature");
        String nonce = req.getParameter("nonce");
        String echostr = req.getParameter("echostr");

        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        PrintWriter out = resp.getWriter();
        if (SignUtil.checkSignature(signature,timestamp,nonce)){
            out.write(echostr);
        }
        out.flush();
        out.close();
        out =null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doPost(req,resp);
    }


}

