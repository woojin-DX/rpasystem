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
    String localPageTitle = " > 구매오더발주 접수";
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
                    <h3>▶ 구매오더발주접수 </h3>
                </div>
                <div id="content_start">
                    <div class="formleft3">
                        <!-- 내용 -->
                        <div class="content_subtitle">
                            <h3> </h3>
                        </div>
                        <!-- 글쓰기 -->
                        <fieldset>
                            <form id="registerForm" name="registerForm" enctype="multipart/form-data">
                                <input type="hidden" id="company_cd" name="company_cd" value="${infoParam.company_cd}" />
                                <input type="hidden" id="user_id" name="user_id" value="${infoParam.user_id}" />
                                <input type="hidden" id="orderfor_key" name="orderfor_key" value="" />
                                <input type="hidden" id="order_dt" name="order_dt" value="${pageParam.order_dt}" />
                                <input type="hidden" id="material_num" name="material_num" value="" />
                                <input type="hidden" id="processFlag" name="processFlag" value="insert" />  
                                <input type="text" style="width:0px; display: none;" />
                                <div class="bbsCom_WRITE_box">
                                    <table class="bbsCom_WRITE">
                                        <!-- 헤드부 -->
                                        <thead>
                                        <caption> 출하정보 상세내역 </caption>
                                        <colgroup>
                                            <col width="120" />
                                            <col width="120" />
                                        </colgroup>
                                        </thead>
                                        <!-- 바디부 -->
                                        <tbody>
                                        
                                         <tr>
                                            <th scope="row" style="font-size:12px" class="ta_r" > 구매오더번호 </th>
                                            <td><input type="text" maxlength="15" id="web_EBELN" name="web_EBELN" size="12" class="input_s1 ta_r" readonly /></td>
                                        </tr>
                                         <tr>
                                            <th scope="row" style="font-size:12px" class="ta_r" > 구매오더 품목번호</th>
                                            <td><input type="text" maxlength="10" id="web_EBELP" name="web_EBELP" size="12" class="input_s1 ta_r" readonly/></td>
                                        </tr>
                                         <tr>
                                            <th scope="row" style="font-size:12px" class="ta_r" > 자재번호</th>
                                            <td><input type="text" maxlength="10" id="web_MATNR" name="web_MATNR" size="12" class="input_s1 ta_r" readonly/></td>
                                        </tr>
                                         <tr>
                                            <th scope="row" style="font-size:12px" class="ta_r" > 자재내역</th>
                                            <td><input type="text" maxlength="10" id="web_TXZ01" name="web_TXZ01" size="12" class="input_s1 ta_r" readonly/></td>
                                        </tr>
                                         <tr>
                                            <th scope="row" style="font-size:12px" class="ta_r"> 발주수량</th>
                                            <td><input type="text" maxlength="10" id="web_MENGE" name="web_MENGE" size="12" class="input_s1 ta_r" readonly/></td>
                                        </tr>
                                         <tr>
                                            <th scope="row" style="font-size:12px" class="ta_r"> 단위</th>
                                            <td><input type="text" maxlength="10" id="web_MEINS" name="web_MEINS" size="12" class="input_s1 ta_r" readonly/></td>
                                        </tr>
                                         <tr>
                                            <th scope="row" style="font-size:12px" class="ta_r"> 납품요청일</th>
                                            <td><input type="text" maxlength="10" id="web_EINDT" name="web_EINDT" size="12" class="input_s1 ta_r" readonly/></td>
                                        </tr>
                                         <tr>
                                            <th scope="row" style="font-size:12px" class="ta_r"> 단가</th>
                                            <td><input type="text" maxlength="10" id="web_NETPR" name="web_NETPR" size="12" class="input_s1 ta_r" readonly/></td>
                                        </tr>
                                         <tr>
                                            <th scope="row" style="font-size:12px" class="ta_r"> 공급업체 접수일자</th>
                                            <td><input type="text" maxlength="10" id="web_SUPP_JUPDT" name="web_SUPP_JUPDT" size="12" class="input_s1 ta_r" readonly/></td>
                                        </tr>
                                         <tr>
                                            <th scope="row" style="font-size:12px" class="ta_r"> 공급업체 접수시간</th>
                                            <td><input type="text" maxlength="10" id="web_SUPP_JUPTM" name="web_SUPP_JUPTM" size="12" class="input_s1 ta_r" readonly/></td>
                                        </tr>
                                         <tr>
                                            <th scope="row" style="font-size:12px" class="ta_r"> 공급업체 최종변경자</th>
                                            <td><input style="background-color:orange" type="text" maxlength="10" id="web_SUPP_F_IN_UNAME" name="web_SUPP_F_IN_UNAME" size="12" class="input_s1 ta_r" readonly/></td>
                                        </tr>
                                       
                                         <tr>
                                           <th scope="row" style="font-size:12px" class="ta_r"> 납품예정수량</th>
                                            <td><input type="text" maxlength="10" id="web_END_MENGE" name="web_END_MENGE" size="12" class="input_s1 ta_r" readonly/></td>
                                        </tr>
                                         <tr>
                                            <th scope="row" style="font-size:12px" class="ta_r"> 최초납품예정일</th>
                                          	<td><input style="background-color:orange" maxlength="10" type="text" id="supply_req_dt" name="supply_req_dt" size="12" class="input_s1 ta_c" value='' readonly></td>
                                        </tr>
                                          <tr>
                                            <th scope="row" style="font-size:12px" class="ta_r"> 최종납품예정일</th>
                                            <td><input style="background-color:orange" maxlength="10" type="text" id="supply_req_dt2" name="supply_req_dt2" size="12" class="input_s1 ta_c" value='' readonly></td>
                                        </tr>
                                          <tr>
                                            <td><input type="hidden" maxlength="10" id="web_SUPP_E_MOD_UNAME" name="web_SUPP_E_MOD_UNAME" size="12" class="input_s1 ta_r" readonly/></td>
                                        </tr>
                                        <tr id="btndiv">
                                            <td class="ta_c" style="border-left:1px solid #e0e0e0;height:30px;background:#000000;" colspan="2">
                                                <div id="btndiv1" style="display:none;display: inline-block;"><input type="radio" id="rtoCfr" name="rtogubun" value="OFR" /><label for="rtoCfr"><span style="color:white">발주접수</span></label>&nbsp;</div>
                                        </td>
                                        
                                        
                                       <%--  
                                       	<tr>
                                            <th scope="row">발주업체명</th>
                                            <td>${infoParam.company_nm}</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">품번</th>
                                            <td><select name="knumh" id="knumh" style="width:165px;">
                                                <option value="">품번선택</option>
                                                <c:forEach var="materialList" items="${materialList}" varStatus="status">
                                                    <option value="${materialList.knumh}">${materialList.material_num}</option>
                                                </c:forEach>
                                            </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">납품처</th>
                                            <td>
                                                <select name="place_key" id="place_key" style="font-size:14px;font-weight:bold;width:165px;height:24px;">
                                                    <option value="">납품처선택</option>
                                                    <c:forEach var="placeList" items="${placeList}" varStatus="status">
                                                        <option value="${placeList.place_key}">${placeList.place_nm}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">수량</th>
                                            <td><input type="text" maxlength="10" id="supply_req_qty" name="supply_req_qty" size="17" class="input_s1 ta_r" /></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">납품요청일</th>
                                            <td><input maxlength="10" type="text" id="supply_req_dt" name="supply_req_dt" size="17" class="input_s1 ta_c" value='' ></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">단가</th>
                                            <td><input type="text" maxlength="8" id="unit_price" name="unit_price" size="17" readonly class="input_r ta_r" /></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">금액</th>
                                            <td><input type="text" maxlength="12" id="total_price" name="total_price" size="17" class="input_r ta_r" readonly /></td>
                                        </tr>
                                        <tr id="receive" style="display: none;">
                                            <th scope="row">수령일</th>
                                            <td><input maxlength="10" type="text" id="receive_dt" name="receive_dt" size="17" class="input_s1 ta_c" value='' ></td>
                                        </tr>
                                         --%>
                                        
                                        
                                        </tbody>
                                    </table>
                                </div>
                            </form>
                            <!-- 버튼 -->
                            <div class="bbsB ta_r mt_20">
                                <ul class="btn_right">
                                    <li id="btnConfirm" style="display:none;"><span class="button medium"><a href="javascript:void(0)" id="receiveConfirm">수령완료</a></span></li>
                                    <li id="btnRegister"><span class="button medium"><a href="javascript:void(0)" id="supplyRegister">등록</a></span></li>
                                    <li id="btnModify" style="display:none;"><span class="button medium"><a href="javascript:void(0)" id="supplyModify">수정</a></span></li>
                                    <li id="btnDelete" style="display:none;"><span class="button medium"><a href="javascript:void(0)" id="supplyDelete">삭제</a></span></li>
                                    <li id="btnCancle" style="display:none;"><span class="button medium"><a href="javascript:void(0)" id="supplyCancle">등록으로 가기</a></span></li>
                                </ul>
                            </div>
                            <!-- //버튼 -->
                        </fieldset>
  
                    </div>
                    <div class="listleft">
                        <!-- 내용 --> 
                        <!-- 목록 -->
                        <div class="bbsL_box_web">
                            <!-- 검색 / 상단 -->
                            <div class="bbsCom_TOP">
                                <form id="searchForm" name="searchForm">
                                	<!-- <span class="button large" ><a href="javascript:void(0)" id="btn_bos">&nbsp;&nbsp;&nbsp;대기&nbsp;&nbsp;&nbsp;</a></span>
                                	<span class="button large"><a href="javascript:void(0)" id="btn_all">&nbsp;&nbsp;&nbsp;전체&nbsp;&nbsp;&nbsp;</a></span> -->
                                	<label style="font-size:20px;font-weight:bold;">공급 업체명 : ${infoParam.company_nm}</label>
                                    <input type="text" style="width:0px; visibility: hidden;">
                                    <input type="hidden" id="pagemode" name="pagemode" value="${pageParam.pagemode}" />
                                    <input type="hidden" id="nCurrpage" name="nCurrpage" value="${pageParam.nCurrpage}" />
                              	
                                    <%-- <fieldset>
                                        <legend>검색</legend>
                              			<label for="order_dt_start" class="blind1">발주기간</label>
                                    	<input size="10" maxlength="8" type="text" id="order_dt_start" name="order_dt_start"  value='${pageParam.order_dt_start}' > ~
                                    	<input size="10" maxlength="8" type="text" id="order_dt_end" name="order_dt_end"  value='${pageParam.order_dt_end}' >
                                   		<input type="image" src="/images/board/btn_schok.gif"  id="searchBtn" alt="검색" title="검색" class="btn_schok" />
                                    </fieldset> --%>
                                    
                                    <td><input style="float:right" type="text" maxlength="10" id="web_SUPP_E_MOD_UNAME1" name="web_SUPP_E_MOD_UNAME1" size="17" class="input_s1 ta_r" /></td>
                                	<label style="font-size:15px;font-weight:bold;float:right">공급 업체 접수자 : </label>
                                </form>
                            </div>
                            <!-- //검색 / 상단 -->
                            
                            <table cellspacing="0" border="1" summary="목록정보"  cellpadding="0" id="tbllist" class="bbsCom2">
                                <caption>게시판 목록</caption>
                                <colgroup>
                                    <col width="100px" />
                                    <!-- <col width="230px" />  -->
                                    <col width="70px" />	<!-- 품목번호 -->
                                    <col width="150px" />
                                    <col width="360px" />  <!-- 자재내역 -->
                                    <col width="100px" />	<!-- 발주수량 -->
                                    <col width="50px" />	<!-- 단위 -->
                                    <col width="80px" />	<!-- 납품요청일 -->
                                    <col width="60px" />	<!-- 단가-->
                                    <col width="130px" />
                                   <!--  <col width="80px" /> -->
                                    <col width="80px" />
                                    <col width="100px" />
                                    <col width="110px" /> 	<!-- 최초납품예정일-->
                                    <col width="110px" />
                                    <col width="0" />
                                </colgroup>
                                <thead>
                                <tr>
                                    <th scope="col">구매오더번호</th>
                                    <th class="ta_l" scope="col">품목번호</th>
                                    <th class="ta_l" scope="col">자재코드</th>
                                    <th scope="col" class="ta_c">자재내역</th>
                                    <th scope="col" class="ta_r">발주수량</th>
                                    <th scope="col" class="ta_l">단위</th>
                                    <th scope="col" class="ta_l">납품요청일</th>
                                    <th scope="col" class="ta_r">단가</th>
                                    <th scope="col" class="ta_l">공급업체접수일자</th> 
                                 <!--    <th scope="col" class="ta_l">공급업체 접수시간</th> -->
                                    <th scope="col" class="ta_r">최종변경자</th>
                                    <th scope="col" class="ta_r">납품예정수량</th>
                                    <th scope="col" class="ta_l">최초납품예정일</th>
                                    <th scope="col" class="ta_l">최종납품예정일</th>
                                    <th scope="col" class="ta_r">S</th>
                               
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="result" items="${webList}" varStatus="status">
                                    <tr class="row" >
                                     <td class="col ord ta_l"><c:out value="${result.EBELN}"  default=""/></td>  
                                     <td class="col ord ta_l"><c:out value="${result.EBELP}"  default=""/></td>  
                                     <td class="col ord ta_l"><c:out value="${result.MATNR}"  default=""/></td>  
                                     <td class="col ord ta_l"><c:out value="${result.TXZ01}"  default=""/></td>  <!-- 자재내역 -->
                                     <td class="col sregqty ta_r"><fmt:formatNumber value="${result.MENGE}" pattern="#,###" /></td> <!-- 발주수량 -->
                                     <td class="col ord ta_l"><c:out value="${result.MEINS}"  default=""/></td>  
                                     
                                     <td class="col ord ta_l"><fmt:parseDate value="${result.EINDT}" var="aYmd" pattern="yyyyMMdd" />
                                     <fmt:formatDate value="${aYmd}" pattern="yyyy.MM.dd" /></td> 
                                         
                                     <td class="col sregqty ta_r"><fmt:formatNumber value="${result.NETPR}" pattern="#,###" /></td> <!-- 단가 -->
                                     
                                     <td class="col ord ta_l"><fmt:parseDate value="${result.SUPP_JUPDT}" var="aYmd" pattern="yyyyMMdd" />
                                     <fmt:formatDate value="${aYmd}" pattern="yyyy.MM.dd" /></td> 
                                     
                                     <td class="col ord ta_l" style="display:none;">
                                     <fmt:parseDate value="${result.SUPP_JUPDT}${result.SUPP_JUPTM}" var="aHms" pattern="yyyyMMddHHmmss"/>
                                     <fmt:formatDate value="${aHms}" pattern="HH:mm:ss" /></td> 
                                     
                                     <td class="col ord ta_l"><c:out value="${result.SUPP_F_IN_UNAME}" default=""/></td>  <!-- 공급업체 최종 변경자-->
                                     <td class="col sregqty ta_r"><fmt:formatNumber value="${result.END_MENGE}" pattern="#,###" /></td> 
                                   	
                                   	<td class="col ord ta_l"><fmt:parseDate value="${result.FNAPDT}" var="aYmd" pattern="yyyyMMdd" />
                                     <fmt:formatDate value="${aYmd}" pattern="yyyy.MM.dd" /></td> 
                                    
                                    <td class="col ord ta_l"><fmt:parseDate value="${result.ENAPDT}" var="aYmd" pattern="yyyyMMdd" />
                                     <fmt:formatDate value="${aYmd}" pattern="yyyy.MM.dd" /></td> 

									 <c:choose>
									 	 <c:when test = "${result.STATU == '1'}">
									 		<td class="col ord ta_l"><c:out value="N" default=""/></td>  
									 	</c:when>
									 	<c:otherwise>
									 		<td class="col ord ta_l"><c:out value="Y"  default=""/></td>  
									 	 </c:otherwise>
									 </c:choose>    
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            
                            
                            <!-- 버튼 -->
                            <!--div class="bbsB ta_r mt_20">
                                <ul class="btn_right">
                                    <li><span class="button medium"><a href="javascript:void(0)" id="btn_write">작성</a></span></li>
                                </ul>
                            </div-->
                            <!-- //버튼 -->

                            <!-- 페이징 -->
                            <div  id="PAGE_NAVI" class="col_paging">${pageNavigater}</div>
                            <!-- // 페이징 -->
                        </div>
                        <!-- //목록 -->


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
        $("#web").attr('class', 'current');
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
            ,maxDate: "+1Y" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
        });
        $("#order_dt_end").datepicker({
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

        $("#supply_req_dt").datepicker({
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
            ,minDate: "+7D" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
            ,maxDate: "+1Y" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
        });
        
        $("#supply_req_dt2").datepicker({
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
            ,minDate: "+7D" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
            ,maxDate: "+1Y" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
        });
        
        $("#receive_dt").datepicker({
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
            //,minDate: "+3D" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
            ,maxDate: "+1Y" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
        });

        $("a[name = 'pageMove']").unbind("click").click(function(e) {
            var comSubmit = new ComSubmit("searchForm");

            comSubmit.setUrl("/web");
            $("#nCurrpage").val($(this).attr("content_id"))
            comSubmit.submit();

        });

        $("#knumh").select2();

        
        
 		$('#web_SUPP_E_MOD_UNAME1').on('change',function(){
 			$('#web_SUPP_E_MOD_UNAME').val($('#web_SUPP_E_MOD_UNAME1').val());
 			$('#web_SUPP_F_IN_UNAME').val($('#web_SUPP_E_MOD_UNAME1').val());
        });
        
        $('#supply_req_qty').on('change',function(){
        	
            var val = $('#supply_req_qty').val();
            alert(val);
        });

        $('#supply_req_qty').number( true, 0 );

        $('#unit_price').on('change',function(){
            var val = $('#unit_price').val();
        });
        $('#unit_price').number( true, 0 );

        $('#total_price').on('change',function(){
            var val = $('#total_price').val();
        });
        $('#total_price').number( true, 0 );

        $("#supply_req_qty").on('keyup', function() {
            if ($('#knumh').select2('val').length == 0){
                alert("품번을 선택해주세요");
                $("#supply_req_qty").val("");
                return;
            }
            if ($('#unit_price').val().length > 0){
                var total_price = $("#supply_req_qty").val() * $('#unit_price').val();
                $('#total_price').val(total_price);
            }
        });

        $('#searchBtn').on("click", function(e){
            $("#pagemode").val("search");
            var comSubmit = new ComSubmit("searchForm");
            comSubmit.setUrl("/web");
            comSubmit.submit();
        });
        
        $('#btn_bos').on("click", function(e){
            $("#pagemode").val("search");
            $("#process_cd").val("ST_BOS");
            var comSubmit = new ComSubmit("searchForm");
            comSubmit.setUrl("/supply");
            comSubmit.submit();
        });
        
        $('#btn_all').on("click", function(e){
            $("#pagemode").val("search");
            $("#process_cd").val("");
            var comSubmit = new ComSubmit("searchForm");
            comSubmit.setUrl("/supply");
            comSubmit.submit();
        });
        
        $("#btnCancle").click(function() {
            $("#knumh").select2().val("").trigger("change");
            //$('#material_num').select2().empty();
            $("#order_dt").val("");
            $("#material_num").val("");
            $("#supply_req_qty").val("");
            $("#supply_req_dt").val("");
            $("#supply_place").val("");
            $("#unit_price").val("");
            $("#total_price").val("");
            $("#orderfor_key").val("");
            $("#place_key option:eq(0)").attr("selected", "selected");
            $("#place_key").attr("disabled",false);
            $("#processFlag").val("insert");
            $("#knumh").prop("disabled", false);
            $("#supply_req_dt").datepicker('option', 'disabled', false);
            $("#supply_req_qty").removeAttr("readonly");
            $("#supply_req_dt").removeAttr("readonly");
            $("#supply_place").removeAttr("readonly");

            $('#btnConfirm').attr('style', "display:none;");
            $('#btnRegister').attr('style', "display:;");
            $('#btnModify').attr('style', "display:none;");
            $('#btnDelete').attr('style', "display:none;");
            $('#btnCancle').attr('style', "display:none;");
        });

        $('#knumh').on('select2:select', function(e) {
            var value = e.params.data;

            var id = value.id;
            var text = value.text;
            var index = value.element.index;
            $("#material_num").val(text);
            fnDisplayPrice();
        });

        function fnDisplayPrice(){
            $("#registerForm").ajaxForm({
                type: 'POST',
                url: 'supply/detailMaterialNum',
                dataType: "json",
                enctype: "multipart/form-data",
                contentType: false,
                processData: false,
                timeout: 30000,
                success: function(result) {
                    if (result.status == 0) {
                        $("#unit_price").val(result.detailMaterialNum.unit_price);
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

        
        $("#tbllist tr td.col").click(function() {
        	var now = new Date();
        	var year = now.getFullYear();	// 연도
        	var month = (now.getMonth()+1).toString();	// 월
        	var date = (now.getDate()).toString();	// 일
        	var hours = (now.getHours()).toString();	// 시간
        	var minutes = (now.getMinutes()).toString();	// 분
        	var seconds = (now.getSeconds()).toString();	// 초
        	
            var tr = $(this).closest('tr');
            var td = tr.children();
            
			if (td.eq(14).text() == "Y" ){
				 alert("접수 완료건 입니다.");
				 return;
            }
            
     
			 
            $('#web_EBELN').val(td.eq(0).text());
            $('#web_EBELP').val(td.eq(1).text());
            $('#web_MATNR').val(td.eq(2).text());
            $('#web_TXZ01').val(td.eq(3).text());
            $('#web_MENGE').val(td.eq(4).text());
            $('#web_MEINS').val(td.eq(5).text());
            $('#web_EINDT').val(td.eq(6).text());
            $('#web_NETPR').val(td.eq(7).text());
            
            $('#web_SUPP_JUPDT').val(year + "." + (month[1] ? month : '0' + month[0]) + "." + (date[1] ? date : '0' + date[0])) ;
            $('#web_SUPP_JUPTM').val((hours[1] ? hours : '0' + hours[0]) + ":" + (minutes[1] ? minutes : '0' + minutes[0]) + ":"+(seconds[1] ? seconds : '0' + seconds[0]) );
            $('#web_SUPP_F_IN_UNAME').val($('#web_SUPP_E_MOD_UNAME1').val());
            $('#web_END_MENGE').val(td.eq(4).text());
            $('#web_SUPP_E_MOD_UNAME').val($('#web_SUPP_E_MOD_UNAME1').val());

        });

        function fnDisplayDetail(){

            $("#registerForm").ajaxForm({
                type: 'POST',
                url: '/supply/detailSupply',
                dataType: "json",
                enctype: "multipart/form-data",
                contentType: false,
                processData: false,
                timeout: 30000,
                success: function(result) {
                    if (result.status == 0) {
                        $("#knumh").prop("disabled", false);
                        $("#knumh").select2().val(result.supplyDetail.knumh).trigger("change");

                        $("#material_num").val(result.supplyDetail.material_num);
                        $("#order_dt").val(result.supplyDetail.order_dt);
                        $("#supply_req_qty").val(result.supplyDetail.supply_req_qty);
                        $("#supply_req_dt").val(result.supplyDetail.supply_req_dt);
                        $("#unit_price").val(result.supplyDetail.unit_price);
                        $("#total_price").val(result.supplyDetail.total_price);
                        $("#place_key").val(result.supplyDetail.place_key).prop("selected", true);
                        $("#place_key").attr("disabled",true);
                        var common_cd = result.supplyDetail.process.common_cd;
                        $("#processFlag").val("update");
                        if (common_cd == "ST_BOS") {
                            $("#knumh").prop("disabled", true);
                            $("#supply_req_qty").removeAttr("readonly");
                            $("#supply_req_dt").datepicker('option', 'disabled', false);
                            $("#supply_place").removeAttr("readonly");
                            $('#btnConfirm').attr('style', "display:none;");
                            $('#btnRegister').attr('style', "display:none;");
                            $('#btnModify').attr('style', "display:;");
                            $('#btnDelete').attr('style', "display:;");
                            $('#btnCancle').attr('style', "display:;");
                        } else if (common_cd == "ST_SPG") {
                            $("#knumh").attr("disabled", true);
                            $("#supply_req_qty").attr("readonly", true);
                            $("#supply_req_dt").datepicker('option', 'disabled', true);
                            $("#supply_place").attr("readonly", true);
                            //$('#receive').attr('style', "display:;");
                            //$("#receive_dt").datepicker('option', 'disabled', false);
                            $('#btnConfirm').attr('style', "display:none;");
                            $('#btnRegister').attr('style', "display:none;");
                            $('#btnModify').attr('style', "display:none;");
                            $('#btnDelete').attr('style', "display:none;");
                            $('#btnCancle').attr('style', "display:;");
                        }
                        else {
                            $("#knumh").prop("disabled", true);
                            $("#supply_req_dt").datepicker('option', 'disabled', false);
                            $("#supply_req_qty").removeAttr("readonly");
                            $("#supply_req_dt").removeAttr("readonly");
                            $("#supply_place").removeAttr("readonly");

                            $('#btnConfirm').attr('style', "display:none;");
                            $('#btnRegister').attr('style', "display:none;");
                            $('#btnModify').attr('style', "display:none;");
                            $('#btnDelete').attr('style', "display:none;");
                            $('#btnCancle').attr('style', "display:;");
                        }

                    }
                    else if (result.status == 1) {
                        alert(result.msg);
                        var comSubmit = new ComSubmit("searchForm");
                        comSubmit.setUrl("/login");
                        comSubmit.submit();
                    }
                },
                error: function(data, status, err) {
                    alert("로그인 시간이 종료되었습니다." + "\n" + "다시 로그인을 해주시기 바랍니다." + "\n"
                        + "code: " + data.status + "\n"
                        + "message :" + data.responseText + "\n"
                        + "error: " + err);
                    return false;
                }
            }).submit();
            
            $("#registerForm").ajaxForm({
                type: 'POST',
                url: '/supply/supplyHistory',
                dataType: "json",
                enctype: "multipart/form-data",
                contentType: false,
                processData: false,
                timeout: 30000,
                success: function(result) {
                    if (result.status == 0) {
                    	$("#boardList > tbody").empty();
                    	var results = result.supplyList;
                    	var str = '';
                        $.each(results , function(i){
                            str += '<TR><TD>' + results[i].process_nm + '</TD><TD>' + results[i].supply_dt + '</TD><TD class="ta_r">' + results[i].supply_qty.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</TD>';
                            str += '</TR>';
                       });
                       $("#boardList").append(str); 
                    }
                    else if (result.status == 1) {
                        alert(result.msg);
                        var comSubmit = new ComSubmit("searchForm");
                        comSubmit.setUrl("/login");
                        comSubmit.submit();
                    }
                },
                error: function(data, status, err) {
                    alert("로그인 시간이 종료되었습니다." + "\n" + "다시 로그인을 해주시기 바랍니다." + "\n"
                        + "code: " + data.status + "\n"
                        + "message :" + data.responseText + "\n"
                        + "error: " + err);
                    return false;
                }
            }).submit();
        }

        /* 등록 */
        $('#supplyRegister').click(function() {

        	var obj_length = document.getElementsByName("rtogubun").length;
        	var rtogubun;
        		for (var i=0; i<obj_length; i++) {
        	            if (document.getElementsByName("rtogubun")[i].checked == true) {
        	            	rtogubun = document.getElementsByName("rtogubun")[i].value;
        	            }
        	        }

        	  if ($('#web_SUPP_E_MOD_UNAME1').val().length == 0){
                  alert("접수자명을 입력해 주세요");
                  $("#web_SUPP_E_MOD_UNAME1").focus()
                  return;
              }
        	  
        	  if ($('#supply_req_dt').val().length == 0){
                  alert("최초납품예정일을 입력해주세요");
                  $("#supply_req_dt").focus()
                  return;
              }
        	  
        	  if ($('#supply_req_dt2').val().length == 0){
                  alert("최종납품예정일을 입력해주세요");
                  $("#supply_req_dt2").focus()
                  return;
              }

        	  if(rtogubun != "OFR"){
     			 alert("발주접수버튼을 체크해 주세요!");
     			 return;
     			}
        	  
            if (window.confirm("발주등록을 진행하시겠습니까?")) {
                $("#registerForm").ajaxForm({
                    type: 'POST',
                    url: "/web/insertweb",
                    dataType: "json",
                    enctype: "multipart/form-data",
                    contentType: false,
                    processData: false,
                    timeout: 30000,
                    success: function(result) {
                        if (result.status == 0) {
                            alert(result.msg);
                            var comSubmit = new ComSubmit("searchForm");
                            comSubmit.setUrl("/web");
                            comSubmit.submit();
                            //location.reload();

                        }
                        else if (result.status == 1) {
                            alert(result.msg);
                        }
                    },
                    error: function(data, status, err) {
                        alert("서버가 응답하지 않습니다." + "\n" + "다시 시도해주시기 바랍니다.");
                    }
                }).submit();
            }
        });

        $('#supplyModify').click(function() {
            var supply_req_qty = $('#supply_req_qty').val();
            if ($('#supply_req_qty').val().length == 0){
                supply_req_qty = "0";
            }
            if (supply_req_qty == "0"){
                alert("수량을 입력해주세요");
                $("#supply_req_qty").focus()
                return;
            }
            if ($('#supply_req_dt').val().length == 0){
                alert("납품요청일을 입력해주세요");
                $("#supply_req_dt").focus()
                return;
            }
            $("#processFlag").val("update");

            if (window.confirm("발주수정을 진행하시겠습니까?")) {
                $("#registerForm").ajaxForm({
                    type: 'POST',
                    url: "/supply/updateSupply",
                    dataType: "json",
                    enctype: "multipart/form-data",
                    contentType: false,
                    processData: false,
                    timeout: 30000,
                    success: function(result) {
                        if (result.status == 0) {
                            alert(result.msg);
                            var comSubmit = new ComSubmit("searchForm");
                            comSubmit.setUrl("/supply");
                            comSubmit.submit();

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
        });

        $('#receiveConfirm').click(function() {
            $("#processFlag").val("confirm");

            if (window.confirm("발주내역에 대한 수령확정을 진행하시겠습니까?")) {
                $("#registerForm").ajaxForm({
                    type: 'POST',
                    url: "/supply/updateSupplyRecive",
                    dataType: "json",
                    enctype: "multipart/form-data",
                    contentType: false,
                    processData: false,
                    timeout: 30000,
                    success: function(result) {
                        if (result.status == 0) {
                            alert(result.msg);
                            var comSubmit = new ComSubmit("searchForm");
                            comSubmit.setUrl("/supply");
                            comSubmit.submit();

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
        });

        $('#supplyDelete').click(function() {

            $("#processFlag").val("delete");

            if (window.confirm("발주등록내역을 삭제하시면 복구가 되지 않습니다.\r\n정말로 발주 등록 내역을 삭제하시겠습니까?")) {
                $("#registerForm").ajaxForm({
                    type: 'POST',
                    url: "/supply/deleteSupply",
                    dataType: "json",
                    enctype: "multipart/form-data",
                    contentType: false,
                    processData: false,
                    timeout: 30000,
                    success: function(result) {
                        if (result.status == 0) {
                            alert(result.msg);
                            $("#nCurrpage").val("1");
                            var comSubmit = new ComSubmit("searchForm");
                            comSubmit.setUrl("/supply");
                            comSubmit.submit();

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
        });

    });

</script>
</body>
</html>