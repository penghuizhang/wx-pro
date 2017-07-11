var mainFrame = null;


$(function () {

    mainFrame = $(".AccountManagement_c_iframe iframe");

    // 适配
    // iframe
    $(".AccountManagement_c_iframe").css("height", $(document).height());
    // 导航高度
    $(".nav_side").css("height", $(document).height() - 100);

    // iframe  链接
    $(".AccountManagement").click(function () {
<<<<<<< .mine
        mainFrame.attr("src", _CTX + "/sys/sysUserSeachServlet")
||||||| .r11
        mainFrame.attr("src", _CTX + "/compusnews/sysUser/list")
=======
        mainFrame.attr("src", _CTX + "/sysUser/svlt?opt=index");
>>>>>>> .r14
    })
    $(".ColumnManagement").click(function () {
        mainFrame.attr("src", _CTX + "/compusnews/prod/list")
    })
    $(".NewsManagement").click(function () {
        mainFrame.attr("src", _CTX + "/newsList.jsp")
    })
    $(".RsvpManagement").click(function () {
        mainFrame.attr("src", _CTX + "/compusnews/rsvp/list")
    })
    $(".ContactManagement").click(function () {
        mainFrame.attr("src", _CTX + "/compusnews/contact/list")
    })
    $(".ApplicationManagement").click(function () {
        mainFrame.attr("src", _CTX + "/compusnews/docsign/list")
    })
    $(".CustManagement").click(function () {
        mainFrame.attr("src", _CTX + "/compusnews/cust/list")
    })


    // pull page   翻页
    $(".pull_page ul li").click(function () {
        var index = $(this).index();
        if ($(this).hasClass("pull_page_df_btn")) {
            return;
        }
        $(this).addClass("on").siblings().removeClass("on");

    })

    // nav
    $(".nav_side>div").click(function () {
        var index = $(this).index();
        $(this).addClass("active").siblings().removeClass("active");
        $(".main_fx>div").eq(index).show().siblings().hide();
        if (index == 0) {
            $(".user_location span").text("账户管理");
        } else if (index == 1) {
            $(".user_location span").text("产品管理");
        } else if (index == 2) {
            $(".user_location span").text("新闻管理");
        } else if (index == 3) {
            $(".user_location span").text("活动申请");
        } else if (index == 4) {
            $(".user_location span").text("客户预约");
        } else if (index == 5) {
            $(".user_location span").text("合同申请");
        } else if (index == 6) {
            $(".user_location span").text("客户管理");
        }
    })

    // 弹窗   all
    // z账户管理 -
    // 添加账户
    $("#add_Account_btn").click(function () {
        $("#add_Account").fadeIn(100);
    })
    $(".add_Account_close").click(function () {
        $("#add_Account").fadeOut(100);
        $("#user_column").fadeOut(100);
        $("#delete_Account").fadeOut(100);
        $("#ac_Account").fadeOut(100);
        $("#add_column").fadeOut(100);
        $("#edit_column").fadeOut(100);
        $("#delete_column").fadeOut(100);
        $("#add_main_win").fadeOut(100);

    })
    // 编辑账户
    $("#edit_Account_btn").click(function () {
        $("#user_column").fadeIn(100);
    })
    //删除账户
    $("#delete_Account_btn").click(function () {
        $("#delete_Account").fadeIn(100);
    })


    // 栏目管理 -
    $("#add_column_btn").click(function () {
        $("#add_column").fadeIn(100);
    })

    // 确定 btn
    $(window).resize(function () {

    });

});

window.onload = function () {
    var winH = $('html,body').height();
    $(".AccountManagement_c_iframe").css('height', (winH - 245) + 'px');
    $(".nav_side").css('height', (winH - 245) + 'px');
}

function passwordLevel(password) {
    var Modes = 0;
    var i;
    for (i = 0; i < password.length; i++) {
        Modes |= CharMode(password.charCodeAt(i));
    }
    return bitTotal(Modes);

    //CharMode函数
    function CharMode(iN) {
        if (iN >= 48 && iN <= 57)//数字
            return 1;
        if (iN >= 65 && iN <= 90) //大写字母
            return 2;
        if ((iN >= 97 && iN <= 122) || (iN >= 65 && iN <= 90)) //大小写
            return 4;
        else
            return 8; //特殊字符
    }

    //bitTotal函数
    function bitTotal(num) {
        modes = 0;
        for (i = 0; i < 4; i++) {
            if (num & 1) modes++;
            num >>>= 1;
        }
        return modes;
    }
}