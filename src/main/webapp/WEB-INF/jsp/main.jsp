<%--
  ~ Copyright (c) 2019.
  ~   ------------------------------------------------------------------------------
  ~   @Project       : RPA Web Project
  ~   @Source        : main.jsp
  ~   @Description   :
  ~   @Author        : GACHINOEL
  ~   @Version       : v1.0
  ~   Copyright(c) 2019 WOOJIN All rights reserved
  ~   ------------------------------------------------------------------------------
  ~                    변         경         사         항
  ~   ------------------------------------------------------------------------------
  ~      DATE           AUTHOR                       DESCRIPTION
  ~   ---------------  ----------    ------------------------------------------------
  ~   2019.11.1       gachinoel     신규생성
  ~   ------------------------------------------------------------------------------
  --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/include/include-header.jsp" %>
</head>
<body>
    <%@ include file="/WEB-INF/include/header.jsp" %>
    <%@ include file="/WEB-INF/include/lefter.jsp" %>
        <!--본문영역-->
        <div id="content" style="width:800px">${userRole}
            <p>Content startContent startContent startContent startContent startContent startContent startContent startContent startContent startContent startContent startContent startContent startContent startContent startContent startContent startContent start</p>
            <p>Content</p>
            <p>Content</p>
            <p>Content</p>
            <p>Content</p>
            <p>Content</p>
            <p>Content</p>
            <p>Content</p>
            <p>Content</p>
            <p>Content</p>
            <p>Content</p>
            <p>Content</p>
            <p>Content</p>
            <p>Content</p>
            <p>Content</p>
            <p>Content</p>
            <p>Content</p>
            <p>Content</p>
            <p>Content</p>
            <p>Content</p>
            <p>Content</p>
            <p>Content</p>
            <p>Content</p>
            <p>Content</p>
            <p>Content</p>
            <p>Content</p>
            <p>Content end</p>
        </div>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#sales").attr('class','current');
                $("#menu").attr('class','active');
            });
        </script>
        <!--본문영역-->
    <%@ include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>
