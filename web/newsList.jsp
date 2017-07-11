<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>
<body>
<!-- 新闻管理 -->
<div class="AccountManagement_c" id="NewsManagement_c">
    <form action="${ctx}/compusnews/news/list" method="post" id="form">
        <h3>新闻管理
            <div class="amcl fr">
                <select class="fl NewsManagement_search" name="type">
                    <c:forEach var="newsChnl" items="${newsChnlList}">
                        <option value="${newsChnl}">${newsChnl}</option>
                    </c:forEach>
                </select>
                <input type="text" placeholder="关键字" class="fl" name="name">

                <div class="search fl"><img src="${ctx}/static/images/search.png" onclick="search()"></div>
            </div>
        </h3>
    </form>
    <div class="AM_ct text_center">
        <div class="AM_ct_in">
            <div class="add_btn df_btn fl" id="add_news_btn">添加</div>
            <div class="edit_btn df_btn fl" id="edit_news_btn">编辑</div>
            <div class="delete_btn df_btn fl" id="delete_news_btn">删除</div>
        </div>
    </div>
    <div class="list">
        <ul class="list_h">
            <li class="b5"><label><input type="checkbox" id="chk_all"></label></li>
            <li class="b50"><label>新闻标题</label></li>
            <li class="b10"><label>栏目名称</label></li>
            <li class="b15"><label>发布时间</label></li>
            <li class="b20"><label>操作</label></li>
        </ul>
        <div class="list_b_c">
            <c:forEach var="news" items="${pager.recordList}">
                <ul class="list_b">
                    <li class="b5">
                        <label>
                            <input type="checkbox" value="${news.id}" id="chk_list" name="chk_list">
                        </label>
                    </li>
                    <li class="b50"><label class="chnl_name"><c:if test="${news.is_top ==1}"><font color="red"> [置顶]</font></c:if>${news.title}</label></li>
                    <li class="b10"><label class="chnl_name">${news.type}</label></li>
                    <li class="b15"><label class="chnl_name"><fmt:formatDate value="${news.crtime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate> </label></li>
                    <li class="b20">
                        <label class="chnl_name">
                            <c:if test="${news.is_top ==0}"><a href="javascript:void(0);" topClick="1"><font color="green"> [置顶]</font></a> </c:if>
                            <c:if test="${news.is_top ==1}"><a href="javascript:void(0);" topClick="0"><font color="green"> [取消置顶]</font></a> </c:if>

                            <c:if test="${news.is_enable ==0}"><a href="javascript:void(0);" enableClick="1"><font color="green"> [置为有效]</font></a> </c:if>
                            <c:if test="${news.is_enable ==1}"><a href="javascript:void(0);" enableClick="0"><font color="green"> [置为无效]</font></a> </c:if>

                        </label>
                    </li>
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

<!-- 删除新闻 -->
<div class="add_Account dn" id="delete_column">
    <div class="add_Account_c">
        <div class="add_Account_h">
            <div class="add_Account_h_in">
                删除新闻
                <span class="fr add_Account_close"><img src="${ctx}/static/images/close.png"></span>
            </div>
        </div>
        <div class="delete_text">确定删除选中的新闻吗？</div>
        <div class="add_Account_ok_btn text_center" id="delete_news_ok_btn">确<i>皮</i>定</div>
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

        // 新增页面
        $("#add_news_btn").click(function () {
            $(window.parent.document).find("#MainFrame").attr("src", _CTX + "/compusnews/news/add");   // window可省略不写
        });

       //新闻置顶操作
        $("a[topClick]").click(function () {
            var top = $(this).attr("topClick");
            var id = $(this).parents('ul').find('#chk_list').val();
            $.post(_CTX + "/compusnews/news/top.json",
                    {
                        id: id, top: top
                    },
                    function (data) {
                        if (data) {
                            if (data.success) {
                                $(window.parent.document).find("#MainFrame").attr("src", _CTX + "/compusnews/news/list");   // window可省略不写
                            } else {
                                alert(data.msg);
                            }
                        }
                    }, "json");
        });

        $("a[enableClick]").click(function () {
            var enable = $(this).attr("enableClick");
            var id = $(this).parents('ul').find('#chk_list').val();
            $.post(_CTX + "/compusnews/news/enable.json",
                {
                    id: id, enable: enable
                },
                function (data) {
                    if (data) {
                        if (data.success) {
                            $(window.parent.document).find("#MainFrame").attr("src", _CTX + "/compusnews/news/list");   // window可省略不写
                        } else {
                            alert(data.msg);
                        }
                    }
                }, "json");
        });

        // 编辑页面
        $("#edit_news_btn").click(function () {
            var checkedCount = $("input[name='chk_list']:checked").length;
            if (checkedCount && checkedCount == 1) {
                var id = $("input[name='chk_list']:checked").val();
                $(window.parent.document).find("#MainFrame").attr("src", _CTX + "/compusnews/news/edit?id=" + id);   // window可省略不写
            } else {
                alert("请选择一条记录编辑");
            }
        });

        // 删除栏目
        $("#delete_news_btn").click(function () {
            var checkedCount = $("input[name='chk_list']:checked").length;
            if (checkedCount && checkedCount >= 1) {
                $("#delete_column").fadeIn(100);
            } else {
                alert("请选择一条记录进行删除");
            }
        })

        $("#delete_news_ok_btn").click(function () {
            var ids = "";
            $("input[name='chk_list']:checked").each(function () {
                ids += $(this).val() + "_";
            });
            $.post(_CTX + "/compusnews/news/remove.json",
                    {
                        ids: ids
                    },
                    function (data) {
                        if (data) {
                            if (data.success) {
                                $(window.parent.document).find("#MainFrame").attr("src", _CTX + "/compusnews/news/list");   // window可省略不写
                                $("#delete_column").fadeOut(100);
                            } else {
                                alert(data.msg);
                            }
                        }
                    }, "json");
        })
    });

</script>
</body>
</html>