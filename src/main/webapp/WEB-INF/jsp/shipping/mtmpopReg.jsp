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
    <script src="/jscript/select2.min.js"></script>
    <script src="/jscript/jquery.number.js" charset="utf-8"></script>

    <link rel="stylesheet" type="text/css" href="/css/style.css" />
    <link rel="stylesheet" type="text/css" href="/css/table.css" />
    <link href="/css/select2.min.css" rel="stylesheet" />
</head>
<body>
<div id="contentmain">
    <div id="wrappop">
        <!-- container -->
        <div id="container">
            <div id="contentpop">
                <h2><span class="blind">본문 영역</span></h2>
                <div id="content_title">
                    <h3>출하정보 - MTM등록 </h3>
                </div>
                <div id="content_pop">

                    <!-- 글쓰기 -->
                    <fieldset>
                        <div class="content_subtitle">
                            <h3> &nbsp;&nbsp;▶ 출하정보 </h3>
                        </div>
                        <table cellspacing="0" border="1" summary="목록정보" cellpadding="0" id="tbllist" class="bbsL" style="max-width:945px;align:center;">
                            <caption>게시판 목록</caption>
                            <colgroup>
                                <col width="160px" />
                                <col width="150px" />
                                <col width="90px" />
                                <col width="100px" />
                                <col width="80px" />
                                <col width="80px" />
                                <col width="100px" />
                                <col width="90px" />
                            </colgroup>
                            <thead>
                            <tr>
                                <th scope="col" style="text-align:left">업체명</th>
                                <th scope="col" style="text-align:left">자재코드</th>
                                <th scope="col" style="text-align:right">납품확정수량</th>
                                <th scope="col">출하일</th>
                                <th scope="col" style="text-align:right">잔여수량</th>
                                <th scope="col" style="background-color:#b2b2b2;text-align:right">가용재고</th>
                                <th scope="col" style="background-color:#b2b2b2;text-align:right">MTM가용재고</th>
                                <th scope="col" style="background-color:#b2b2b2;text-align:right">생산오더수량</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td style="text-align:left">${shippingDetail.company_nm}</td>
                                <td style="text-align:left">${shippingDetail.material_num}</td>
                                <td style="text-align:right">100,000</td>
                                <td>2020.02.28</td>
                                <td style="text-align:right">100,000</td>
                                <td style="text-align:right">100,000</td>
                                <td style="text-align:right">100,000</td>
                                <td style="text-align:right">0</td>
                            </tr>

                            </tbody>
                        </table>
                        <div class="content_subtitle">
                            <h3> &nbsp;&nbsp;▶ MTM등록정보 </h3>
                        </div>
                        <table cellspacing="0" border="1" summary="목록정보" cellpadding="0" id="tbllist" class="bbsL" style="max-width:945px;align:center;">
                            <caption>게시판 목록</caption>
                            <colgroup>
                                <col width="60px" />
                                <col width="100px" />
                                <col width="150px" />
                                <col width="250px" />
                                <col width="150px" />
                                <col width="100px" />
                                <col width="0" />
                                <col width="0" />
                                <col width="0" />
                                <col width="0" />
                            </colgroup>
                            <thead>
                            <tr>
                                <th scope="col">선택</th>
                                <th scope="col">등록일자</th>
                                <th scope="col">변경전 저장위치</th>
                                <th scope="col" style="text-align:left">변경자재코드</th>
                                <th scope="col">변경후 저장위치</th>
                                <th scope="col" style="text-align:right">수정수량</th>
                                <th scope="col" style="display:none">업체코드</th>
                                <th scope="col" style="display:none">품번</th>
                                <th scope="col" style="display:none">출하일자</th>
                                <th scope="col" style="display:none">출하순번</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><input type="checkbox" name="delflag" /></td>
                                <td>2020.02.28</td>
                                <td>1107</td>
                                <td style="text-align:left">SILZKR8E8G/03</td>
                                <td>1108</td>
                                <td style="text-align:right">100,000</td>
                                <td scope="col" style="display:none">B0021A02</td>
                                <td scope="col" style="display:none">SILZKR8E8G/02ASDFG</td>
                                <td scope="col" style="display:none">2020.02.28</td>
                                <td scope="col" style="display:none">1</td>
                            </tr>

                            </tbody>
                        </table>
                        <!-- 버튼 -->
                        <div class="bbsB ta_r mt_10" style="max-width:945px;">
                            <ul class="btn_right">
                                <li><span class="button medium"><a href="#this" id="btnMTMRegAll">MTM등록완료</a></span></li>
                                <li><span class="button medium"><a href="#this" id="btnMTMDelete">MTM삭제</a></span></li>
                                <li><span class="button medium"><a href="void(0)" onclick="javascript:self.close()">닫기</a></span></li>
                            </ul>
                        </div>
                        <div class="content_subtitle">
                            <h3> &nbsp;&nbsp;▶ MTM등록 </h3>
                        </div>

                        <form id="frm" name="frm" enctype="multipart/form-data">
                            <div class="bbsL_WRITE_box">
                                <table class="bbsL_WRITE">
                                    <!-- 헤드부 -->
                                    <thead>
                                    <caption> 출하 상세내역 </caption>
                                    <colgroup>
                                        <col width="110" />
                                        <col width="200" />
                                        <col width="110" />
                                        <col width="200" />
                                    </colgroup>
                                    </thead>
                                    <!-- 바디부 -->
                                    <tbody>
                                    <tr>
                                        <th scope="row">변경전저장위치</th>
                                        <td><input maxlength="10" type="text" id="pre_storage_loc" name="pre_storage_loc" size="20" class="input_s1" value='' ></td>
                                        <th scope="row">변경품번</th>
                                        <td>
                                            <select name="modi_material_num" id="modi_material_num" style="width:153px;">
                                                <option value="">품번선택</option>
                                                <option value="SILZKR8E8G/02">SILZKR8E8G/02</option>
                                                <option value="BPR6ES/09">BPR6ES/09</option>
                                                <option value="DCPR7E/04">DCPR7E/04</option>
                                                <option value="SILZKR8E8G/02">SILZKR8E8G/02</option>
                                                <option value="SILZKR8E9G/03">SILZKR8E9G/03</option>
                                                <option value="PLZKAR6A-11/18">PLZKAR6A-11/18</option>
                                                <option value="SILZKR7B11/12">SILZKR7B11/12</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">변경후저장위치</th>
                                        <td><input maxlength="10" type="text" id="storage_loc" name="storage_loc" size="20" class="input_s1" value='' ></td>
                                        <th scope="row">변경수량</th>
                                        <td><input maxlength="10" type="text" id="modi_qty" name="modi_qty" size="20" class="input_s1" style="text-align: center;" value='' ></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </form>
                        <!-- 버튼 -->
                        <div class="bbsB ta_r mt_20">
                            <ul class="btn_right">
                                <li><span class="button medium"><a href="#this" id="btnMTMReg">MTM등록</a></span></li>
                            </ul>
                        </div>
                        <!-- //버튼 -->
                    </fieldset>
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
<script type="text/javascript">
    $(document).ready(function() {

        $("#order_dt_start").datepicker({
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

        $("#modi_material_num").select2();
        $('#modi_qty').number( true, 0 );
    });


</script>
</body>
</html>