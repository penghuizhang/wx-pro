<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx"
       value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <script>
        var _CTX = '${ctx}';
    </script>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
    <title>美中投资集团</title>
    <link rel="stylesheet" href="${ctx}/static/back/css/main.css">
    <script src="${ctx}/static/back/lib/common/jquery-1.11.1.min.js" type="text/javascript" ></script>
    <script src="${ctx}/static/back/js/main.js?v=1" type="text/javascript" ></script>
</head>
