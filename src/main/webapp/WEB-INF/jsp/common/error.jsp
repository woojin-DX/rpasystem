<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isErrorPage = "true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>error</title>
    </head>
    <body>
    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td width="100%" height="100%" align="center" valign="middle" style="padding-top: 150px;">
            	page message : <c:out value="${pageMessage }" /><br />
                code : <c:out value="${error.STATUS_CODE }" /><br />
                exception type : <c:out value="${error.EXCEPTION_TYPE }" /><br />
                message : <c:out value="${error.MESSAGE }" /><br />
                exception : <c:out value="${error.EXCEPTION }" /><br />
                request uri : <c:out value="${error.REQUEST_URI }" /><br />
                servlet name : <c:out value="${error.SERVLET_NAME }" /><br />
            </td>
        </tr>
    </table>
    </body>
</html>