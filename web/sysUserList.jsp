<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>

<!-- 栏目管理 -->
<div class="AccountManagement_c" id="ColumnManagement_c">
    <form action="${ctx}/compusnews/sysUser/list" method="post" id="form">
        <h3>账号管理
            <div class="amcl fr">
                <input type="text" placeholder="账号名称" class="fl" name="acct" value="${pager.params.acct}">
                <div class="search fl"><img src="${ctx}/static/back/images/search.png" onclick="search()"></div>
            </div>
        </h3>
    </form>
    <div class="AM_ct text_center">
        <div class="AM_ct_in">
            <div class="add_btn df_btn fl" id="add_column_btn">添加</div>
            <div class="edit_btn df_btn fl" id="edit_column_btn">编辑</div>
            <div class="delete_btn df_btn fl" id="delete_column_btn">删除</div>
            <%--<div class="fr df_btn ac_btn" id="ac_Account_btn">账号授权</div>--%>
        </div>
    </div>

    <div class="list">
        <ul class="list_h">
            <li class="b20"><label><input type="checkbox" id="chk_all"></label></li>
            <li class="b80"><label>账户名称</label></li>
        </ul>
        <div class="list_b_c">
            <c:forEach var="sysUserList" items="${sysUserList}">

                <ul class="list_b">
                    <li class="b20">
                        <label>
                            <input type="checkbox" value="${sysUserList.id}" id="chk_list" name="chk_list">
                        </label>
                    </li>
                    <li class="b80"><label class="sysuser_acct">${sysUserList.acct}</label></li>
                </ul>
            </c:forEach>
            <c:if test="${fn:length(pager.recordList)==0}">
                <ul class="list_null">
                    <li class="text_center">无记录！</li>
                </ul>
            </c:if>
        </div>
        <div class="pull_page">
            ${pager.formPageStr}
        </div>
    </div>
</div>

<!-- 添加栏目 -->
<div class="add_Account dn" id="add_column">
    <div class="add_Account_c">
        <div class="add_Account_h">
            <div class="add_Account_h_in">
                添加账号
                <span class="fr add_Account_close"><img src="${ctx}/static/back/images/close.png"></span>
            </div>
        </div>
        <div class="user_name user_i">
            <label>用户名</label>
            <input type="text" placeholder="输入用户名" name="sysName" id="add_sysuser_acct">
        </div>
        <div class="user_password user_i">
            <label>密<i>&nbsp;</i>码</label>
            <input type="password" placeholder="输入密码" name="sysPwd" id="add_sysuser_pwd">
        </div>
        <div class="add_Account_ok_btn text_center" id="add_column_ok_btn">确<i>&nbsp;</i>定</div>
    </div>
</div>

<!-- 编辑栏目 -->
<div class="add_Account dn" id="edit_column">
    <div class="add_Account_c">
        <div class="add_Account_h">
            <div class="add_Account_h_in">
                编辑账号
                <span class="fr add_Account_close"><img src="${ctx}/static/back/images/close.png"></span>
            </div>
        </div>
        <div class="user_name user_i">
            <label>用户名</label> <input type="text" placeholder="输入用户名" name="sysName" id="edit_sysuser_acct" readonly>
        </div>
        <div class="user_password user_i">
            <label>密<i>调</i>码</label> <input type="password" placeholder="输入密码" name="sysPwd" id="edit_sysuser_pwd">
        </div>
        <div class="add_Account_ok_btn text_center" id="edit_column_ok_btn">确<i>皮</i>定</div>
    </div>
</div>

<!-- 账户授权 -->
<div class="add_Account dn" id="ac_Account">
    <div class="add_Account_c">
        <div class="add_Account_h">
            <div class="add_Account_h_in">
                账户栏目授权
                <span class="fr add_Account_close"><img src="${ctx}/static/back/images/close.png"></span>
            </div>
        </div>
        <ul id="mainItem">
            <li><label>无新闻栏目可授权</label></li>
        </ul>
        <div class="add_Account_ok_btn text_center" id="ac_Account_ok_btn">保<i>呀</i>存</div>
    </div>
</div>

<!-- 删除账号 -->
<div class="add_Account dn" id="delete_column">
    <div class="add_Account_c">
        <div class="add_Account_h">
            <div class="add_Account_h_in">
                删除账号
                <span class="fr add_Account_close"><img src="${ctx}/static/back/images/close.png"></span>
            </div>
        </div>
        <div class="delete_text">确定删除选中的账号吗？</div>
        <div class="add_Account_ok_btn text_center" id="delete_column_ok_btn">确<i>皮</i>定</div>
    </div>
</div>

