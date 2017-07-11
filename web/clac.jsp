<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>JAVA 世界</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script src="/static/back/lib/common/jquery-1.11.1.min.js"></script>
</head>
<style type="text/css">
    div {
        width: 285px;
        height: 350px;
        border: 2px solid black;
    }

    .operator {
        width: 35px;
        height: 25px;
    }

</style>
<body>
<div align="center">
    <h1>web计算器</h1>
    <label>请输入第一个参数：</label><br/>
    <input type="text" id="firstValue" style="height:30px; width:150px; color: red;"/><br/>
    <label>请输入第二个参数：</label><br/>
    <input type="text" id="secondValue" style="height:30px; width:150px; color: red;"/> <br/>
    <label>请选择运算方式：</label><br/>
    <table>
        <tr>
            <td><input type="button" value="+" class="operator" id="add" onclick="operate('add');"/></td>
            <td><input type="button" value="-" class="operator" id="subtract" onclick="operate('subtract');"/></td>
            <td><input type="button" value="*" class="operator" id="multiply" onclick="operate('multiply');"/></td>
            <td><input type="button" value="/" class="operator" id="divide" onclick="operate('divide');"/></td>
        </tr>
    </table>
    <label>计算结果为：</label><br/>
    <input type="text" id="result" style="height:30px; width:150px; color: red;"/> <br/>
</div>
<script>
    function operate(operation) {
        var firstValue = $('#firstValue').val();
        var secondValue = $('#secondValue').val();
        $.get("./calc", {firstValue: firstValue, secondValue: secondValue, operator: operation},
            function (data) {
                $('#result').val(data);
            }
        );
    }
</script>
</body>
</html>
