<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx"
       value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<script>
    var _CTX = '${ctx}';
</script>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/back/css/main.css">
</head>
<body>

<div class="login_head">
    <div class="container">
        <img src="${ctx}/static/images/logo.png">
    </div>
</div>

<form id="form" action="" method="post" validator="true">
    <div class="login_banner">
        <div class="container">
            <div class="login">
                <div class="login_in">
                    <div class="login_h">后台登录</div>
                    <div class="user_login">
                        <input type="text" placeholder="账号" id="acct" name="acct" value="gxw">
                        <input type="password" placeholder="密码" id="pwd" name="pwd" value="1">
                    </div>
                    <div class="img_code">
                        <input type="text" placeholder="验证码" id="vcode" name="code">
                        <img src="${ctx}/login?opt=code" id="code" alt="" onclick="change(this)">
                    </div>
                    <div class="login_btn">登录</div>
                </div>
            </div>
        </div>
    </div>
</form>
<div class="login_end text_center">版权所有&copy;：</div>
<script src="${ctx}/static/back/lib/common/jquery-1.11.1.min.js"></script>

<script>
    var _CTX = '${ctx}';

    if (window != top)
        top.location.href = location.href;

    function change(srcObj) {
        srcObj.src = _CTX + "/login?opt=code&v=" + Math.random();
    }

    $().ready(function () {
        $('div.login_btn').click(function (e) {
            var acct = $('#acct').val();
            var pwd = $('#pwd').val();
            var vcode = $('#vcode').val();
            if (acct == null || acct == '') {
                alert("请输入账号");
                $('acct').focus();
                return;
            }
            if (pwd == null || pwd == '') {
                alert("请输入密码");
                $('pwd').focus();
                return;
            }
            if (vcode == null || vcode == '') {
                alert("请输入验证码");
                $('vcode').focus();
                return;
            }
            $(this).attr('disabled', 'disabled').html('<i></i>登录中...');
            $.post(_CTX + "/login?opt=log",
                    $('#form').serialize(),
                    function (data) {
                        if (data) {
                            if (data.success) {
                                $('div.login_btn').removeAttr('disabled').html('登录');
                                window.location.href = _CTX + "/login?opt=index";
                            } else {
                                $('div.login_btn').removeAttr('disabled').html('登录');
                                alert(data.msg);
                                $('#code').attr("src", _CTX + "/login?opt=code&v=" + Math.random());
                            }
                        }
                    }, "json");

        });
    });
</script>
</body>
</html>