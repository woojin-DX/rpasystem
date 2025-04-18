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
    String localPageTitle = " > 납품문서 재발행";
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
                    <h3>▶ 납품문서 발행 </h3>
                </div>
               
                <div id="content_start">
                
                
                        <!-- 글쓰기 -->
                        <fieldset>
                            <form id="registerForm" name="registerForm" enctype="multipart/form-data">
                             	<input type="hidden" id="deliverydoc_arr" name="deliverydoc_arr" value="" />
                                <input type="hidden" id="company_cd" name="company_cd" value="${infoParam.company_cd}" />
                                <input type="hidden" id="user_id" name="user_id" value="${infoParam.user_id}" />
                                <input type="hidden" id="orderfor_key" name="orderfor_key" value="" />
                                <input type="hidden" id="order_dt" name="order_dt" value="${pageParam.order_dt}" />
                                <input type="hidden" id="material_num" name="material_num" value="" />
                                <input type="hidden" id="processFlag" name="processFlag" value="insert" />  
                                <input type="text" style="width:0px; display: none;" />
                            </form>
                        </fieldset>

                    <div class="listleft">
                        <!-- 내용 -->
                        <!-- 목록 -->
                        <div class="bbsL_box_Reissue">
                            <!-- 검색 / 상단 -->
                            <div class="bbsCom_TOP">
                                <form id="searchForm" name="searchForm">
                                	<!-- <span class="button large" ><a href="javascript:void(0)" id="btn_bos">&nbsp;&nbsp;&nbsp;대기&nbsp;&nbsp;&nbsp;</a></span>
                                	<span class="button large"><a href="javascript:void(0)" id="btn_all">&nbsp;&nbsp;&nbsp;전체&nbsp;&nbsp;&nbsp;</a></span> -->
                                	<%-- <label style="font-size:20px;font-weight:bold;">공급 업체명 : ${infoParam.company_nm}</label> --%>
                                    <input type="text" style="width:0px; visibility: hidden;">
                                    <input type="hidden" id="pagemode" name="pagemode" value="${pageParam.pagemode}" />
                                    <input type="hidden" id="nCurrpage" name="nCurrpage" value="${pageParam.nCurrpage}" />
                              	
                                    <fieldset>
                                        <legend>검색</legend>
                              			<label for="order_dt_start" class="blind1"></label>
                                    	<input size="10" maxlength="8" type="text" id="order_dt_start" name="order_dt_start"  value='${pageParam.order_dt_start}' style="display:none" > 
                                    	<input size="10" maxlength="8" type="text" id="order_dt_end" name="order_dt_end"  value='${pageParam.order_dt_end}' style="display:none">
                                		<label style="font-size:15px;font-weight:bold">납품문서번호 : </label>
                                		<label for="schword" class="blind">검색어 입력</label>
                                        <input type="text" name="schword" id="schword" title="검색어를 입력해주세요" class="input_sch" style="width:150px" placeholder="검색어를 입력해주세요" value="${pageParam.schword}" />
                                		<input type="image" src="/images/board/btn_schok.gif"  id="searchBtn" alt="검색" title="검색" class="btn_schok" />
	 									<input type="button"  value="납품문서 발행" id="searchBtn2" class="btn_schok" /> 
                                   		<input type="button"  value="납품문서 취소" id="searchBtn3" class="btn_schok" /> 
                                   		<input type="button"  value="웹라벨" id="searchBtn4" class="btn_schok" /> 
                                   		<input type="text" style="display:none" name="DELVN_NUM" id="DELVN_NUM" tclass="input_sch" style="width:150px" value=""  />
                                 </fieldset>
                                </form>
                                
                            </div>
                            <!-- //검색 / 상단 -->
                            
                            <table cellspacing="0" border="1" summary="목록정보"  cellpadding="0" id="tbllist" class="bbsCom2">
                                <caption>게시판 목록</caption>
                                <colgroup>
                                	<col width="50" />    	<!-- 확인 -->
                                	<col width="120px" />   <!-- 납품문서 번호 -->
                                    <col width="160px" />	<!-- 납품문서 항목번호 -->
                                    <col width="120px" />   <!-- 구매오더번호 -->
                                    <col width="160px" />	<!-- 품목번호 -->
                                    <col width="170px" />	<!-- 자재코드 -->
                                    <col width="450px" />  	<!-- 자재내역 -->
                                    <col width="100px" />	<!-- 발주수량 -->
                                    <col width="50px" />	<!-- 단위 -->
                                    <col width="100px" />	<!-- 납품요청일 -->
                                    <col width="120px" />	<!-- 납품예정수량-->
                                   	<col width="90px" />	<!-- 납품수량-->
                                    <col width="100px" />	<!-- 납품예정일-->
                                    <col width="100px" />	<!-- 총납품수량-->
                                    <col width="100px" />	<!-- 남은수량-->
                                    <col width="0" />
                                </colgroup>
                                <thead>
                                <tr>
                                  
                                	<th scope="col" nowrap="nowrap">확인</th>
                                	<th scope="col" class="ta_l">납품문서 번호</th>
                                	<th scope="col" class="ta_l">납품문서 항목번호</th>
                                   	<th scope="col" class="ta_l">구매오더 번호</th>
                                    <th class="ta_l" scope="col">구매오더 품목번호</th>
                                    <th class="ta_l" scope="col">자재코드</th>
                                    <th scope="col" class="ta_c">자재내역</th>
                                    <th scope="col" class="ta_r">발주수량</th>
                                    <th scope="col" class="ta_l">단위</th>
                                    <th scope="col" class="ta_l">납품예정일</th>
                                    <th scope="col" class="ta_r">납품예정수량</th>
                                    
                                    <th scope="col" class="ta_r">납품수량</th>
                                    <th scope="col" class="ta_r">납품일</th>
                                    <th scope="col" class="ta_r">총납품수량</th>
                                    <th scope="col" class="ta_r">남은수량</th>
                                    
                                    <th scope="col" class="ta_r" style="display:none">클라이언트</th>
                                    <th scope="col" class="ta_r" style="display:none" >공급업체</th>
                                    <th scope="col" class="ta_r" style="display:none">수정장</th>
                                    <th scope="col" class="ta_r" style="display:none">수정자ID</th>
                                        

                                </tr>
                                </thead>
                                <tbody>
                              
                                <c:forEach var="result" items="${DeliveryDocList}" varStatus="status">
                                    <tr class="row" >
                                     
                                     <td nowrap="nowrap" >
                                          <input type="checkbox"  id="checkOne${status.count}" name="checkOne" class="checkBtn" value="${result.DELVN}:${result.DELVS}:${result.EBELN}:${result.EBELP}:${result.MENGE}:${result.ibgo_menge}:${result.ebeln_menge}">
                                     </td>    
                                     <!-- <th class="col ord ta_l"><input name="checkAll" class="checkBtn" id="checkAll" type="checkbox" ></th> -->
                                     <td class="col ord ta_l"><c:out value="${result.DELVN}"  default=""/></td>  	<!-- 납품문서 번호 -->
                                     <td class="col ord ta_c"><c:out value="${result.DELVS}"  default=""/></td>  	<!-- 납품문서 항목번호 -->
                                     <td class="col ord ta_l"><c:out value="${result.EBELN}"  default=""/></td>  	<!-- 구매오더번호 -->
                                     <td class="col ord ta_c"><c:out value="${result.EBELP}"  default=""/></td>  	<!-- 품목번호 -->
                                     <td class="col ord ta_l"><c:out value="${result.MATNR}"  default=""/></td>  	<!-- 자재코드 -->
                                     <td class="col ord ta_l"><c:out value="${result.TXZ01}"  default=""/></td>  	<!-- 자재내역 -->
                                     <td class="col sregqty ta_r"><fmt:formatNumber value="${result.ebeln_menge}" pattern="#,###" /></td> <!-- 발주수량 -->
                                     <td class="col ord ta_l"><c:out value="${result.MEINS}"  default=""/></td>  	<!-- 단위 -->
                                  
                                     <td class="col ord ta_l"><fmt:parseDate value="${result.EINDT}" var="aYmd" pattern="yyyyMMdd" /> <!-- 납품요청일 -->
                                     <fmt:formatDate value="${aYmd}" pattern="yyyy.MM.dd" /></td> 
                                     
                                     <td class="col sregqty ta_r"><fmt:formatNumber value="${result.ebeln_menge}" pattern="#,###" /></td> <!-- 납품예정수량 -->
   
                                     <td class="col sregqty ta_r"><fmt:formatNumber value="${result.MENGE}" pattern="#,###" /></td> <!-- 납품수량 -->  
                                     <td class="col ord ta_r"><fmt:parseDate value="${result.NAP_PRE_DT}" var="aHms" pattern="yyyyMMdd"/> <!-- 납품예정일-->
                                     <fmt:formatDate value="${aHms}" pattern="yyyy.MM.dd" /></td>       
                                     <td class="col sregqty ta_r"><fmt:formatNumber value="${result.menge_tot}" pattern="#,###" /></td> <!-- 총 납품수량 -->
                                     <td class="col sregqty ta_r"><fmt:formatNumber value="${result.ibgo_menge}" pattern="#,###" /></td> <!-- 남은수량 -->               
                                                                     
                                     <td scope="col" class="col ord ta_l" style="display:none"><c:out value="${result.MANDT}"  default=""/>
                                     <td scope="col" class="col ord ta_l" style="display:none"><c:out value="${result.LIFNR}"  default=""/>
                                     <td scope="col" class="col ord ta_l" style="display:none"><c:out value="${result.UNAME}"  default=""/>
                                     <td scope="col" class="col ord ta_l" style="display:none"><c:out value="${result.FIN_ID}"  default=""/>
                                     
                                <%-- 	<td scope="col" class="orderfor_key" style="display:none"><c:out value="${result.shpping.orderfor_key}"  default=""/></td> --%>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>

                            <!--div class="bbsB ta_r mt_20">
                                <ul class="btn_right">
                                    <li><span class="button medium"><a href="javascript:void(0)" id="btn_write">작성</a></span></li>
                                </ul>
                            </div>
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
        $("#ReissueDeliveryDoc").attr('class', 'current');
    
       /*   $("#order_dt_start").datepicker({
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
        }); */

        $("a[name = 'pageMove']").unbind("click").click(function(e) {
            var comSubmit = new ComSubmit("searchForm");

            comSubmit.setUrl("/web/ReissueDeliveryDoc");
            $("#nCurrpage").val($(this).attr("content_id"))
            comSubmit.submit();

        });

        $("#knumh").select2();

 		$('#DeliveryDoc_SUPP_E_MOD_UNAME1').on('change',function(){
 			$('#DeliveryDoc_SUPP_E_MOD_UNAME').val($('#DeliveryDoc_SUPP_E_MOD_UNAME1').val());
 			$('#DeliveryDoc_SUPP_F_IN_UNAME').val($('#DeliveryDoc_SUPP_E_MOD_UNAME1').val());
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
            comSubmit.setUrl("/web/ReissueDeliveryDoc");
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

            //$('#DELVN_NUM').val(td.eq(1).text().trim());
			$('#DeliveryDoc_DELVN').val(td.eq(1).text().trim());
			$('#DeliveryDoc_DELVS').val(td.eq(2).text().trim());
			
            $('#DeliveryDoc_EBELN').val(td.eq(3).text().trim());
            $('#DeliveryDoc_EBELP').val(td.eq(4).text().trim());
            $('#DeliveryDoc_MATNR').val(td.eq(5).text().trim());
            $('#DeliveryDoc_TXZ01').val(td.eq(6).text().trim());
            $('#DeliveryDoc_ebeln_menge').val(td.eq(7).text().trim());
            $('#DeliveryDoc_MEINS').val(td.eq(8).text().trim());
            $('#DeliveryDoc_EINDT').val(td.eq(9).text().trim());
            $('#DeliveryDoc_END_MENGE').val(td.eq(10).text());					/* 납품예정수량	*/
            $('#DeliveryDoc_MENGE_tot').val(td.eq(13).text()); 					/* 총납품수량	*/

            $('#DeliveryDoc_MENGE').val(td.eq(11).text().trim()); 				/* 납품수량	*/
            $('#DeliveryDoc_ibgo_menge').val(td.eq(14).text().trim()); 			/* 남은수량	*/
            $('#DeliveryDoc_NAP_PRE_DT').val(year + "." + (month[1] ? month : '0' + month[0]) + "." + (date[1] ? date : '0' + date[0])) ;
    
            $('#DeliveryDoc_MANDT').val(td.eq(15).text().trim()); 				/* 클라이언트	*/
            $('#DeliveryDoc_LIFNR').val(td.eq(16).text().trim()); 				/* 공급업체	*/
            $('#DeliveryDoc_UNAME').val(td.eq(17).text().trim()); 				/* 생성인	*/
            $('#DeliveryDoc_FIN_ID').val(td.eq(18).text().trim()); 				/* 생성인ID	*/
            $('#DeliveryDoc_ibgo_menge_r').val(td.eq(14).text().trim()); 		/* 잔고	*/
            
        
            if ($('#DeliveryDoc_DELVN').val().length > 0){
				 alert("납품문서 처리된 항목입니다.");
				 return;
         	}

			if ($('#DeliveryDoc_DELVS').val().length > 0){
				 alert("납품문서 처리 대기중인 항목 입니다.");
				 return;
          	}
			
			
            if ($('#DeliveryDoc_END_MENGE').val() == $('#DeliveryDoc_MENGE_tot').val()){
       		 alert("납품완료된 항목입니다.!!");
       		 return;
       	  }
        
            
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

        

     /*  납품문서 처리*/
         $('#searchBtn1').click(function() {
        	 
        	 var checkCnt = $("input:checkbox[name='checkOne']:checked").length;
             var items=[]; 
             $('input[name="checkOne"]:checkbox:checked').each(function(){
            	 items.push($(this).val());
             });

             var tmp = items.join(',');
             
             
             alert(tmp);

              if(tmp.indexOf("::") && tmp.indexOf(":A:")){
         		 alert("납품문서 처리된 항목이 포함되어 있습니다.");
         		return ;
         	} 

        	 if (checkCnt == 0){
                 alert("납품문서 처리할 항목을 선택해주세요.");
                 return false;
             }
        	 
        	 $("#deliverydoc_arr").val(tmp);
        	 if (window.confirm("납품문서 처리를 진행하시겠습니까?")) {
                 $("#registerForm").ajaxForm({
                     type: 'POST',
                     url: "/web/updateDeliveryDoc",
                     dataType: "json",
                     enctype: "multipart/form-data",
                     contentType: false,
                     processData: false,
                     timeout: 30000,
                     success: function(result) {
                         if (result.status == 0) {
                             alert(result.msg);
                             var comSubmit = new ComSubmit("searchForm");
                             comSubmit.setUrl("/web/ReissueDeliveryDoc");
                             comSubmit.submit();
                             //location.reload();

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
        
   		/*납품문서 발행  */
         $('#searchBtn2').click(function() { 
             if ($('#DELVN_NUM').val().length <= 0){
				 alert("납품문서 발행할 항목을 선택해주세요!");
				 return;
         	}

             
             var comSubmit = new ComSubmit("searchForm");
             comSubmit.setUrl("/web/resheetExcel");
             comSubmit.submit(); 
         });
         
   		
   		/* 체크박스 비교 */
         $('.checkBtn').on('change',function(){
            var tr = $(this).closest('tr');
            var td = tr.children();
            $('#DELVN_NUM').val(td.eq(1).text().trim());
          
        	 $('input[name="checkOne"]:checkbox').each(function(){	 
        		if (td.eq(1).text().trim()  ==  this.value.substring(0,this.value.indexOf(":"))){
        			this.checked = true;
        			
        		}else {
        		 this.checked = false;
        	 	}
             });
         });

         $('#searchBtn4').click(function() {
        	 window.open('http://210.123.24.231:8080/Account/Logon ', '_blank'); 

        	
         });
         
         /*  납품문서 취소*/
         $('#searchBtn3').click(function() {
        	
        	 var checkCnt = $("input:checkbox[name='checkOne']:checked").length;
             var items=[]; 
             $('input[name="checkOne"]:checkbox:checked').each(function(){
            	 items.push($(this).val());
             });

             var tmp = items.join(',');
             
            
             if(tmp.indexOf("::") >= 0){
         		 alert("납품문서 취소 처리가 안된 항목이 포함되어 있습니다.");
         		return ;
         	}

        	 if (checkCnt == 0){
                 alert("납품문서 취소 처리할 항목을 선택해주세요.");
                 return false;
             }
        	 
        	 $("#deliverydoc_arr").val(tmp);
        	 if (window.confirm("납품문서 취소 처리를 진행하시겠습니까?")) {
                 $("#registerForm").ajaxForm({
                     type: 'POST',
                     url: "/web/updateReissueDeliveryDoc",
                     dataType: "json",
                     enctype: "multipart/form-data",
                     contentType: false,
                     processData: false,
                     timeout: 30000,
                     success: function(result) {
                         if (result.status == 0) {
                             alert(result.msg);
                             var comSubmit = new ComSubmit("searchForm");
                             comSubmit.setUrl("/web/ReissueDeliveryDoc");
                             comSubmit.submit();
                             //location.reload();

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
         
        
        /* 납품확정 */
        $('#supplyRegister').click(function() {
        	var obj_length = document.getElementsByName("rtogubun").length;
        	var rtogubun;
        		for (var i=0; i<obj_length; i++) {
        	            if (document.getElementsByName("rtogubun")[i].checked == true) {
        	            	rtogubun = document.getElementsByName("rtogubun")[i].value;
        	            }
        	        }
   
        		
        		if ($('#DeliveryDoc_EBELN').val().length == 0){
   				 alert("항목을 선택해 주세요.");
   				 return;
             	 }
        		
        		
        		 if ($('#DeliveryDoc_DELVS').val().length > 0){
             		 alert("이미 납품확정한 항목입니다..!!");
             		 return;
             	  }
        		 
        		 if ($('#DeliveryDoc_END_MENGE').val() == $('#DeliveryDoc_MENGE_tot').val()){
             		 alert("납품완료된 항목입니다.!!");
             		 return;
             	  }
        		  
        	  	if ($('#DeliveryDoc_ibgo_menge').val() == 0){
                     alert("수량을 입력해주세요!");
                     $("#DeliveryDoc_ibgo_menge").focus()
                     return;
                 } 

        	  if ($('#DeliveryDoc_ibgo_menge_r').val().replace(",","") > $('#DeliveryDoc_ibgo_menge').val().replace(",","")){
           		  alert("발주수량 보다 많습니다");
           	   	  $("#DeliveryDoc_ibgo_menge").focus()
           		  return;
           	  } 
	

        	  if(rtogubun != "OFR"){
     			 alert("납품확정 버튼을 체크해 주세요!");
     			 return;
     			}
        	  
            if (window.confirm("납품확정을 진행하시겠습니까?")) {
                $("#registerForm").ajaxForm({
                    type: 'POST',
                    url: "/web/insertDeliveryDoc",
                    dataType: "json",
                    enctype: "multipart/form-data",
                    contentType: false,
                    processData: false,
                    timeout: 30000,
                    success: function(result) {
                        if (result.status == 0) {
                            alert(result.msg);
                            var comSubmit = new ComSubmit("searchForm");
                            comSubmit.setUrl("/web/ReissueDeliveryDoc");
                            comSubmit.submit();

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