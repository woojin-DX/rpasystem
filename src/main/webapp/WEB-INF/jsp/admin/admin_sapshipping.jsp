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
    String localPageTitle = " > 수동출하정보";
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
                    <h3>수동출하정보 </h3>
                </div>
                <div id="content_start">
                    <!-- 내용 -->
                    <div class="listleftall">
                    <div class="bbsL_box">
                    	<form id="registerForm" name="registerForm" enctype="multipart/form-data">
                            <input type="hidden" id="shipping_arr" name="shipping_arr" value="" />
                        </form>
                        <!-- 검색 / 상단 -->
                        <div class="bbsS_TOP">
                            <form id="searchForm" name="searchForm" method="post">
                                <input type="hidden" id="pagemode" name="pagemode" value="${pageParam.pagemode}" />
                                <input type="hidden" id="nCurrpage" name="nCurrpage" value="${pageParam.nCurrpage}" />
                                <fieldset>
                                    <legend>검색</legend>
                                    <label for="supply_dt_start" class="blind1">출하기간</label>
                                    <input size="10" maxlength="8" type="text" id="supply_dt_start" name="supply_dt_start"  value='${pageParam.supply_dt_start}' > ~
                                    <input size="10" maxlength="8" type="text" id="supply_dt_end" name="supply_dt_end"  value='${pageParam.supply_dt_end}' >
                                    <label for="coperation_cd" class="blind1">발주처</label>
                                    <select name="coperation_cd" id="coperation_cd" class="select_r1">
                                        <option value="">전체</option>
                                        <c:forEach var="companyList" items="${companyList}" varStatus="status">
                                            <option value="${companyList.company_cd}" <c:if test="${pageParam.coperation_cd == companyList.company_cd}">selected="selected"</c:if>>${companyList.company_nm}</option>
                                        </c:forEach>
                                    </select>
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
                        <table cellspacing="0" border="1" summary="목록정보" cellpadding="0" id="tbllist" class="bbsLAll" max-width="1900px" >
                            <caption>게시판 목록</caption>
                            <colgroup>
                            	<col width="50px" />
                                <col width="80px" />
                                <col width="80px" />
                                <col width="200px" />
                                <col width="150px" />
                                <col width="80px" />
                                <col width="150px" />
                                <col width="70px" />
                                <col width="70px" />
                                <col width="70px" />
                                <col width="130px" />
                                <col width="80px" />
                                <col width="80px" />
                                <col width="80px" />
                                <col width="0" />
                                <col width="0" />
                                <col width="0" />
                            </colgroup>
                            <thead>
                            <tr>
                            	<th scope="col" rowspan="2" nowrap="nowrap" ><input name="checkAll" class="checkBtn" id="checkAll" type="checkbox"></th>
                                <th scope="col" rowspan="2" >상태</th>
                                <th scope="col" rowspan="2" >출하일</th>
                                <th scope="col" rowspan="2" class="ta_l">업체명</th>
                                <th scope="col" rowspan="2" class="ta_l">자재코드</th>
                                <th scope="col" colspan="3" style="background-color:#b2b2b2;">SAP 출하정보</th>
                                <th scope="col" colspan ="7">시판아이트 정보</th>
                                <th scope="col" rowspan="2" style="display:none">업체코드</th>
                                <th scope="col" rowspan="2" style="display:none">발주일</th>
                                <th scope="col" rowspan="2" style="display:none">품번</th>
                            </tr>
                            <tr>
                                <th scope="col" style="background-color:#b2b2b2;">출하일자</th>
                                <th scope="col" style="background-color:#b2b2b2;">자재코드</th>
                                <th scope="col" class="ta_r" style="background-color:#b2b2b2;">출하수량</th>
                                <th scope="col">자재유형</th>
                                <th scope="col">포장단위</th>
                                <th scope="col" class="ta_l">납품처</th>
                                <th scope="col" class="ta_r">출하수량</th>
                                <th scope="col">포장방법</th>
                                <th scope="col">출하방법</th>
                                <th scope="col">포장여부</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="result" items="${shippingList}" varStatus="status">
                                <tr bgcolor="#${result.trbgcolor}" >
                                	<td nowrap="nowrap" ><input type="checkbox"  id="checkOne${status.count}" name="checkOne" class="checkBtn" value="${result.shipping_key}"></td>
                                    <td><c:out value="${result.process_nm}"  default=""/></td>
                                    <td><c:out value="${result.supply_dt}"  default=""/></td>
                                    <td class="ta_l"><c:out value="${result.company_nm}"  default=""/></td>
                                    <td class="ta_l"><c:out value="${result.material_num}"  default=""/></td>
                                    <td style="color:#FF0000;"><c:out value="${result.sap_req_dt}"  default=""/></td>
                                    <td style="color:#FF0000;"><c:out value="${result.sap_material_code}"  default=""/></td>
                                    <td class="ta_r" style="color:#FF0000;"><fmt:formatNumber value="${result.sap_qty}" pattern="#,###" /></td>
                                    <td><c:out value="${result.mtart_nm}"  default=""/></td>
                                    <td><c:out value="${result.bstrf}"  default=""/></td>
                                    <td class="ta_l"><c:out value="${result.supply_place}"  default=""/></td>
                                    <c:choose>
                                        <c:when test = "${result.supply_qty == ''}">
                                            <td class="ta_r"><c:out value="${result.supply_qty}"  default=""/></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td class="ta_r"><fmt:formatNumber value="${result.supply_qty}" pattern="#,###" /></td>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test = "${result.packing_cd == 'OM_MTM'}">
                                            <td class="link mtm"><c:out value="${result.packing_nm}"  default=""/></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><c:out value="${result.packing_nm}"  default=""/></td>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test = "${pagecng == 'CFMLIST'}">
                                            <td><input type="text" id="shipping_method${status.count}" name="shipping_method" content_id="<c:out value="${result.shipping_key}"  default=""/>" value="<c:out value="${result.shipping_method}"  default=""/>" /></td>
		                                    <c:choose>
		                                        <c:when test = "${result.packing_flag == 'Y'}">
		                                            <td><input type="checkbox" id="packing_flag${status.count}" checked name="packing_flag" value="<c:out value="${result.shipping_key}"  default=""/>"/></td>
		                                        </c:when>
		                                        <c:otherwise>
			                                        <c:choose>
				                                        <c:when test = "${result.packing_cd == 'OM_MPM' || result.packing_cd == 'OM_MTM' || result.packing_cd == 'OM_PKG'}">
				                                            <td><input type="checkbox" id="packing_flag${status.count}" name="packing_flag" value="<c:out value="${result.shipping_key}"  default=""/>"/></td>
				                                        </c:when>
				                                        <c:otherwise>
				                                            <td> </td>
				                                        </c:otherwise>
				                                    </c:choose>
		                                        </c:otherwise>
		                                    </c:choose>
                                        </c:when>
                                        <c:otherwise>
                                            <td><c:out value="${result.shipping_method}"  default=""/></td>
	                                        <c:choose>
		                                        <c:when test = "${result.packing_cd == 'OM_MPM' || result.packing_cd == 'OM_MTM' || result.packing_cd == 'OM_PKG'}">
		                                            <td><c:out value="${result.packing_flag}"  default=""/></td>
		                                        </c:when>
		                                        <c:otherwise>
		                                            <td> </td>
		                                        </c:otherwise>
		                                    </c:choose>
                                        </c:otherwise>
                                    </c:choose>
                                    
                                    <td scope="col" style="display:none" class="comp"><c:out value="${result.orderfor_key}"  default=""/></td>
                                    <td scope="col" style="display:none" class="item"><c:out value="${result.material_num}"  default=""/></td>
                                    <td scope="col" style="display:none" class="ord"><c:out value="${result.order_dt}"  default=""/></td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                        <!-- //목록 -->
                        <!-- 버튼 -->
                        <div class="bbsB ta_l mt_10">
                            <ul class="btn_all">
                                <li id="btnShpping"><span class="button medium"><a href="javascript:void(0)" id="shippingConfirm">출하등록</a></span>&nbsp;&nbsp;</li>
                            </ul>
                        </div>

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
        $("#shippingpsv").attr('class', 'current');

        $("#supply_dt_start").datepicker({
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
        $("#supply_dt_end").datepicker({
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

        $("#material_list").select2();

        $("a[name = 'pageMove']").unbind("click").click(function(e) {
            var comSubmit = new ComSubmit("searchForm");
            if ("${userRole}" == "ADMIN" && "${pagecng}" == "CFMLIST") {
                comSubmit.setUrl("/admin/cfmlist");
            }
            else if ("${userRole}" == "ADMIN" && "${pagecng}" == "SPGLIST") {
                comSubmit.setUrl("/admin/spglist");
            }
            else {
                comSubmit.setUrl("/shipping");
            }
            $("#nCurrpage").val($(this).attr("content_id"))
            comSubmit.submit();

        });

        $("#tbllist tr td.link.mtm").click(function() {
            var tr = $(this).closest('tr');
            var td = tr.children();
            var orderfor_key = td.eq(tr.find(".comp").index()).text();
            var order_dt = td.eq(tr.find(".ord").index()).text();
            var item_num = td.eq(tr.find(".item").index()).text();
            var param = {'orderfor_key':orderfor_key,'material_num':item_num}
            if ("${userRole}" == "ADMIN"){
            	setPostPopupWin("/admin/mtmpop",param,"mtmpop",0,0,960,500,'auto');
            }
            else{
            	setPostPopupWin("/shipping/mtmpop",param,"mtmpop",0,0,860,500,'auto');
            }
        });

        $('#searchBtn').on("click", function(e){
            $("#pagemode").val("search");
            var comSubmit = new ComSubmit("searchForm");
            comSubmit.setUrl("/admin/psvlist");
            comSubmit.submit();
        });

        $('#shippingConfirm').on("click", function(e){
        	var checkCnt = $("input:checkbox[name='checkOne']:checked").length;
            var items=[]; $('input[name="checkOne"]:checkbox:checked').each(function(){
                items.push($(this).val());
            });
            var tmp = items.join(',');

            if (checkCnt == 0){
                alert("수동출하할 항목을 선택해주세요.");
                return false;
            }
            $("#shipping_arr").val(tmp);
            if (window.confirm("수동출하를 진행하시겠습니까?")) {
                $("#registerForm").ajaxForm({
                    type: 'POST',
                    url: "/admin/multiUpdateShippingSupply",
                    dataType: "json",
                    enctype: "multipart/form-data",
                    contentType: false,
                    processData: false,
                    timeout: 30000,
                    success: function(result) {
                        if (result.status == 0) {
                            alert(result.msg);
                            var comSubmit = new ComSubmit("searchForm");
                            comSubmit.setUrl("/admin/psvlist");
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
        
        $('input[name=shipping_method]').keydown(function(key) {
        	if (key.keyCode == 13) {
        		if ($(this).val().trim().length == 0){
        			alert("출하방법을 입력하세요");
        			$(this).val("");
        		}
        		else{
        			$.ajax({
                        type : "POST",
                        url : "/shipping/updateShipping_method",
                        data : {"shipping_key": $(this).attr("content_id"),"shipping_method":$(this).val()},
                        dataType : "json",
                        error : function(error) {
                            alert("서버가 응답하지 않습니다. \n다시 시도해주시기 바랍니다.");
                        },
                        success : function(result) {
                        	if (result.status == 0) {
                                alert(result.msg);
                            }
                            else if (result.status == 1) {
                                alert(result.msg);
                                $(this).val("");
                            }
                        },
                        error: function(data, status, err) {
                            alert("서버가 응답하지 않습니다." + "\n" + "다시 시도해주시기 바랍니다." + "\n"
                                + "code: " + data.status + "\n"
                                + "message1 : " + status + "\n"
                                + "error: " + err);
                        }
                    });

        		}

        	}
       	});
        
        $("input[name=packing_flag]").change(function(){
			var packing_flag = "";
            if($(this).is(":checked")){
            	packing_flag = "Y";

            }else{
            	packing_flag = "N";

            }
            $.ajax({
                type : "POST",
                url : "/shipping/updateShipping_method",
                data : {"shipping_key": $(this).val(),"packing_flag":packing_flag},
                dataType : "json",
                error : function(error) {
                    alert("서버가 응답하지 않습니다. \n다시 시도해주시기 바랍니다.");
                },
                success : function(result) {
                	if (result.status == 0) {
                        alert(result.msg);
                    }
                    else if (result.status == 1) {
                        alert(result.msg);
                        $(this).val("");
                    }
                },
                error: function(data, status, err) {
                    alert("서버가 응답하지 않습니다." + "\n" + "다시 시도해주시기 바랍니다." + "\n"
                        + "code: " + data.status + "\n"
                        + "message1 : " + status + "\n"
                        + "error: " + err);
                }
            });

        });
        
        window.onload = function() { 
        	var table = [];
        	var table_count = 1;
        	var k = 0;
        	$("#tbllist tbody").find("tr").each(function(i,v) {
        		table.push($(v).find("td:nth-child(5)").text());
        	})
        	for(var i=0; i<table.length; i++) {
        	  for(var j=i+1; j<table.length; j++) {
        	    if((table[i] == table[j]) ) {
        	      table_count +=1;
        	      k+=1;
        	    }
        	  }
        	  $("#tbllist tbody").find("tr")[i].children[5].setAttribute("rowspan", table_count);
        	  $("#tbllist tbody").find("tr")[i].children[6].setAttribute("rowspan", table_count);
        	  $("#tbllist tbody").find("tr")[i].children[7].setAttribute("rowspan", table_count);
        	  
        	  if(table_count > 1) {
        	    for(var q = 1; q < table_count; q++) {
        	      $("#tbllist tbody").find("tr")[i+q].children[5].style.display = "none";
        	      $("#tbllist tbody").find("tr")[i+q].children[6].style.display = "none";
        	      $("#tbllist tbody").find("tr")[i+q].children[7].style.display = "none";
        	    }
        	  }
        	  table_count = 1;
        	  i=k;
        	  k++;
        	}
		};
		
		$("[name=checkAll]").click(function(){
			allCheckFunc( this );
		});
		$("[name=checkOne]").each(function(){
			$(this).click(function(){
				oneCheckFunc( $(this) );
			});
		});
        	

    });
    
    
    function allCheckFunc( obj ) {
		//$("[name=checkOne]").prop("checked", $(obj).prop("checked") );
		if( $(obj).prop("checked") )
		{
			var chkValue = new Array;
			
			$('input:checkbox[name="checkOne"]').each(function() {
				var splitDATA = $(this).val();
				chkValue.push(splitDATA)
			});
			
			var chkValueFnr = new Array;
			$('input:checkbox[name="checkOne"]').each(function() {
				var splitSubDATA = $(this).val();
				if ($.inArray(splitSubDATA, chkValueFnr) == -1){
					$(this).prop("checked", true);
					chkValueFnr.push(splitSubDATA[0]);
				}
				else{
					$(this).prop("checked", false);
				}
	
			});
		}
		else{
			$("[name=checkOne]").prop("checked", $(obj).prop("checked") );
		}
		
		checkBoxLength = $("[name=checkOne]").length;
		checkedLength = $("[name=checkOne]:checked").length;

		if( checkBoxLength == checkedLength ) {
			$("[name=checkAll]").prop("checked", true);
		} else {
			$("[name=checkAll]").prop("checked", false);
		}
		
	}
	
	/* 체크박스 체크시 전체선택 체크 여부 */
	function oneCheckFunc( obj )
	{
		var allObj = $("[name=checkAll]");
		var objName = $(obj).attr("name");
		
		var chkValue = new Array;
		
		$('input:checkbox[name="checkOne"]').each(function() {

			if ($(this).prop("checked") && $(this).attr("id") != $(obj).attr("id")){
				var splitDATA = $(this).val().split("-");
				chkValue.push(splitDATA[0])
			}
		});
		
	
		if( $(obj).prop("checked") )
		{
			var splitSubDATA = $(obj).val().split("-");
			var checkResult = splitSubDATA[0];
			if (splitSubDATA.length > 1){
				checkResult = splitSubDATA[0] + "-" + splitSubDATA[1];
			}
			if ($.inArray(checkResult, chkValue) > -1){
				alert("동일한 조건유형은 하나만 선택가능합니다.");
				$(obj).prop("checked", false);
				return;
			}
			
			checkBoxLength = $("[name="+ objName +"]").length;
			checkedLength = $("[name="+ objName +"]:checked").length;
	
			if( checkBoxLength == checkedLength ) {
				allObj.prop("checked", true);
			} else {
				allObj.prop("checked", false);
			}
		}
		else
		{
			allObj.prop("checked", false);
		}
	}

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