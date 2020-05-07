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
    String localPageTitle = " > 출하정보";
    pageContext.setAttribute("localPageTitle", localPageTitle) ;
%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/include/include-header.jsp" %>
    <script>
        var resultFlag = '${resultFlag}';
        if (resultFlag.length > 1){
            var resultMsg = '${resultMsg}';
            alert(resultMsg);
        }

    </script>
    <style>
        input[type="radio"] {
            display:none;
        }

        input[type="radio"] + label {
            color:#f2f2f2;
            font-family:Arial, sans-serif;
        }

        input[type="radio"] + label span {
            display:inline-block;
            width:19px;
            height:19px;
            margin:-2px 10px 0 0;
            vertical-align:middle;
            background:url(/images/check_radio_sheet.png) -38px top no-repeat;
            cursor:pointer;
        }

        input[type="radio"]:checked + label span {
            background:url(/images/check_radio_sheet.png) -57px top no-repeat;
        }

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
                    <c:choose>
                        <c:when test = "${pagecng == 'PRELIST'}">
                            <h3>출하정보 (출하전목록) </h3>
                        </c:when>
                        <c:when test = "${pagecng == 'BOSLIST'}">
                            <h3>출하정보 (대기목록) </h3>
                        </c:when>
                        <c:otherwise>
                            <h3>출하정보 </h3>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div id="content_start">
                    <div class="formleft">
                        <!-- 내용 -->
                        <div class="content_subtitle">
                             <h3> &nbsp;&nbsp;▶ 출하등록 </h3>
                        </div>
                        <!-- 글쓰기 -->
                        <fieldset>
                            <form id="registerForm" name="registerForm" enctype="multipart/form-data">
                                <input type="hidden" id="shipping_key" name="shipping_key" value="" />
                                <input type="hidden" id="orderfor_key" name="orderfor_key" value="" />
                                <input type="hidden" id="user_id" name="user_id" value="${infoParam.user_id}" />
                                <input type="hidden" id="process_cd" name="process_cd" value="" />
                                <input type="hidden" id="nextprocess_cd" name="nextprocess_cd" value="" />
                                <input type="hidden" id="unit_price" name="unit_price" value="" />
                                <input type="hidden" id="material_num" name="material_num" value="" />
                                <input type="hidden" id="remainallqty" name="remainallqty" value="" />
                                <input type="hidden" id="remaininvenqty" name="remaininvenqty" value="" />
                                <input type="hidden" id="remainmtmqty" name="remainmtmqty" value="" />
                                <input type="hidden" id="modi_mtm_item" name="modi_mtm_item" value="" />
                                <input type="hidden" id="modi_mtm_loc" name="modi_mtm_loc" value="" />
                                <input type="hidden" id="modi_mtm_ploc" name="modi_mtm_ploc" value="" />
                                <input type="hidden" id="modi_mtm_qty" name="modi_mtm_qty" value="" />
                                <input type="hidden" id="processFlag" name="processFlag" value="insert" />
                                <div class="bbsL_WRITE_box" id="bbsL_WRITE_box">
                                    <table class="bbsL_WRITE">
                                        <!-- 헤드부 -->
                                        <thead>
                                        <caption> 출하 상세내역 </caption>
                                        <colgroup>
                                            <col width="110" />
                                            <col width="200" />
                                        </colgroup>
                                        </thead>
                                        <!-- 바디부 -->
                                        <tbody>
                                        <tr>
                                            <th scope="row">상태</th>
                                            <td class="ta_c"><label id="process_nm_str"></label></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">업체코드</th>
                                            <td class="ta_c"><label id="company_cd_str"></label></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">발주업체명</th>
                                            <td class="ta_c"><label id="company_nm_str"></label></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">접수일자</th>
                                            <td class="ta_c"><label id="accept_dt_str"></label></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">자재코드</th>
                                            <td class="ta_c"><label id="material_num_str"></label></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">자재유형</th>
                                            <td class="ta_c"><label id="mtart_nm_str"></label></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">포장단위</th>
                                            <td class="ta_r"><label id="bstrf_str"></label></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">납품요청수량</th>
                                            <td class="ta_r"><label id="supply_req_qty_str"></label></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">납품요청일</th>
                                            <td class="ta_c"><label id="supply_req_dt_str"></label></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">납품처</th>
                                            <td class="ta_c"><label id="supply_place_str"></label></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">가용재고</th>
                                            <td class="ta_r"><label id="inven_use_qty_str"></label></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">MTM가용재고</th>
                                            <td class="ta_r"><label id="mtm_qty_str"></label></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">생산오더수량</th>
                                            <td class="ta_r"><label id="prod_order_qty_str"></label></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">납품확정수량</th>
                                            <td><input type="text" id="confirm_qty" name="confirm_qty" size="20" class="input_s1 ta_r"  value="0"/></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">출하예정일</th>
                                            <td><input maxlength="10" type="text" id="shipping_dt" name="shipping_dt" size="20" class="input_s1 ta_c" value='' ></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">출하일</th>
                                            <td><input maxlength="10" type="text" id="supply_dt" name="supply_dt" size="20" class="input_s1 ta_c" value='' ></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">출하수량</th>
                                            <td><input maxlength="10" type="text" id="supply_qty" name="supply_qty" size="20" class="input_s1 ta_r" value='0' ></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">포장방법</th>
                                            <td>
                                                <select name="packing_method" id="packing_method" class="select_r1">
                                                    <c:forEach var="packingList" items="${packingList}" varStatus="status">
                                                        <option value="${packingList.common_cd}">${packingList.status_nm}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">출하방법</th>
                                            <td><input maxlength="10" type="text" id="shipping_method" name="shipping_method" size="20" class="input_s2 ta_c" style="text-align: center;" value='' ></td>
                                        </tr>
                                        <tr id="mtmdiv" style="display:none;">
                                            <th scope="row">MTM수량</th>
                                            <td><input maxlength="10" type="text" id="supplymtm_qty" name="supplymtm_qty" size="20" class="input_s1 ta_r" value='0' ></td>
                                        </tr>
                                        <tr id="btndiv" style="display:none;">
                                            <td class="ta_c" style="border-left:1px solid #e0e0e0;height:30px;background:#40464b;" colspan="2">
                                                <div id="btndiv1" style="display:none;display: inline-block;"><input type="radio" id="rtoCfr" name="rtogubun" value="OFR" /><label for="rtoCfr"><span></span>발주접수</label>&nbsp;</div>
                                                <div id="btndiv2" style="display:none;display: inline-block;"><input type="radio" id="rtoReg" name="rtogubun" value="CFM" /><label for="rtoReg"><span></span>확정</label>&nbsp;</div>
                                                <div id="btndiv3" style="display:none;display: inline-block;"><input type="radio" id="rtoModi" name="rtogubun" value="MOD" /><label for="rtoModi"><span></span>변경</label>&nbsp;</div>
                                                <div id="btndiv4" style="display:none;display: inline;"><input type="radio" id="rtoDel" name="rtogubun" value="DEL" /><label for="rtoDel"><span></span>삭제</label>&nbsp;</div>
                                                <div id="btndiv5" style="display:none;display: inline;"><input type="radio" id="rtoEnd" name="rtogubun" value="END" /><label for="rtoEnd"><span></span>부분납품종료</label>&nbsp;</div>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>

                                </div>
                            </form>
                            <!-- 버튼 -->
                            <div class="bbsB ta_r mt_10">
                                <ul class="btn_all">
                                    <li class="li_left" style="display:none;" id="btnShppingMTM"><span class="button medium"><a href="javascript:void(0)" id="shppingMTM">MTM등록</a></span>&nbsp;&nbsp;</li>
                                    <li class="li_right" style="display:none;" id="btnShippingRegister"><span class="button medium"><a href="javascript:void(0)" id="shippingRegister">처리</a></span></li>
                                    <!--li id="btnShippingDelete"><span class="button medium">삭제</span></li>
                                    <li id="btnShippingFinish"><span class="button medium">완료</span></li-->
                                </ul>
                            </div>
                            <!-- //버튼 -->
                        </fieldset>

                        <!-- //내용 -->
                    </div>
                    <div class="listleft" id="listleft">
                        <!-- 내용 -->

                        <div class="bbsL_box">
                            <!-- 검색 / 상단 -->
                            <div class="bbsS_TOP">
                                <form id="searchForm" name="searchForm" method="post">
                                    <input type="hidden" id="pagemode" name="pagemode" value="${pageParam.pagemode}" />
                                    <input type="hidden" id="nCurrpage" name="nCurrpage" value="${pageParam.nCurrpage}" />
                                    <input type="hidden" id="pagecng" name="pagecng" value="${pagecng}" />
                                    <fieldset>
                                        <legend>검색</legend>
                                        <label for="accept_dt_start" class="blind1">발주기간</label>
                                        <input size="10" maxlength="8" type="text" id="accept_dt_start" name="accept_dt_start"  value='${pageParam.accept_dt_start}' > ~
                                        <input size="10" maxlength="8" type="text" id="accept_dt_end" name="accept_dt_end"  value='${pageParam.accept_dt_end}' >
                                        <label for="coperation_cd" class="blind1">발주처</label>
                                        <select name="coperation_cd" id="coperation_cd" class="select_r1">
                                            <option value="">전체</option>
                                            <c:forEach var="companyList" items="${companyList}" varStatus="status">
                                            <option value="${companyList.company_cd}" <c:if test="${pageParam.coperation_cd == companyList.company_cd}">selected="selected"</c:if>>${companyList.company_nm}</option>
                                            </c:forEach>
                                        </select>
                                        <label for="common_cd" class="blind1" id="common_cd_label">상태구분</label>
                                        <select name="common_cd" id="common_cd" class="select_r1">
                                            <option value="">전체</option>
                                            <option value="ST_LST">출하전목록</option>
                                            <c:forEach var="commonList" items="${commonList}" varStatus="status">
                                                <option value="${commonList.common_cd}" <c:if test="${pageParam.common_cd == commonList.common_cd}">selected="selected"</c:if>>${commonList.status_nm}</option>
                                            </c:forEach>
                                        </select>
                                        <label for="material_list" class="blind1">품번</label>
                                        <select name="material_list" id="material_list" style="width:153px;">
                                            <option value="">품번선택</option>
                                            <c:forEach var="materialList" items="${materialList}" varStatus="status">
                                                <option value="${materialList.material_num}" <c:if test="${pageParam.material_list == materialList.material_num}">selected="selected"</c:if>>${materialList.material_num}</option>
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
                                        <col width="80px" />
                                        <col width="80px" />
                                        <col width="160px" />
                                        <col width="150px" />
                                        <col width="70px" />
                                        <col width="70px" />
                                        <col width="120px" />
                                        <col width="90px" />
                                        <col width="90px" />
                                        <col width="75px" />
                                        <col width="75px" />
                                        <col width="75px" />
                                        <col width="75px" />
                                        <col width="80px" />
                                        <col width="80px" />
                                        <col width="100px" />
                                        <col width="90px" />
                                        <col width="0" />
                                        <col width="0" />
                                        <col width="0" />
                                        <col width="0" />
                                        <col width="0" />
                                        <col width="0" />
                                        <col width="0" />
                                    </colgroup>
                                    <thead>
                                    <tr>
                                        <th scope="col">상태</th>
                                        <th scope="col">발주일자</th>
                                        <th scope="col" class="ta_l">업체명</th>
                                        <th scope="col" class="ta_l">자재코드</th>
                                        <th scope="col">자재유형</th>
                                        <th scope="col">포장단위</th>
                                        <th scope="col" class="ta_l">납품처</th>
                                        <th scope="col" class="ta_r">납품요청수량</th>
                                        <th scope="col" class="ta_r">납품확정수량</th>
                                        <th scope="col">출하일</th>
                                        <th scope="col">포장방법</th>
                                        <th scope="col">출하방법</th>
                                        <th scope="col" class="ta_r">출하수량</th>
                                        <th scope="col" class="ta_r">잔여수량</th>
                                        <th scope="col" class="ta_r" style="background-color:#b2b2b2;">가용재고</th>
                                        <th scope="col" class="ta_r" style="background-color:#b2b2b2;">MTM가용재고</th>
                                        <th scope="col" class="ta_r" style="background-color:#b2b2b2;">생산오더수량</th>
                                        <th scope="col" style="display:none">진행상태코드</th>
                                        <th scope="col" style="display:none">업체코드</th>
                                        <th scope="col" style="display:none">발주일</th>
                                        <th scope="col" style="display:none">출하순번</th>
                                        <th scope="col" style="display:none">수정권한</th>
                                        <th scope="col" style="display:none">품번</th>
                                        <th scope="col" style="display:none">품번</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="result" items="${shippingList}" varStatus="status">
                                    <tr class="row" bgcolor="#${result.trbgcolor}" >
                                        <td class="col"><c:out value="${result.process.status_nm}"  default=""/></td>
                                        <td class="col"><c:out value="${result.order_dt}"  default=""/></td>
                                        <td class="col ta_l"><c:out value="${result.company_nm}"  default=""/></td>
                                        <td class="col mat ta_l"><c:out value="${result.material_num}"  default=""/></td>
                                        <td class="col"><c:out value="${result.mtart_nm}"  default=""/></td>
                                        <td class="col bstrf ta_r"><fmt:formatNumber value="${result.bstrf}" pattern="#,###" /></td>
                                        <td class="col ta_l"><c:out value="${result.splace.place_nm}"  default=""/></td>
                                        <td class="col reqqty ta_r"><fmt:formatNumber value="${result.supply_req_qty}" pattern="#,###" /></td>
                                        <c:choose>
                                            <c:when test = "${result.confirm_qty == ''}">
                                                <td class="col ta_r"><c:out value="${result.confirm_qty}"  default=""/></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td class="col ta_r"><fmt:formatNumber value="${result.confirm_qty}" pattern="#,###" /></td>
                                            </c:otherwise>
                                        </c:choose>
                                        <td class="col sdt"><c:out value="${result.supply_dt}"  default=""/></td>
                                        <c:choose>
                                            <c:when test = "${result.packing_cd == 'OM_MTM'}">
                                                <td class="link mtm"><c:out value="${result.packing.status_nm}"  default=""/></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td class="col"><c:out value="${result.packing.status_nm}"  default=""/></td>
                                            </c:otherwise>
                                        </c:choose>
                                        <td class="col"><c:out value="${result.shipping_method}"  default=""/></td>
                                        <c:choose>
                                            <c:when test = "${result.supply_qty == ''}">
                                                <td class="col ta_r"><c:out value="${result.supply_qty}"  default=""/></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td class="col ta_r"><fmt:formatNumber value="${result.supply_qty}" pattern="#,###" /></td>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test = "${result.remain_qty == ''}">
                                                <td class="col ta_r"><c:out value="${result.remain_qty}"  default=""/></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td class="col ta_r"><fmt:formatNumber value="${result.remain_qty}" pattern="#,###" /></td>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test = "${result.inven_use_qty == ''|| result.inven_use_qty == '0' }">
                                                <td class="col inuse ta_r"><c:out value="${result.inven_use_qty}"  default=""/></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td class="link material ta_r"><fmt:formatNumber value="${result.inven_use_qty}" pattern="#,###" /></td>
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
                                        <td scope="col" style="display:none" class="flag"><c:out value="${result.process.common_cd}"  default=""/></td>
                                        <td scope="col" style="display:none" class="shipping_key"><c:out value="${result.shipping_key}"  default=""/></td>
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

                                <!-- 페이징 -->
                                <div  id="PAGE_NAVI" class="col_paging">${pageNavigater}</div>
                                <!-- // 페이징 -->
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
        if ("${pagecng}" == "PRELIST") {
            $("#shipping_adminlist").attr('class', 'current');
            $('#common_cd_label').attr('style', "display:none;");
            $('#common_cd').attr('style', "display:none;");

        }
        else if ("${pagecng}" == "BOSLIST") {
            $("#shipping_adminboslist").attr('class', 'current');
            $('#common_cd_label').attr('style', "display:none;");
            $('#common_cd').attr('style', "display:none;");

        }
        else{
            $("#shipping_admin").attr('class', 'current');
        }

        $("#bbsL_WRITE_box").find("input, select, button, textarea, a").prop("disabled",true);
        $("#accept_dt_start").datepicker({
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
            ,maxDate: "+1Y" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
        });
        $("#accept_dt_end").datepicker({
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
            ,maxDate: "+1Y" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
        });

        $("#shipping_dt").datepicker({
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
            ,maxDate: "+1Y" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
        });

        $("#supply_dt").datepicker({
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
            ,minDate: "0D" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
            ,maxDate: "+1Y" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
        });

        $('#btnShippingDelete').on('click', function() {
            $.ajax({
                url: "/admin",
                context: document.body,
                async: true
            }).done(function(data) {
                $( "#tbllist" ).load();
            });

        });


        $("a[name = 'pageMove']").unbind("click").click(function(e) {
            var comSubmit = new ComSubmit("searchForm");
            if ("${pagecng}" == "PRELIST") {
                comSubmit.setUrl("/admin/prelist");
            }
            else if ("${pagecng}" == "BOSLIST") {
                comSubmit.setUrl("/admin/boslist");
            }
            else{
                comSubmit.setUrl("/admin");
            }
            $("#nCurrpage").val($(this).attr("content_id"))
            comSubmit.submit();

        });

        $("#material_list").select2();

        $('#confirm_qty').on('change',function(){
            var val = $('#confirm_qty').val();
        });

        $('#confirm_qty').number( true, 0 );

        $('#supply_qty').on('change',function(){
            var val = $('#supply_qty').val();
        });

        $('#supply_qty').number( true, 0 );

        $("#tb").find("tbody").find("td:nth-child(2)").bind("click",function(){
            //자신의 배경색을 제외사고 색을 white로 변경
            //$(this).parent().siblings().css("background","white");
            //모든 tr 배경색을 white
            $("#tb tr").css("background","white");
            //선택된 tr의 배경색을 cyan
            $(this).parent().css("background","cyan");
            alert($(this).prev().text());
        });

        $("#tbllist tr td.col").click(function() {
            // 현재 클릭된 Row(<tr>)
            var tr = $(this).closest('tr');
            var td = tr.children();
            $('input[name="rtogubun"]').removeAttr('checked');

            $(this).closest('tr').addClass("trbackcolor");                      //클릭된 부분을 상단에 정의된 CCS인 selected클래스로 적용
            $(this).closest('tr').siblings().removeClass("trbackcolor");  //siblings:형제요소들,    removeClass:선택된 클래스의 특성을 없앰

            // td.eq(index)를 통해 값 호출
            var regExp = /[\{\}\[\]\/?.,;:₩|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi
            var flag = td.eq(tr.find(".flag").index()).text();
            var shipping_key = td.eq(tr.find(".shipping_key").index()).text();

            $("#process_cd").val(flag);
            $('#shipping_key').val(shipping_key);

            if (flag == "ST_BOS") {
                $("#bbsL_WRITE_box").find("input, select, button, textarea, a").prop("disabled", false);
                $('#btndiv').attr('style', "display:;");
                $('#btndiv1').attr('style', "display:inline-block;");
                $('#btndiv2').attr('style', "display:none;");
                $('#btndiv3').attr('style', "display:none;");
                $('#btndiv4').attr('style', "display:none;");
                $('#btndiv5').attr('style', "display:none;");
                $('#btnShippingRegister').attr('style', "display:inline-block;");
                $('#btnShppingMTM').attr('style', "display:none;");
            }else if (flag == "ST_OFR") {
                    $("#bbsL_WRITE_box").find("input, select, button, textarea, a").prop("disabled",false);
                    $('#btndiv').attr('style', ";");
                    $('#btndiv1').attr('style', "display:none;");
                    $('#btndiv2').attr('style', "display:none;");
                    $('#btndiv3').attr('style', "display:inline-block;");
                    $('#btndiv4').attr('style', "display:inline-block;");
                    $('#btndiv5').attr('style', "display:inline-block;");
                    $('#btnShippingRegister').attr('style', "display:;");
                    $('#btnShppingMTM').attr('style', "display:none;");
            }else if (flag == "ST_ING" || flag == "ST_CFM") {

                $("#bbsL_WRITE_box").find("input, select, button, textarea, a").prop("disabled",false);
                $('#btndiv').attr('style', "display:;");
                $('#btndiv1').attr('style', "display:none;");
                $('#btndiv2').attr('style', "display:none;");
                $('#btndiv3').attr('style', "display:inline-block;");
                $('#btndiv4').attr('style', "display:inline-block;");
                $('#btndiv5').attr('style', "display:inline-block;");
                $('#btnShippingRegister').attr('style', "display:;");
                $('#btnShppingMTM').attr('style', "display:none;");
            } else if (flag == "ST_BCP" || flag == "ST_SPG") {
                $("#bbsL_WRITE_box").find("input, select, button, textarea, a").prop("disabled",true);
                $('#btndiv').attr('style', "display:none;");
                $('#btndiv1').attr('style', "display:none;");
                $('#btndiv2').attr('style', "display:none;");
                $('#btndiv3').attr('style', "display:none;");
                $('#btndiv4').attr('style', "display:none;");
                $('#btndiv5').attr('style', "display:none;");
                $('#btnShippingRegister').attr('style', "display:none;");
            }
            else{
                $("#bbsL_WRITE_box").find("input, select, button, textarea, a").prop("disabled",true);
                $('#btndiv').attr('style', "display:none;");
                $('#btndiv1').attr('style', "display:none;");
                $('#btndiv2').attr('style', "display:none;");
                $('#btndiv3').attr('style', "display:none;");
                $('#btndiv4').attr('style', "display:none;");
                $('#btndiv5').attr('style', "display:none;");
                $('#btnShippingRegister').attr('style', "display:none;");
            }
            fnDisplayContent();

        });

        $("#packing_method").on( "change", function() {
            $("#packing_method option:selected").each(function(){
                if ($(this).val() == "OM_MTM"){
                    $('#btnShppingMTM').attr('style', "display:;");
                    $('#mtmdiv').attr('style', "display:;");
                    $('#supplymtm_qty').val("0");
                    $('#supplymtm_qty').attr("readonly",true);

                }
                else{
                    $('#btnShppingMTM').attr('style', "display:none;");
                    $('#mtmdiv').attr('style', "display:none;");
                    $('#supply_qty').removeAttr("readonly");
                }
            });
        });

        $("#tbllist tr td.link.mtm").click(function() {
            var tr = $(this).closest('tr');
            var td = tr.children();
            var shipping_key = td.eq(tr.find(".shipping_key").index()).text();
            var param = {'shipping_key':shipping_key}
            setPostPopupWin("/admin/mtmpop",param,"mtmpop",0,0,960,500,'auto');
        });

        $("#tbllist tr td.link.material").click(function() {
            var tr = $(this).closest('tr');
            var td = tr.children();
            var material_num = td.eq(tr.find(".mat").index()).text();
            var param = {'material_num':material_num}
            setPostPopupWin("/admin/materialpop",param,"materialpop",0,0,660,500,'auto');
        });
        
        $('#shippingExcel').on("click", function(e){
            var comSubmit = new ComSubmit("searchForm");
            comSubmit.setUrl("/admin/shippingexcel");
            comSubmit.submit();
        });

        $("#shppingMTM").on("click",function() {
            var shipping_key = $("#shipping_key").val();
            var order_dt = $("#order_dt").val();
            var material_num = $("#material_num").val();
            var shipping_seq = $("#shipping_seq").val();
            var confirm_qty = $("#confirm_qty").val();
            var supply_dt = $("#supply_dt").val();
            var allsupply_qty = $("#allsupply_qty").val();
            var supplymtm_qty = $("#supplymtm_qty").val();
            if (supplymtm_qty.length == 0){
                supplymtm_qty = "0";
            }

            var remain_qty = parseInt(confirm_qty) - parseInt(allsupply_qty) + parseInt(supplymtm_qty);
            var param = {'shipping_key':shipping_key,'material_num':material_num}
            setPostPopupWin("/admin/mtmreg",param,"mtmreg",0,0,900,600,'auto');
        });

        $('#searchBtn').on("click", function(e){
            $("#pagemode").val("search");
            var comSubmit = new ComSubmit("searchForm");
            if ("${pagecng}" == "PRELIST") {
                comSubmit.setUrl("/admin/prelist");
            }
            else if ("${pagecng}" == "BOSLIST") {
                comSubmit.setUrl("/admin/boslist");
            }
            else{
                comSubmit.setUrl("/admin");
            }
            comSubmit.submit();
        });

        $('#shippingRegister').click(function() {
            var regExp = /[\{\}\[\]\/?.,;:₩|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi
            var process_cd = $("#process_cd").val();

            var rtogubun = $('input[name="rtogubun"]:checked').val();
            if(typeof rtogubun == "undefined"){
                alert("어떤 처리를 할지 선택해주세요.");
                return;
            }
            if (rtogubun == "OFR") { $("#processFlag").val("insert"); }
            else if (rtogubun == "CFM") { $("#processFlag").val("insert"); }
            else if (rtogubun == "MOD") { $("#processFlag").val("update"); }
            else if (rtogubun == "DEL") { $("#processFlag").val("delete"); }
            else if (rtogubun == "END") { $("#processFlag").val("finish");}

            var srcEl = $("#registerForm");
            var dataFlag = false;
            var datanum = 0;
            $.each(srcEl[0].elements, function(index,elem) {
                var _this = $(elem);

                objid = _this.prop('id').toLowerCase();
                typenm = _this.prop("type").toUpperCase();
                if (typenm == "TEXT") {
                    if (($("#" + objid).val().trim() != "0") && ($("#" + objid).val().trim().length > 0)) {
                        datanum ++;
                    }
                    else{
                    	if(objid == "shipping_method"){
                    		datanum ++;
                    	}
                    }
                    if (!dataFlag) {
                        if (($("#" + objid).val().trim() == "0") || ($("#" + objid).val().trim().length == 0)) {
                            if ((objid == "supply_qty") || (objid == "supplymtm_qty")) {
                                if (parseInt($("#supply_qty").val().trim()) + parseInt($("#supplymtm_qty").val().trim()) == 0) {
                                    dataFlag = true;
                                }
                            }
                        }
                    }

                }
                if (typenm == "SELECT-ONE") {
                    if ($("#" + objid).val().trim() != "" && $("#" + objid).val().trim() != "OM_TBD") {
                        datanum ++;
                    }
                    if (!dataFlag) {
                        if ($("#" + objid).val().trim() == "" || $("#" + objid).val().trim() == "OM_TBD") {
                            dataFlag = true;
                        }
                    }
                }
            });

            if (parseInt($("#confirm_qty").val().trim()) == 0 ){
                alert("납품확정수량 반드시 입력하셔야 합니다.");
                $("#confirm_qty").focus();
                return;
            }

            if (parseInt($("#supply_qty").val().trim().replace(regExp, "")) > parseInt($("#confirm_qty").val().trim().replace(regExp, "")) ){
                alert("출하 수량은 납품확정수량보다 작아야 합니다.");
                $("#supply_qty").focus();
                return;
            }


            var comfirm_qty = parseInt($("#confirm_qty").val().trim().replace(regExp, ""))
            var supply_qty = parseInt($("#supply_qty").val().trim().replace(regExp, ""))
            var supplymtm_qty = parseInt($("#supplymtm_qty").val().trim().replace(regExp, ""))

            if (process_cd != "ST_BOS") {
                if (supply_qty > parseInt($("#remainallqty").val().trim())) {
                    alert("출하수량은 잔여 수량보다 작아야 합니다.");
                    $("#supply_qty").focus();
                    return;
                }

                if (supply_qty > parseInt($("#remaininvenqty").val().trim())) {
                    alert("출하수량은 가용재고보다 작아야 합니다.");
                    $("#supply_qty").focus();
                    return;
                }

                if (supplymtm_qty > parseInt($("#remainmtmqty").val().trim())) {
                    alert("출하수량은 MTM가용재고보다 작아야 합니다.");
                    $("#supply_qty").focus();
                    return;
                }
            }
            var msg = "";
            if (process_cd == "ST_BOS") {
                if (dataFlag){
                    if (parseInt($("#confirm_qty").val().trim()) > 0 && $("#shipping_dt").val().trim().length > 0 && datanum > 2){
                        msg = "확정대기로 진행하시겠습니까?";
                        $("#nextprocess_cd").val("ST_ING");
                    }
                    else {
                        msg = "발주접수를 진행하시겠습니까?";
                        $("#nextprocess_cd").val("ST_OFR");
                    }
                }
                else{
                    msg = "대기중 내역을 확정으로 진행하시겠습니까?";
                    $("#nextprocess_cd").val("ST_CFM");
                }
            }
            else {
                if (rtogubun == "END") {
                    msg = "완료처리를 하시겠습니까?";
                    $("#nextprocess_cd").val("ST_CFM");
                }
                else if (rtogubun == "DEL") {
                    msg = "삭제처리를 하시겠습니까?";
                    $("#nextprocess_cd").val("ST_OFR");
                }
                else{
                    if (dataFlag) {
                        if (parseInt($("#confirm_qty").val().trim()) > 0 && $("#shipping_dt").val().trim().length > 0 && datanum > 2) {
                            msg = "확정대기로 수정처리를 하시겠습니까?";
                            $("#nextprocess_cd").val("ST_ING");
                        } else {
                            msg = "수정처리를 하시겠습니까?";
                        }
                    } else {
                    	if (parseInt($("#confirm_qty").val().trim()) > 0 && $("#shipping_dt").val().trim().length > 0 && datanum > 2 && datanum < 6) {
                            msg = "확정대기로 수정처리를 하시겠습니까?";
                            $("#nextprocess_cd").val("ST_ING");
                        } else {
                        	msg = "확정으로 수정처리를 하시겠습니까?"
                                $("#nextprocess_cd").val("ST_CFM");
                        }
                        
                    }
                }
            }
            if (window.confirm(msg)) {
                $("#registerForm").ajaxForm({
                    type: 'POST',
                    url: "/admin/processShippingInfo",
                    dataType: "json",
                    enctype: "multipart/form-data",
                    contentType: false,
                    processData: false,
                    timeout: 30000,
                    success: function(result) {
                        if (result.status == 1) {
                            alert(result.msg);
                            var comSubmit = new ComSubmit("searchForm");
                            if ("${pagecng}" == "PRELIST") {
                                comSubmit.setUrl("/admin/prelist");
                            }
                            else if ("${pagecng}" == "BOSLIST") {
                                comSubmit.setUrl("/admin/boslist");
                            }
                            else{
                                comSubmit.setUrl("/admin");
                            }
                            comSubmit.submit();
                            //window.location.reload(true);

                        }
                        else if (result.status == 0) {
                            alert(result.msg);
                        }
                    },
                    error: function(data, status, err) {
                        alert("서버가 응답하지 않습니다." + "\n" + "다시 시도해주시기 바랍니다." + "\n"
                            + "code: " + data.status + "\n"
                            + "message :" + data.responseText + "\n"
                            + "message1 : " + status + "\n"
                            + "error: " + err);
                    }
                }).submit();
            }

        });

        function fnDisplayContent(){

            $("#registerForm").ajaxForm({
                type: 'POST',
                url: '/admin/detailShipping',
                dataType: "json",
                enctype: "multipart/form-data",
                contentType: false,
                processData: false,
                timeout: 30000,
                success: function(result) {

                    if (result.status == 0) {
                        if (result.shippingDetail.process == null) {
                            $("#process_nm_str").text("");
                        } else {
                            $("#process_nm_str").text(result.shippingDetail.process.status_nm);
                        }
                        $("#orderfor_key").val(result.shippingDetail.orderfor_key);
                        $("#material_num").val(result.shippingDetail.material_num);
                        $("#process_cd").val(result.shippingDetail.process_cd);
                        $("#nextprocess_cd").val(result.shippingDetail.process_cd);
                        $("#company_cd_str").text(result.shippingDetail.company_cd);
                        $("#company_nm_str").text(result.shippingDetail.company_nm);

                        if (result.shippingDetail.firstaccept_dt == null)
                            $("#accept_dt_str").text("");
                        else
                            $("#accept_dt_str").text(result.shippingDetail.firstaccept_dt);
                        $("#material_num_str").text(result.shippingDetail.material_num);
                        $("#bstrf_str").text($.number(result.shippingDetail.bstrf));
                        $("#mtart_nm_str").text(result.shippingDetail.mtart_nm);
                        $("#supply_req_qty_str").text($.number(result.shippingDetail.supply_req_qty));
                        $("#supply_req_dt_str").text(result.shippingDetail.supply_req_dt);
                        if (result.shippingDetail.splace == null) {
                            $("#supply_place_str").text("");
                        } else {
                            $("#supply_place_str").text(result.shippingDetail.splace.place_nm);
                        }
                        $("#inven_use_qty_str").text($.number(result.shippingDetail.inven_use_qty));
                        $("#mtm_qty_str").text($.number(result.shippingDetail.mtm_use_qty));
                        $("#prod_order_qty_str").text($.number(result.shippingDetail.prod_order_qty));

                        var packing_cd = result.shippingDetail.packing;
                        if (packing_cd == null) {
                            $("#packing_method").val("OM_TBD");
                            packing_cd = "OM_TBD";
                        } else {
                            $("#packing_method").val(result.shippingDetail.packing.common_cd);
                            packing_cd = result.shippingDetail.packing.common_cd;
                        }

                        $("#shipping_method").val(result.shippingDetail.shipping_method);
                        var shipping_seq = result.shippingDetail.shipping_seq;
                        if (shipping_seq == "0") {
                            $("#supply_qty").val("0");
                        }
                        else{
                            if (result.shippingDetail.packing_cd == "OM_MTM") {
                                $("#supply_qty").val(parseInt(result.shippingDetail.supply_qty)-parseInt(result.shippingDetail.mtmreg_qty));
                                $("#supplymtm_qty").val(result.shippingDetail.mtmreg_qty);
                            }
                            else{
                                $("#supply_qty").val(result.shippingDetail.supply_qty);
                            }
                        }
                        $("#supply_dt").val(result.shippingDetail.supply_dt);
                        $("#unit_price").val(result.shippingDetail.unit_price);

                        $("#remainallqty").val(result.shippingDetail.remainallqty);
                        $("#remaininvenqty").val(result.shippingDetail.remaininvenqty);
                        $("#remainmtmqty").val(result.shippingDetail.remainmtmqty);
                        if (packing_cd == "OM_MTM"){
                            $("#modi_mtm_item").val(result.shippingDetail.mtminfo.pre_storage_loc); //jquery 이용
                            $("#modi_mtm_loc").val(result.shippingDetail.mtminfo.modi_meterial_num); //jquery 이용
                            $("#modi_mtm_qty").val(result.shippingDetail.mtminfo.modi_qty); //jquery 이용
                            $("#modi_mtm_ploc").val(result.shippingDetail.mtminfo.storage_loc); //jquery 이용
                        }
                        if (result.shippingDetail.process_cd == "ST_BOS"){
                            $("#confirm_qty").val(result.shippingDetail.supply_req_qty);
                            $("#shipping_dt").val(result.shippingDetail.supply_req_dt);
                        }
                        else{
                            $("#confirm_qty").val(result.shippingDetail.confirm_qty);
                            $("#shipping_dt").val(result.shippingDetail.shipping_dt);
                        }
                        if (packing_cd == "OM_MTM"){
                            $('#btnShppingMTM').attr('style', "display:;");
                            $('#mtmdiv').attr('style', "display:;");
                            $('#supplymtm_qty').attr("readonly",true);
                        }
                        else{
                            $('#btnShppingMTM').attr('style', "display:none;");
                            $('#mtmdiv').attr('style', "display:none;");
                            $('#supplymtm_qty').val("0");
                        }

                    }
                    else if (result.status == 1) {
                        alert(result.msg);
                    }
                },
                error: function(data, status, err) {
                    alert("서버가 응답하지 않습니다." + "\n" + "다시 시도해주시기 바랍니다." + "\n"
                        + "code: " + data.status + "\n"
                        + "message :" + data.responseText + "\n"
                        + "message1 : " + status + "\n"
                        + "error: " + err);
                }
            }).submit();
        }

        function genRowspan(className){

            $("#" + className).each(function() {

                var rows = $("#" + className + ":contains('" + $(this).text() + "')");

                if (rows.length > 1) {

                    rows.eq(0).attr("rowspan", rows.length);

                    rows.not(":eq(0)").remove();

                }

            });

        }

        /*
         *
         * 같은 값이 있는 열을 병합함
         *
         * 사용법 : $('#테이블 ID').rowspan(0);
         *
         */
        $.fn.rowspan = function(colIdx, isStats) {
            return this.each(function(){
                var that;
                $('tr', this).each(function(row) {
                    $('td:eq('+colIdx+')', this).filter(':visible').each(function(col) {
                        if ($(this).html() == $(that).html()&&
                            ((!isStats || isStats && $(this).prev().html() == $(that).prev().html()))) {
                            rowspan = $(that).attr("rowspan") || 1;
                            rowspan = Number(rowspan)+1;

                            $(that).attr("rowspan",rowspan);
                            // do your action for the colspan cell here
                            $(this).hide();
                            //$(this).remove();
                            // do your action for the old cell here
                        } else {
                            that = this;
                        }

                        //$(this).colspan(row); // row 돌때 마다 colspan

                        // set the that if not already set
                        that = (that == null) ? this : that;

                    });
                });
            });
        };



        /*
         *
         * 같은 값이 있는 행을 병합함
         *
         * 사용법 : $('#테이블 ID').colspan (0);
         *
         */
        $.fn.colspan = function(rowIdx) {
            return this.each(function(){
                var that;
                $('tr', this).filter(":eq("+rowIdx+")").each(function(row) {
                    $(this).find('td').filter(':visible').each(function(col) {
                        if ($(this).html() == $(that).html()) {
                            colspan = $(that).attr("colSpan") || 1;
                            colspan = Number(colspan)+1;

                            $(that).attr("colSpan",colspan);
                            $(this).hide(); // .remove();
                        } else {
                            that = this;
                        }
                        that = (that == null) ? this : that;
                    });
                });
            });
        };


    });

    function setPostPopupWin(url,params,winnm,winl,wint,nWidth,nHeight,strScroll) {

        var nScnWidth = screen.width/2;
        var nScnHeight = screen.height/2;

        if (winl == 0)
            winl = nScnWidth - nWidth/2;

        if (wint == 0)
            wint = nScnHeight - nHeight/2;

        if (strScroll == "auto") strScroll = "yes";

        var settings  ='height='+nHeight+'px,';
        settings +='width='+nWidth+'px,';
        settings +='top='+wint+'px,';
        settings +='left='+winl+'px,';
        settings +='scrollbars='+strScroll+',';
        settings +='toolbar=no,location=no,directories=no,status=no,resizable=yes,menubar=no,copyhistory=no';

        var win = window.open("",winnm,settings);

        var $form = $('<form></form>');
        $form.attr('action', url);
        $form.attr('method', 'post');
        $form.attr('target', winnm);
        $form.appendTo('body');
        for(var key in params) {
            var hiddenField = $('<input name="'+key+'" type="hidden" value="'+params[key]+'">');
            $form.append(hiddenField);
        }
        $form.submit();

        if (!win)
            alert('차단된 팝업창을 허용해 주세요.');
        else{
            win.window.resizeTo(nWidth,nHeight);

            if(parseInt(navigator.appVersion) >= 4){win.window.focus();}
        }
    }

</script>
</body>
</html>