<script>

    function search() {
        $('#form').submit();
    }

    $(document).ready(function () {
        //全选
                $("#chk_all").click(function () {
                    $("input[name='chk_list']").prop("checked", $(this).prop("checked"));
                });
                //新增栏目
                $('#add_column_ok_btn').on('click', function (e) {
                    e.preventDefault();
                    var acct = $('#add_sysuser_acct').val();
                    var pwd = $('#add_sysuser_pwd').val();
                    if (acct == '' || pwd == '') {
                        alert('请填写用户名和密码');
                        return;
            }
            if (passwordLevel(pwd) == 1 || pwd.length < 6) {
                alert('密码必须是字母、数字、字符两种以上组合，且长度不能小于6位');
                return;
            }

            $.post(_CTX + "/sys/sysUserAddSvlt",

                    {
                        sysName: acct,
                        sysPwd: pwd
                    },
                    function (data) {

                        if (data) {
                            if (data.success) {
                                $(window.parent.document).find("#MainFrame").attr("src", _CTX + "/sys/sysUserSeachServlt");   // window可省略不写
                                $("#add_column_ok_btn").fadeOut(100);

                                clear();
                            } else {
                                alert(data.msg);
                            }
                        }
                    }, "json");
        });

        // 编辑弹窗
        $("#edit_column_btn").click(function () {
            var checkedCount = $("input[name='chk_list']:checked").length;
            if (checkedCount && checkedCount == 1) {
                $('#edit_sysuser_acct').val($("input[name='chk_list']:checked").parents("ul").find(".sysuser_acct").text());
                $("#edit_column").fadeIn(100);
            } else {
                alert("请选择一条记录编辑");
            }
        });

        //编辑栏目
        $('#edit_column_ok_btn').on('click', function (e) {
            e.preventDefault();
            var acct = $('#edit_sysuser_acct').val();
            var pwd = $('#edit_sysuser_pwd').val();
            if (acct == '' || pwd == '') {
                alert('请填写用户名和密码');
                return;
            }
            if (passwordLevel(pwd) == 1 || pwd.length < 6) {
                alert('密码必须是字母、数字、字符两种以上组合，切长度不能小于6位');
                return;
            }

            $.post(_CTX + "/sys/sysUserModifyServlet",

                    {
                        id: $("input[name='chk_list']:checked").val(),
                        acct: acct,
                        pwd: pwd
                    },
                    function (data) {
                        if (data) {
                            if (data.success) {
                                //$(window.parent.document).find("#MainFrame").attr("src", _CTX + "/compusnews/sysUser/list");   // window可省略不写
                                $(window.parent.document).find("#MainFrame").attr("src", _CTX + "/sys/sysUserSeachServlt");   // window可省略不写

                                $("#edit_column").fadeOut(100);
                                clear();
                            } else {
                                alert(data.msg);
                            }
                        }
                    }, "json");
        });

        // 删除栏目
        $("#delete_column_btn").click(function () {
            var checkedCount = $("input[name='chk_list']:checked").length;
            if (checkedCount && checkedCount >= 1) {
                $("#delete_column").fadeIn(100);
            } else {
                alert("请选择一条记录进行删除");
            }
        });

        //确认删除
        $("#delete_column_ok_btn").click(function () {
            var ids = "";
            $("input[name='chk_list']:checked").each(function () {

                //ids += $(this).val() + "_";
                ids += $(this).val();

            });

            $.post(_CTX + "/sys/sysUserDelServlet",

                    {
                        ids: ids
                    },
                    function (data) {
                        if (data) {
                            if (data.success) {
                                $(window.parent.document).find("#MainFrame").attr("src", _CTX + "/sys/sysUserSeachServlt");   // window可省略不写
                                $("#delete_column").fadeOut(100);
                                clear();
                            } else {
                                alert(data.msg);
                            }
                        }
                    }, "json");
        });

        //确认授权
        $("#ac_Account_ok_btn").click(function () {
            var chnlCodeList = "";
            $("input[name='chnlCode']:checked").each(function () {
                chnlCodeList += $(this).val() + "_";
            });
            var  acct = $("input[name='chk_list']:checked").parents('ul').find('.sysuser_acct').text();
            if(chnlCodeList=='' || acct ==''){
                alert("请选择授权账号和栏目");
                return;
            }
            $.post(_CTX + "/compusnews/sysUser/auth.json",
                    {
                        acct: acct,
                        chnlCodeList: chnlCodeList
                    },
                    function (data) {
                        if (data) {
                            if (data.success) {
                                alert("授权成功");
                                $("#ac_Account").fadeOut(100);
                            } else {
                                alert(data.msg);
                            }
                        }
                    }, "json");
        });


        function clear() {
            $('#add_sysuser_acct').val('');
            $('#add_sysuser_pwd').val('');
            $('#edit_sysuser_acct').val('');
            $('#edit_sysuser_pwd').val('');
        }

    });
</script>
</body>
</html>
