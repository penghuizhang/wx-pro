<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="_layout_header.jsp" %>

<body>
<!-- 新闻管理 -->
<form method="post" action="" id="ajaxForm">
    <div class="release_news">
        <div class="news_title">
            <label class="text_center">标题</label>
            <input type="text" placeholder="填写标题(应控制在32个汉字以内）" id="title" name="title" maxlength="32">
        </div>
        <div class="common_text">
            <label class="text_center">主图</label>
            <input type="text" id="mainImg" name="main_img" size="20" style=" width: 460px;" readonly
                   maxlength="128" value=""><a href="javascript:void(0)" onclick="openUploadWin()">上传主图</a>
        </div>
        <div class="column_name">
            <label class="text_center">相关产品</label>
            <select class="column_name_release" id="prod_id" name="prod_id">
                <c:forEach var="prod" items="${prodList}">
                    <option value="${prod.id}">${prod.title}</option>
                </c:forEach>
            </select>
        </div>
        <div class="column_name">
            <label class="text_center">类型</label>
            <select class="column_name_release" id="chnlCode" name="type">
                <c:forEach var="newsChnl" items="${productTypeList}">
                    <option value="${newsChnl}">${newsChnl}</option>
                </c:forEach>
            </select>
        </div>
        <div class="column_name">
            <label class="text_center">是否置顶</label>
            <select class="column_name_release" name="is_top">
                <option value="0" selected>否</option>
                <option value="1">是</option>
            </select>
        </div>
        <div class="column_content">
                        <textarea id="container" rows="100" cols="100" placeholder="" name="content"
                                  style="height:500px;" class="release_news_content"></textarea>
            <script type="text/javascript"
                    src="${ctx}/static/ckeditor/ckeditor.js?t=B37D54V"></script>
            <script type="text/javascript">
                //<![CDATA[
                CKEDITOR.replace('container');
                //]]>
            </script>
        </div>
        <div class="release_news_ok_btn text_center">发<i>我</i>布</div>
    </div>
</form>
<!-- 添加栏目 -->
<div class="add_Account dn" id="add_main_win">
    <div class="add_Account_c" style=" height: 240px;">
        <div class="add_Account_h">
            <div class="add_Account_h_in">
                主图上传
                <span class="fr add_Account_close"><img src="${ctx}/static/images/close.png"></span>
            </div>
        </div>
        <div class="user_name user_i">
            <input type="file" id="main_img">
        </div>
        <div class="add_Account_ok_btn text_center" id="add_img_ok_btn" onclick="ajaxFileUpload()">确<i>&nbsp;</i>定</div>
    </div>
</div>
<script src='${ctx}/static/js/common/jquery.form.js'></script>
<script src='${ctx}/static/js/fileupload_js/LocalResizeIMG.js'></script>
<script src='${ctx}/static/js/fileupload_js/patch/mobileBUGFix.mini.js'></script>
<script src='${ctx}/static/js/fileupload_js/dist/lrz.bundle.js'></script>
<script>

    function openUploadWin() {
        $("#add_main_win").fadeIn(100);
    }

    function ajaxFileUpload() {
        if ($('#main_img').val() == '') {
            alert("图片为空");
            return false;
        } else {
            var file = $('#main_img').val();
            var mime = file.toLowerCase().substr(file.lastIndexOf("."));
            if (mime != ".jpg"
                    && mime != ".jpeg"
                    && mime != ".png"
                    && mime != ".gif") {
                $('#main_img').val('')
                alert("图片格式不正确!");
                return false;
            }
        }
        //以图片宽度为1000进行压缩
        //alert($("#file1")[0].files[0]);
        lrz($("#main_img")[0].files[0], {
            width: 300
        }).then(function (rst) {
            //压缩后异步上传
            //alert(rst);
            $.ajax({
                url: '${ctx}/compusnews/news/uploadImg.json',
                type: "POST",
                data: {
                    filename: rst.origin.name,
                    fileLength: rst.base64.length,
                    imgdata: rst.base64//压缩后的base值
                },
                dataType: "json",
                cache: true,
                async: false,
                success: function (data) {
                    if (data.success) {
                        $("#add_main_win").fadeOut(100);
                        alert('上传图片成功');
                        $("#mainImg").val(data.msg);
                    } else {
                        alert(data.msg);
                    }
                },
                error: function () {
                    alert("上传失败,可能是网络问题，请重新上传！");
                }
            });
        }).catch(function (err) {
            // 处理失败会执行
            alert('上传失败,可能是网络问题，请重新上传！');
        }).always(function () {
            //return false;
        });
        //
        return false;
    }
    $(function () {
        $(".release_news_ok_btn").click(function () {
            var text = CKEDITOR.instances.container.getData();
            $("#container").val(text);
            var title = $('#title').val();
            var mainImg = $('#mainImg').val();
            var chnlCode = $('#type').val();
            var detail = $('#container').val();

            if(title=='' || chnlCode=='' || detail==''){
                alert("新闻标题、栏目、内容不能为空");
                return;
            }
            $('#ajaxForm').ajaxSubmit({
                type: "post",  //提交方式
                dataType: "json", //数据类型
                url: _CTX + "/compusnews/news/create.json",//默认是form action
                success: function (data) {
                    alert("添加成功");
                    $(window.parent.document).find("#MainFrame").attr("src", _CTX + "/compusnews/news/list");   // window可省略不写
                }
            });
        });
    });
</script>
</body>
</html>