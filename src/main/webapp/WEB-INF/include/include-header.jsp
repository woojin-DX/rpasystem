<%@ page pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMddHHmmss");
    String today = formatter1.format(new Date());
    pageContext.setAttribute("today", today) ;
    String pageTitle = "시판사이트";
    pageContext.setAttribute("pageTitle", pageTitle) ;
%>
    <title> ${pageTitle} </title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="http://malsup.github.com/jquery.form.js"></script>
    <script type="text/javascript" src="https://rawgit.com/select2/select2/master/dist/js/select2.js"></script>
    <script src="<c:url value='/jscript/common.js?${today}' />" charset="utf-8"></script>
    <script src="<c:url value='/jscript/CommonUtil.js?${today}' />" charset="utf-8"></script>
    <script src="<c:url value='/jscript/jquery.number.js?${today}' />" charset="utf-8"></script>

    <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" type="text/css" />

    <link rel="stylesheet" type="text/css" href="/css/style.css?${today}" />
    <link rel="stylesheet" type="text/css" href="/css/table.css?${today}" />
    <link rel="stylesheet" type="text/css" href="/css/select2.min.css?${today}" />
