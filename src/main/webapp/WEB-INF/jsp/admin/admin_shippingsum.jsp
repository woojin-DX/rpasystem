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
    String localPageTitle = " > 자재코드합산정보";
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
                    <h3>자재코드 합산정보 </h3>
                </div>
                <div id="content_start">
                    <div class="listleft" id="listleft">
                        <!-- 내용 -->

                        <div class="bbsL_box">
                            <!-- 검색 / 상단 -->
                            <div class="bbsS_TOP">
                                <form id="searchForm" name="searchForm" method="post">
                                    <input type="hidden" id="pagemode" name="pagemode" value="${pageParam.pagemode}" />
                                    <fieldset>
                                        <legend>검색</legend>
                                        <label for="material_list" class="blind1">품번</label>
                                        <select name="material_list" id="material_list" style="width:153px;">
                                            <option value="">품번선택</option>
                                            <c:forEach var="materialList" items="${materialList}" varStatus="status">
                                                <option value="${materialList.material_num}">${materialList.material_num}</option>
                                            </c:forEach>
                                        </select>
                                        <label for="schword" class="blind">검색어 입력</label>
                                        <input type="text" name="schword" id="schword" title="검색어를 입력해주세요" class="input_sch" style="width:150px" placeholder="검색어를 입력해주세요" value="" />
                                        <input type="image" src="/images/board/btn_schok.gif"  id="searchBtn" alt="검색" title="검색" class="btn_schok" />

                                    </fieldset>
                                </form>
                            </div>
                            <!-- //검색 / 상단 -->

                            <!-- 목록 -->
                            <div id="datalist">
                                <table cellspacing="0" border="1" summary="목록정보" max-width="1300px" cellpadding="0" id="tbllist" class="bbsL">
                                    <caption>게시판 목록</caption>
                                    <colgroup>
                                        <col width="200px" />
                                        <col width="100px" />
                                        <col width="100px" />
                                        <col width="100px" />
                                        <col width="120px" />
                                        <col width="100px" />
                                        <col width="100px" />
                                    </colgroup>
                                    <thead>
                                    <tr>
                                        <th scope="col">자재코드</th>
                                        <th scope="col">자재유형</th>
                                        <th scope="col" class="ta_r">발주수량</th>
                                        <th scope="col" class="ta_r">가용재고</th>
                                        <th scope="col" class="ta_r">발주수량검증</th>
                                        <th scope="col" class="ta_r">MTM가용재고</th>
                                        <th scope="col" class="ta_r">생산오더수량</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="result" items="${shippingList}" varStatus="status">
                                    <tr class="row" >
                                        <td class="col ta"><c:out value="${result.material_num}"  default=""/></td>
                                        <td class="col"><c:out value="${result.mtart}"  default=""/></td>
                                        <td class="col ta_r"><fmt:formatNumber value="${result.sum_qty}" pattern="#,###" /></td>
                                        <c:choose>
                                            <c:when test = "${result.inven_use_qty == ''}">
                                                <td class="col inuse ta_r"><c:out value="${result.inven_use_qty}"  default=""/></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td class="col inuse ta_r"><fmt:formatNumber value="${result.inven_use_qty}" pattern="#,###" /></td>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test = "${result.verification_qty == ''}">
                                                <td class="col inuse ta_r"><c:out value="${result.verification_qty}"  default=""/></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td class="col inuse ta_r"><fmt:formatNumber value="${result.verification_qty}" pattern="#,###" /></td>
                                            </c:otherwise>
                                        </c:choose>

                                        <c:choose>
                                            <c:when test = "${result.mtm_use_qty == ''}">
                                                <td class="col inmtm ta_r"><c:out value="${result.mtm_use_qty}"  default=""/></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td class="col inmtm ta_r"><fmt:formatNumber value="${result.mtm_use_qty}" pattern="#,###" /></td>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test = "${result.prod_order_qty == ''}">
                                                <td class="col ta_r"><c:out value="${result.prod_order_qty}"  default=""/></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td class="col ta_r"><fmt:formatNumber value="${result.prod_order_qty}" pattern="#,###" /></td>
                                            </c:otherwise>
                                        </c:choose>
                                    </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>
                                <!-- //목록 -->
                                <div class="bbsB ta_r mt_10">
		                            <ul class="btn_all">
		                                <li id="btnShppingExcel"><span class="button medium"><a href="javascript:void(0)" id="shippingExcel">목록엑셀다운</a></span>&nbsp;&nbsp;</li>
		                            </ul>
		                        </div>
                            </div>
                        </div>
                        <!-- //내용 -->
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
        $("#sumlist").attr('class', 'current');

        $("#material_list").select2();

        $('#searchBtn').on("click", function(e){
            $("#pagemode").val("search");
            var comSubmit = new ComSubmit("searchForm");
            comSubmit.setUrl("/admin/sumlist");
            comSubmit.submit();
        });
        
        $('#shippingExcel').on("click", function(e){
            var comSubmit = new ComSubmit("searchForm");
            comSubmit.setUrl("/admin/sumlistexcel");
            comSubmit.submit();
        });


    });
</script>
</body>
</html>