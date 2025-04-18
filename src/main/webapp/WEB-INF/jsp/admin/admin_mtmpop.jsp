<%@ page pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMddHHmmss");
    String today = formatter1.format(new Date());

    String pageTitle = "시판사이트";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MTM등록</title>
    <meta charset="UTF-8" />
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
    <script src="/jscript/common.js" charset="utf-8"></script>
    <script src="/jscript/CommonUtil.js" charset="utf-8"></script>
    <!-- jQuery UI CSS파일-->
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" type="text/css" />

    <link rel="stylesheet" type="text/css" href="/css/style.css" />
    <link rel="stylesheet" type="text/css" href="/css/table.css" />
</head>
<body>
<div id="contentmain">
    <div id="wrappop">
        <!-- container -->
        <div id="container">
            <div id="contentpop">
                <h2><span class="blind">본문 영역</span></h2>
                <div id="content_title">
                    <h3>출하정보 - MTM목록 </h3>
                </div>
                <div id="content_pop">
                    <div class="bbsL_box">
                        <!-- 검색 / 상단 -->
                        <!--div class="bbsS_TOP">
                            <form id="searchForm" name="searchForm">
                                <input type="hidden" id="pagemode" name="pagemode" value="${pageParam.pagemode}" />
                                <fieldset>
                                    <legend>검색</legend>
                                    <label for="order_dt_start" class="blind1">기간</label>
                                    <input size="10" maxlength="8" type="text" id="order_dt_start" name="order_dt_start"  value='' > ~
                                    <input size="10" maxlength="8" type="text" id="order_dt_end" name="order_dt_end"  value='' >
                                    <label for="keyword" class="blind">검색어 입력</label>
                                    <input type="text" name="keyword" id="keyword" title="검색어를 입력해주세요" class="input_sch" style="width:150px" placeholder="검색어를 입력해주세요" value="" />
                                    <input type="image" src="/images/board/btn_schok.gif"  id="searchBtn" alt="검색" title="검색" class="btn_schok" />

                                </fieldset>
                            </form>
                        </div-->
                        <!-- //검색 / 상단 -->

                        <!-- 목록 -->
                        <table cellspacing="0" border="1" summary="목록정보" cellpadding="0" class="bbsL">
                            <caption>게시판 목록</caption>
                            <colgroup>
                                <col width="200px" />
                                <col width="150px" />
                                <col width="150px" />
                                <col width="120px" />
                                <col width="110px" />
                                <col width="120px" />
                            </colgroup>
                            <thead>

                            <tr>
                                <th scope="col" class="ta_c">업체명</th>
                                <th scope="col" class="ta_c">변경 전 자재코드</th>
                                <th scope="col" class="ta_c">변경 전 저장위치</th>
                                <th scope="col" class="ta_c">MTM수량</th>
                                <th scope="col" class="ta_c">변경 후 자재코드</th>
                                <th scope="col" class="ta_c">변경 후 저장위치</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="result" items="${shippingMtmList}" varStatus="status">
                                <tr>
                                    <td class="ta_c"><c:out value="${result.company_nm}"  default=""/></td>
                                    <td class="ta_c"><c:out value="${result.material_num}"  default=""/></td>
                                    <td class="ta_c"><c:out value="${result.pre_storage_loc}"  default=""/></td>
                                    <td class="ta_c"><fmt:formatNumber value="${result.modi_qty}" pattern="#,###" /></td>
                                    <td class="ta_c"><c:out value="${result.modi_meterial_num}"  default=""/></td>
                                    <td class="ta_c"><c:out value="${result.storage_loc}"  default=""/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <!-- //목록 -->
                    </div>
                    <!-- //내용 -->
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

</body>
</html>