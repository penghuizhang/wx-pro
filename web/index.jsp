<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<div class="header">
    <div class="header_in">
        <img src="${ctx}/static/back/images/tit.png">

        <div class="quit text_center">安全退出</div>
    </div>
</div>
<div class="content ">
    <div class="user">
        <div class="user_status fl text_center">当前用户： <span>${acct}</span></div>
        <div class="user_location fl">当前： 首页-<span>账户管理</span></div>
    </div>
    <div class="nav_side text_center fl">
        <div class="nav AccountManagement active">账户管理</div>
        <div class="nav ColumnManagement">产品管理</div>
        <div class="nav NewsManagement">新闻管理</div>
        <div class="nav RsvpManagement">活动申请</div>
        <div class="nav ContactManagement">客户预约</div>
        <div class="nav ApplicationManagement">合同申请</div>
        <div class="nav CustManagement">客户管理</div>
    </div>
    <div class="main_fx">
        <div class="AccountManagement_c_iframe">
            <iframe id="MainFrame" src="${ctx}/sys/sysUserSeachServlt"></iframe>
        </div>
    </div>
</div>

<script>
    var _CTX = '${ctx}';
    $().ready(function () {
        $('div.quit').click(function (e) {
            window.location.href = _CTX + "/logout";
        });
    });
</script>
</body>
</html>