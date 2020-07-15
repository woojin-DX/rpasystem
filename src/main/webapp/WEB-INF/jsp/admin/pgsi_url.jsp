<%--
  Created by IntelliJ IDEA.
  User: jhkim
  Date: 2019-11-07
  Time: 오후 1:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.woojin.commercial.util.PageNavigater"%>
<%
    String localPageTitle = " > P-GSI관리";
    pageContext.setAttribute("localPageTitle", localPageTitle) ;
%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/include/include-header.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/include/header.jsp" %>
<!--본문영역-->
<div id="contentmain">
    <div id="wrap">
        <!-- container -->
        <div id="container">
            <div id="content">
                <div id="content_title">
                    <h3>G-PSI 관리 </h3>
                </div>
                <div id="content_start">
                	<div class="bbsB ta_l mt_10">
                        <ul class="btn_all">
                            <li id="btnPSIX0"><span class="button medium" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/pgsi/listPsix0Data" id="PSIX0">PSIX0 파일 생성하기&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></span><br /><br /></li>
                            <li id="btnPSIX1"><span class="button medium" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/pgsi/listPsix1Data" id="PSIX1">PSIX1 파일 생성하기&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></span><br /><br /></li>
                            <li id="btnPSIX2"><span class="button medium" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/pgsi/listPsix2Data" id="PSIX2">PSIX2 파일 생성하기&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></span><br /><br /></li>
                            <li id="btnPSIX3"><span class="button medium" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/pgsi/listPsix3Data" id="PSIX3">PSIX3 파일 생성하기&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></span><br /><br /></li>
                            <li id="btnPSIX4"><span class="button medium" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/pgsi/listPsix4Data" id="PSIX4">PSIX4 파일 생성하기&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></span><br /><br /></li>
                            <li id="btnPSIX5"><span class="button medium" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/pgsi/listPsix5Data" id="PSIX5">PSIX5 파일 생성하기&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></span><br /><br /></li>
                            <li id="btnPSIX6"><span class="button medium" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/pgsi/listPsix6Data" id="PSIX6">PSIX6 파일 생성하기&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></span><br /><br /></li>
                            <li id="btnPSIX7"><span class="button medium" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/pgsi/listPsix7Data" id="PSIX7">PSIX7 파일 생성하기&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></span><br /><br /></li>
                            <li id="btnPSIX9"><span class="button medium" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/pgsi/listPsix9Data" id="PSIX9">PSIX9 파일 생성하기&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></span><br /><br /></li>
                        </ul>
                    </div>
                </div>
                <!-- //content_start -->
            </div>
            <!-- //content -->
        </div>
        <!-- //container -->

    </div>
    <!-- //wrap -->
</div>
<!--본문영역-->
<%@ include file="/WEB-INF/include/footer.jsp" %>
<script type="text/javascript">
    $(document).ready(function() {
        $(document).prop('title', '${pageTitle} ${localPageTitle}');

        $("#pgsi").attr('class','current');
        
    });


</script>
</body>
</html>