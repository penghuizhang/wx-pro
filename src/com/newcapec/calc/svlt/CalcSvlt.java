package com.newcapec.calc.svlt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author guoxianwei 2017-05-11 11:10:21
 */
public class CalcSvlt extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        int firstValue = Integer.parseInt(request.getParameter("firstValue"));  //获取页面第一个输入数字
        int secondValue = Integer.parseInt(request.getParameter("secondValue"));//获取页面第二个输入数字
        String operator = request.getParameter("operator");                     //获取页面选择的运算方式

        String result = Calc.calculate(firstValue, secondValue, operator);

        PrintWriter out = response.getWriter();
        out.print(result);
    }

}
