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
    request.setCharacterEncoding("utf-8");

    String localPageTitle = " > 거래명세표출력";
    pageContext.setAttribute("localPageTitle", localPageTitle) ;
%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/include/include-header.jsp" %>
    <style>
        /*datepicer 버튼 롤오버 시 손가락 모양 표시*/
        .ui-datepicker-trigger{cursor: pointer;}
        /*datepicer input 롤오버 시 손가락 모양 표시*/
        .hasDatepicker{cursor: pointer;}
        .ui-datepicker-calendar { display:none; }
    </style>
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
                    <h3>출하정보 </h3>
                </div>
                <div id="content_start">
                    <!-- 내용 -->
                    <div class="listleft" style="width:900px;">
                        <div class="bbsL_box">
                            <!-- 검색 / 상단 -->
                            <div class="bbsS_TOP">
                                <form id="searchForm" name="searchForm" method="post">
                                    <input type="hidden" id="pagemode" name="pagemode" value="${pageParam.pagemode}" />
                                    <input type="hidden" id="nCurrpage" name="nCurrpage" value="${pageParam.nCurrpage}" />
                                    <input type="hidden" id="company_cd" name="company_cd" value="" />
                                    <input type="hidden" id="company_nm" name="company_nm" value="" />
                                    <input type="hidden" id="supply_dt" name="supply_dt" value="" />
                                    <input type="hidden" id="confirm_dt" name="confirm_dt" value="" />
                                    <input type="hidden" id="place_key" name="place_key" value="" />
                                    <input type="hidden" id="place_nm" name="place_nm" value="" />
                                    <input type="hidden" id="supply_qty" name="supply_qty" value="" />
                                    <fieldset>
                                        <legend>검색</legend>
                                        <label for="supply_month" class="blind1">출하기간</label>
                                        <input size="10" maxlength="8" type="text" id="supply_month" name="supply_month"  value='${pageParam.supply_month}' >
                                        <input type="image" src="/images/board/btn_schok.gif"  id="searchBtn" alt="검색" title="검색" class="btn_schok" />

                                    </fieldset>
                                </form>
                            </div>
                            <!-- //검색 / 상단 -->

                            <!-- 목록 -->
                            <table cellspacing="0" border="1" summary="목록정보" max-width="800px" cellpadding="0" id="tbllist" class="bbsL">
                                <caption>게시판 목록</caption>
                                <colgroup>
                                    <col width="80px" />
                                    <col width="400px" />
                                    <col width="150px" />
                                    <col width="90px" />
                                    <col width="120px" />
                                    <col width="0" />
                                    <col width="0" />
                                </colgroup>
                                <thead>
                                <tr>
                                    <th scope="col">출하일자</th>
                                    <th scope="col" class="ta_l">업체명</th>
                                    <th scope="col" class="ta_l">출하처</th>
                                    <th scope="col" class="ta_r">총수량</th>
                                    <th scope="col">거래명세서 다운로드</th>
                                    <th scope="col" style="display:none">업체코드</th>
                                    <th scope="col" style="display:none">남품처키</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="result" items="${supplyList}" varStatus="status">
                                    <tr >
                                        <td class="supply_dt ta_c"><c:out value="${result.supply_dt}"  default=""/></td>
                                        <td class="company_nm ta_l"><c:out value="${result.company_nm}"  default=""/></td>
                                        <td class="place_nm ta_l"><c:out value="${result.place_nm}"  default=""/></td>
                                        <td class="supply_qty ta_r"><fmt:formatNumber value="${result.supply_qty}" pattern="#,###" /></td>
                                        <td class="ta_r"><span class="button medium"><a href="javascript:void(0)" name="shippingConfirm"  id="shippingConfirm${status.count}">거래명세서 다운로드</a></span></td>
                                        <td scope="col" style="display:none" class="company_cd"><c:out value="${result.company_cd}"  default=""/></td>
                                        <td scope="col" style="display:none" class="place_key"><c:out value="${result.place_key}"  default=""/></td>
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
        $("#supplyresheet").attr('class', 'current');

        $.datepicker.regional['ko'] = {
            closeText: '닫기',
            prevText: '이전달',
            nextText: '다음달',
            currentText: '오늘',
            monthNames: ['1월(JAN)','2월(FEB)','3월(MAR)','4월(APR)','5월(MAY)','6월(JUN)',
                '7월(JUL)','8월(AUG)','9월(SEP)','10월(OCT)','11월(NOV)','12월(DEC)'],
            monthNamesShort: ['1월','2월','3월','4월','5월','6월',
                '7월','8월','9월','10월','11월','12월'],
            dayNames: ['일','월','화','수','목','금','토'],
            dayNamesShort: ['일','월','화','수','목','금','토'],
            dayNamesMin: ['일','월','화','수','목','금','토'],
            weekHeader: '주',
            dateFormat: 'yy.mm.dd',
            firstDay: 0,
            isRTL: false,
            showMonthAfterYear: true,
            yearSuffix: '',
            showOn: 'both',
            buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif", //버튼 이미지 경로
            buttonImageOnly: true, //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
            buttonText: "선택", //버튼에 마우스 갖다 댔을 때 표시되는 텍스트
            changeMonth: true,
            changeYear: true,
            showButtonPanel: true,
            yearRange: 'c-99:c+99'
        };
        $.datepicker.setDefaults($.datepicker.regional['ko']);

        var datepicker_default = {
            showOn: 'both',
            buttonText: "달력",
            currentText: "이번달",
            changeMonth: true,
            changeYear: true,
            showButtonPanel: true,
            yearRange: 'c-99:c+99',
            showOtherMonths: true,
            selectOtherMonths: true
        }

        datepicker_default.closeText = "선택";
        datepicker_default.dateFormat = "yy.mm";
        datepicker_default.onClose = function (dateText, inst) {
            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
            $(this).datepicker( "option", "defaultDate", new Date(year, month, 1) );
            $(this).datepicker('setDate', new Date(year, month, 1));
        }

        datepicker_default.beforeShow = function () {
            var selectDate = $(this).val().split(".");
            var year = Number($(this).val().substring(0,4));
            var month = Number($(this).val().substring(5,7)) - 1;
            $(this).datepicker( "option", "defaultDate", new Date(year, month, 1) );
        }

        $("#supply_month").datepicker(datepicker_default);

        $("#material_list").select2();

        $('#searchBtn').on("click", function(e){
            $("#pagemode").val("search");
            var comSubmit = new ComSubmit("searchForm");
            comSubmit.setUrl("/supply/resheet");
            comSubmit.submit();
        });

        $("a[name=shippingConfirm]").on("click", function(e){
            var regExp = /[\{\}\[\]\/?.,;:₩|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
            var tr = $(this).closest('tr');
            var td = tr.children();

            var supply_dt = td.eq(tr.find(".supply_dt").index()).text();
            var company_cd = td.eq(tr.find(".company_cd").index()).text();
            var place_key = td.eq(tr.find(".place_key").index()).text();
            var company_nm = td.eq(tr.find(".company_nm").index()).text();
            var supply_dt = td.eq(tr.find(".supply_dt").index()).text();
            var place_nm = td.eq(tr.find(".place_nm").index()).text();
            var supply_qty = td.eq(tr.find(".supply_qty").index()).text().replace(regExp,"");

            $("#supply_dt").val(supply_dt);
            $("#company_cd").val(company_cd);
            $("#place_key").val(place_key);
            $("#company_nm").val(company_nm);
            $("#supply_dt").val(supply_dt);
            $("#confirm_dt").val(supply_dt);
            $("#place_nm").val(place_nm);
            $("#supply_qty").val(supply_qty);
            var comSubmit = new ComSubmit("searchForm");
            comSubmit.setUrl("/supply/resheetExcel");
            comSubmit.submit();
        });


    });

</script>
</body>
</html>