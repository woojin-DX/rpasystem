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
                    <div class="listleft">
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
                                    <label for="supply_month" class="blind1">출하기간</label>
                                    <input size="10" maxlength="8" type="text" id="supply_month" name="supply_month"  value='${pageParam.supply_month}' >
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
                        <table cellspacing="0" border="1" summary="목록정보" max-width="1300px" cellpadding="0" id="tbllist" class="bbsL">
                            <caption>게시판 목록</caption>
                            <colgroup>
                                <col width="60px" />
                                <col width="80px" />
                                <col width="200px" />
                                <col width="150px" />
                                <col width="90px" />
                                <col width="80px" />
                                <col width="80px" />
                                <col width="0" />
                                <col width="0" />
                            </colgroup>
                            <thead>
                            <tr>
                                <th scope="col" nowrap="nowrap" ><input name="checkAll" class="checkBtn" id="checkAll" type="checkbox"></th>
                                <th scope="col">출하일자</th>
                                <th scope="col" class="ta_l">업체명</th>
                                <th scope="col" class="ta_l">자재코드</th>
                                <th scope="col" class="ta_r">출하수량</th>
                                <th scope="col" class="ta_r">단가</th>
                                <th scope="col" class="ta_r">금액</th>
                                <th scope="col" style="display:none">출하키</th>
                                <th scope="col" style="display:none">발주키</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="result" items="${supplyList}" varStatus="status">
                                <tr>
                                    <td nowrap="nowrap" >
                                          <input type="checkbox"  id="checkOne${status.count}" name="checkOne" class="checkBtn" value="${result.shipping_key}">
                                    </td>
                                    <td class="ta_c"><c:out value="${result.supply_dt}"  default=""/></td>
                                    <td class="ta_l"><c:out value="${result.company_nm}"  default=""/></td>
                                    <td class="ta_l"><c:out value="${result.material_num}"  default=""/></td>
                                    <td class="ta_r"><fmt:formatNumber value="${result.supply_qty}" pattern="#,###" /></td>
                                    <td class="ta_r"><fmt:setLocale value="ko_KR"/><fmt:formatNumber value="${result.unit_price}" type="currency" /></td>
                                    <td class="ta_r"><fmt:setLocale value="ko_KR"/><fmt:formatNumber value="${result.total_price}" type="currency" /></td>
                                    <td scope="col" style="display:none" class="comp"><c:out value="${result.shipping_key}"  default=""/></td>
                                    <td scope="col" style="display:none" class="item"><c:out value="${result.orderfor_key}"  default=""/></td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                        <!-- //목록 -->
                        <!-- 버튼 -->
                        <div class="bbsB ta_r mt_10">
                            <ul class="btn_all">
                                <li  class="li_right"><span class="button medium"><a href="javascript:void(0)" id="shippingConfirm">납품확정</a></span>&nbsp;&nbsp;</li>
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
        $("#supplyconfirm").attr('class', 'current');

        $("#supply_month").datepicker({
            dateFormat: 'yy.mm' //Input Display Format 변경
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

        $('#searchBtn').on("click", function(e){
            $("#pagemode").val("search");
            var comSubmit = new ComSubmit("searchForm");
            comSubmit.setUrl("/supply/confirm");
            comSubmit.submit();
        });

        $('#shippingConfirm').on("click", function(e){
            var checkCnt = $("input:checkbox[name='checkOne']:checked").length;
            var items=[]; $('input[name="checkOne"]:checkbox:checked').each(function(){
                items.push($(this).val());
            });
            var tmp = items.join(',');

            if (checkCnt == 0){
                alert("납품확정할 항목을 선택해주세요.");
                return false;
            }
            $("#shipping_arr").val(tmp);
            if (window.confirm("납품확정을 진행하시겠습니까?")) {
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
                            comSubmit.setUrl("/supply/confirm");
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

        $("[name=checkAll]").click(function(){
            allCheckFunc( this );
        });
        $("[name=checkOne]").each(function(){
            $(this).click(function(){
                oneCheckFunc( $(this) );
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
                    if ($.inArray(splitSubDATA, chkValue) > -1){
                       $(this).prop("checked", true);
                        chkValueFnr.push(splitSubDATA);
                    }
                    else{
                        $(this).prop("checked", true);
                        chkValueFnr.push(splitSubDATA);
                    }

                });
            }
            else{
                $("[name=checkOne]").prop("checked", $(obj).prop("checked") );
            }

            var checkBoxLength = $("[name=checkOne]").length;
            var checkedLength = $("[name=checkOne]:checked").length;

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

                var checkBoxLength = $("[name="+ objName +"]").length;
                var checkedLength = $("[name="+ objName +"]:checked").length;

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

    });

</script>
</body>
</html>