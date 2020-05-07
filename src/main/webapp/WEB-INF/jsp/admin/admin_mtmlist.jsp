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
    String localPageTitle = " > MTM등록 정보";
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
                    <h3>MTM등록 정보 </h3>
                </div>
                <div id="content_start">
                    <div class="listleft">
                        <!-- 내용 -->

                        <div class="bbsL_box">
                            <!-- 검색 / 상단 -->
                            <div class="bbsS_TOP">
                                <form id="searchForm" name="searchForm">
                                    <input type="hidden" id="pagemode" name="pagemode" value="${pageParam.pagemode}" />
                                    <input type="hidden" id="nCurrpage" name="nCurrpage" value="${pageParam.nCurrpage}" />
                                    <fieldset>
                                        <legend>검색</legend>
                                        <label for="order_dt_start" class="blind1">출하기간</label>
                                        <input size="10" maxlength="8" type="text" id="supply_dt_start" name="supply_dt_start"  value='${pageParam.supply_dt_start}' > ~
                                        <input size="10" maxlength="8" type="text" id="supply_dt_end" name="supply_dt_end"  value='${pageParam.supply_dt_end}' >
                                        <label for="schword" class="blind">검색어 입력</label>
                                        <input type="text" name="schword" id="schword" title="검색어를 입력해주세요" class="input_sch" style="width:150px" placeholder="검색어를 입력해주세요" value="${pageParam.schword}" />
                                        <input type="image" src="/images/board/btn_schok.gif"  id="searchBtn" alt="검색" title="검색" class="btn_schok" />

                                    </fieldset>
                                </form>
                            </div>
                            <!-- //검색 / 상단 -->

                            <!-- 목록 -->
                            <table cellspacing="0" border="1" summary="목록정보" cellpadding="0" class="bbsL">
                                <caption>게시판 목록</caption>
                                <colgroup>
                                    <col width="80px" />
                                    <col width="100px" />
                                    <col width="200px" />
                                    <col width="100px" />
                                    <col width="150px" />
                                    <col width="110px" />
                                    <col width="150px" />
                                    <col width="110px" />
                                    <col width="100px" />
                                </colgroup>
                                <thead>
                                <tr>
                                    <th scope="col" class="ta_c">상태</th>
                                    <th scope="col" class="ta_c">업체코드</th>
                                    <th scope="col" class="ta_c">업체명</th>
                                    <th scope="col" class="ta_c">출하일</th>
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
                                        <td class="ta_c"><c:out value="${result.process_nm}"  default=""/></td>
                                        <td class="ta_c"><c:out value="${result.company_cd}"  default=""/></td>
                                        <td class="ta_c"><c:out value="${result.company_nm}"  default=""/></td>
                                        <td class="ta_c"><c:out value="${result.supply_dt}"  default=""/></td>
                                        <td class="ta_c"><c:out value="${result.modi_meterial_num}"  default=""/></td>
                                        <td class="ta_c"><c:out value="${result.pre_storage_loc}"  default=""/></td>
                                        <td class="ta_c"><fmt:formatNumber value="${result.modi_qty}" pattern="#,###" /></td>
                                        <td class="ta_c"><c:out value="${result.material_num}"  default=""/></td>
                                        <td class="ta_c"><c:out value="${result.storage_loc}"  default=""/></td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                            <!-- //목록 -->

                            <!-- 페이징 -->
                            <div  id="PAGE_NAVI" class="col_paging">${pageNavigater}</div>
                            <!-- // 페이징 -->
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
        $(document).prop('title', '<%=pageTitle + localPageTitle%>');
        $("#mtmlist_admin").attr('class', 'current');

        $("#order_dt_start").datepicker({
            dateFormat: 'yy.mm.dd' //Input Display Format 변경
            ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
            ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
            ,changeYear: true //콤보박스에서 년 선택 가능
            ,changeMonth: true //콤보박스에서 월 선택 가능
            ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시
            ,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
            ,buttonImageOnly: true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
            ,buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트
            ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
            ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
            ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
            ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
            ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
            //,minDate: "-1M" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
            ,maxDate: "+1M" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
        });
        $("#order_dt_end").datepicker({
            dateFormat: 'yy-mm-dd' //Input Display Format 변경
            ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
            ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
            ,changeYear: true //콤보박스에서 년 선택 가능
            ,changeMonth: true //콤보박스에서 월 선택 가능
            ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시
            ,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
            ,buttonImageOnly: true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
            ,buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트
            ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
            ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
            ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
            ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
            ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
            //,minDate: "-1M" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
            ,maxDate: "+1M" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
        });

        $('#searchBtn').on("click", function(e){
            $("#pagemode").val("search");
            var comSubmit = new ComSubmit("searchForm");
            comSubmit.setUrl("/admin/mtmlist");
            comSubmit.submit();
        });

        $("a[name = 'pageMove']").unbind("click").click(function(e) {
            var comSubmit = new ComSubmit("searchForm");

            comSubmit.setUrl("/admin/mtmlist");
            $("#nCurrpage").val($(this).attr("content_id"))
            comSubmit.submit();

        });

    });

</script>
</body>
</html>