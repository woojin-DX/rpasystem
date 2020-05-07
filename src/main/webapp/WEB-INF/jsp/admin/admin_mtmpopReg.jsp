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

                        <table cellspacing="0" border="1" summary="목록정보" cellpadding="0" id="tbllist" class="bbsL" style="align:center;">
                            <caption>게시판 목록</caption>
                            <colgroup>
                                <col width="150px" />
                                <col width="150px" />
                                <col width="90px" />
                                <col width="100px" />
                                <col width="80px" />
                                <col width="80px" />
                                <col width="100px" />
                                <col width="90px" />
                                <col width="0" />
                            </colgroup>
                            <thead>
                            <tr>
                                <th scope="col" class="col ta_c">업체명</th>
                                <th scope="col" class="col ta_c">자재코드</th>
                                <th scope="col" class="col ta_r">납품확정수량</th>
                                <th scope="col" class="col ta_c">출하일</th>
                                <th scope="col" class="col ta_r">잔여수량</th>
                                <th scope="col" class="col ta_r">가용재고</th>
                                <th scope="col" class="col ta_r">MTM가용재고</th>
                                <th scope="col" class="col ta_r">생산오더수량</th>
                                <th scope="col" style="display:none">이전수량</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td class="col ta_l">${shippingDetail.company_nm}</td>
                                <td class="col ta_l">${shippingDetail.material_num}</td>
                                <c:choose>
                                    <c:when test = "${confirm_qty == ''}">
                                        <td class="col ta_r"><c:out value="${confirm_qty}"  default=""/></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td class="col ta_r"><fmt:formatNumber value="${confirm_qty}" pattern="#,###" /></td>
                                    </c:otherwise>
                                </c:choose>
                                <td class="col ta_c">${supply_dt}</td>
                                <c:choose>
                                    <c:when test = "${shippingDetail.confirm_qty == ''}">
                                        <td class="col ta_r"><c:out value="${shippingDetail.confirm_qty}"  default=""/></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td class="col ta_r"><fmt:formatNumber value="${shippingDetail.confirm_qty}" pattern="#,###" /></td>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test = "${shippingDetail.remaininvenqty == ''}">
                                        <td class="col ta_r"><c:out value="${shippingDetail.remaininvenqty}"  default=""/></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td class="col ta_r"><fmt:formatNumber value="${shippingDetail.remaininvenqty}" pattern="#,###" /></td>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test = "${shippingDetail.remainmtmqty == ''}">
                                        <td class="col ta_r"><c:out value="${shippingDetail.remainmtmqty}"  default=""/></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td class="col ta_r"><fmt:formatNumber value="${shippingDetail.remainmtmqty}" pattern="#,###" /></td>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test = "${shippingDetail.prod_order_qty == ''}">
                                        <td class="col ta_r"><c:out value="${shippingDetail.prod_order_qty}"  default=""/></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td class="col ta_r"><fmt:formatNumber value="${shippingDetail.prod_order_qty}" pattern="#,###" /></td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>

                            </tbody>
                        </table>

                        <div class="content_subtitle">
                            <h3> &nbsp;&nbsp;▶ MTM등록 </h3>
                        </div>

                        <form id="regForm" name="regForm" method="post">
                            <input type="hidden" id="confirm_qty" name="confirm_qty" value="${shippingDetail.remainallqty}" />

                            <div class="bbsL_WRITE_box">
                                <table class="bbsL_WRITE">
                                    <!-- 헤드부 -->
                                    <thead>
                                    <caption> 출하 상세내역 </caption>
                                    <colgroup>
                                        <col width="130" />
                                        <col width="200" />
                                        <col width="130" />
                                        <col width="100" />
                                        <col width="100" />
                                    </colgroup>
                                    <thead>
                                    <tr>
                                        <th scope="col" class="col ta_c">변경자재코드</th>
                                        <th scope="col" class="col ta_c">MTM가용재고</th>
                                        <th scope="col" class="col ta_c">변경전저장위치</th>
                                        <th scope="col" class="col ta_c">변경수량</th>
                                        <th scope="col" class="col ta_c">변경후저장위치</th>
                                    </tr>
                                    </thead>
                                    <!-- 바디부 -->
                                    <tbody>
                                    <c:forEach var="result" items="${mtmMatrialList}" varStatus="status">
                                        <input type="hidden" id="modi_meterial_num${status.count}" name="modi_meterial_num" value="${result.modi_meterial_num}" />
                                        <input type="hidden" id="pre_storage_loc${status.count}" name="pre_storage_loc" value="${result.pre_storage_loc}" />
                                        <input type="hidden" id="original_qty${status.count}" name="original_qty" value="${result.lastmmt_qty}" />
                                        <input type="hidden" id="oldmodi_qty${status.count}" name="oldmodi_qty" value="${result.modi_qty}" />
                                    <tr>
                                        <td class="col ta_c"><c:out value="${result.modi_meterial_num}"  default=""/></td>
                                        <td class="col ta_r"><fmt:formatNumber value="${result.original_qty}" pattern="#,###" /></td>
                                        <td class="col ta_c"><c:out value="${result.pre_storage_loc}"  default=""/></td>
                                        <c:choose>
                                            <c:when test = "${result.modi_qty == ''}">
                                                <td class="col ta_c"><input maxlength="10" type="text" id="modi_qty${status.count}" name="modi_qty" size="20" class="input_s1 ta_r"  value='' ></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td class="col ta_c"><input maxlength="10" type="text" id="modi_qty${status.count}" name="modi_qty" size="20" class="input_s1 ta_r"  value='${result.modi_qty}' ></td>
                                            </c:otherwise>
                                        </c:choose>
                                        <td class="col ta_c"><input maxlength="10" type="text" id="storage_loc${status.count}" name="storage_loc" size="20" class="input_s1 ta_c" value='${result.storage_loc}' ></td>
                                    </tr>
                                    </c:forEach>
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
        $("#btnMTMReg").click(function() {
            var modi_meterial_num = $('input[name=modi_qty]').val();
            var grpl = $("input[name=modi_qty]").length;
            //배열 생성
            var mqtyarr = new Array();
            var moriqtyarr = new Array();
            var mitemarr = new Array();
            var mlocarr = new Array();
            var mplocarr = new Array();
            var morioldqtyarr = new Array();
            //배열에 값 주입
            var n = 0;
            var nTot = 0;
            var regExp = /[\{\}\[\]\/?.,;:₩|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi

            for(var i=0; i<grpl; i++) {

                if ($("input[name=modi_qty]").eq(i).val().length > 0 ) {
                    mqtyarr[n] = $("input[name=modi_qty]").eq(i).val().replace(regExp, "");
                    mitemarr[n] = $("input[name=modi_meterial_num]").eq(i).val();
                    mlocarr[n] = $("input[name=storage_loc]").eq(i).val();
                    mplocarr[n] = $("input[name=pre_storage_loc]").eq(i).val();
                    moriqtyarr[n] = $("input[name=original_qty]").eq(i).val().replace(regExp, "");
                    morioldqtyarr[n] = $("input[name=oldmodi_qty]").eq(i).val().replace(regExp, "");
                    var oldmty =0;
                    if ($("input[name=oldmodi_qty]").eq(i).val().length > 0){
                        oldmty = parseInt($("input[name=oldmodi_qty]").eq(i).val());
                    }

                    if (parseInt($("input[name=modi_qty]").eq(i).val()) > (parseInt($("input[name=original_qty]").eq(i).val())+oldmty)){
                        alert("MTM가용 수량보다 입력값이 큽니다")
                        return;
                    }
                    nTot += parseInt($("input[name=modi_qty]").eq(i).val());
                    if (nTot > parseInt($("#confirm_qty").val())){
                        alert("잔여수량보다 큰값을 입력할 수 없습니다.")
                        return;

                    }

                    n++
                }
            }
            $("#modi_mtm_item", opener.document).val(mitemarr); //jquery 이용
            $("#modi_mtm_loc", opener.document).val(mlocarr); //jquery 이용
            $("#modi_mtm_qty", opener.document).val(mqtyarr); //jquery 이용
            $("#modi_mtm_ploc", opener.document).val(mplocarr); //jquery 이용
            $("#supplymtm_qty", opener.document).val(nTot); //jquery 이용
            self.close();
        });

    });


</script>
</body>
</html>