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
    String localPageTitle = " > 납품처관리";
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
                    <h3>납품처 등록 및 관리 </h3>
                </div>
                <div id="content_start">
                    <div class="formleft">
                        <!-- 내용 -->
                        <div class="content_subtitle">
                            <h3> </h3>
                        </div>
                        <!-- 글쓰기 -->
                        <fieldset>
                            <form id="registerForm" name="registerForm" enctype="multipart/form-data">
                                <input type="hidden" id="company_cd" name="company_cd" value="${pageParam.company_cd}"/>
                                <input type="hidden" id="place_key" name="place_key" value=""/>
                                <div class="bbsL_WRITE_box">
                                    <table class="bbsL_WRITE">
                                        <!-- 헤드부 -->
                                        <thead>
                                        <caption> 납품처 등록 </caption>
                                        <colgroup>
                                            <col width="80" />
                                            <col width="230" />
                                        </colgroup>
                                        </thead>
                                        <!-- 바디부 -->
                                        <tbody>
                                        <tr>
                                            <th scope="row">납품처</th>
                                            <td><input maxlength="15" type="text" id="place_nm" name="place_nm" size="29" class="input_s1" value='' ></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">남품처주소</th>
                                            <td><input maxlength="70" type="text" id="place_addr" name="place_addr" size="29" class="input_s1" value='' ></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">사용여부</th>
                                            <td scope="row">
                                                <select name="use_fl" id="use_fl" class="select_r1" style="width:215px">
                                                    <option value="1" selected>사용</option>
                                                    <option value="0">미사용</option>
                                                </select>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </form>
                            <!-- 버튼 -->
                            <div class="bbsB ta_r mt_20">
                                <ul class="btn_right">
                                    <li id="btnRegister"><span class="button medium"><a href="javascript:void(0)" id="commonRegister">등록</a></span></li>
                                    <li id="btnModify" style="display:none;"><span class="button medium"><a href="javascript:void(0)" id="commonModify">수정</a></span></li>
                                    <li id="btnCancle" style="display:none;"><span class="button medium"><a href="javascript:void(0)" id="commonCancle">취소</a></span></li>
                                </ul>
                            </div>
                            <!-- //버튼 -->
                        </fieldset>

                        <!-- //내용 -->
                    </div>
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
                                        <label for="schuser_fl" class="blind1">사용여부</label>
                                        <select name="schuser_fl" id="schuser_fl" class="select_r1">
                                            <option value="">전체</option>
                                            <option value="1" <c:if test="${pageParam.schuser_fl == '1'}">selected="selected"</c:if>>사용</option>
                                            <option value="0" <c:if test="${pageParam.schuser_fl == '0'}">selected="selected"</c:if>>미사용</option>
                                        </select>
                                        <input type="image" src="/images/board/btn_schok.gif"  id="searchBtn" alt="검색" title="검색" class="btn_schok" />

                                    </fieldset>
                                </form>
                            </div>
                            <!-- //검색 / 상단 -->

                            <!-- 목록 -->
                            <table cellspacing="0" border="1" summary="목록정보"  cellpadding="0" id="tbllist" class="bbsL">
                                <caption>게시판 목록</caption>
                                <colgroup>
                                    <col width="150px" />
                                    <col width="250px" />
                                    <col width="100px" />
                                    <col width="0" />
                                    <col width="0" />
                                </colgroup>
                                <thead>
                                <tr>
                                    <th scope="col">납품처</th>
                                    <th scope="col" class="ta_l">납품처 주소</th>
                                    <th scope="col">사용여부</th>
                                    <th scope="col" style="display:none">납품처키</th>
                                    <th scope="col" style="display:none">사용여부</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="result" items="${placeList}" varStatus="status">
                                <tr class="row">
                                    <td class="placeNm "><c:out value="${result.place_nm}"  default=""/></td>
                                    <td class="ta_l placeAddr"><c:out value="${result.place_addr}"  default=""/></td>
                                    <td><c:out value="${result.use_nm}"  default=""/></td>
                                    <td  class="placeKey" style="display:none"><c:out value="${result.place_key}"  default=""/></td>
                                    <td  class="useFl" style="display:none"><c:out value="${result.use_fl}"  default=""/></td>
                                </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <!-- //목록 -->

                            <!-- 페이징 -->
                            <div  id="PAGE_NAVI" class="col_paging">
                                    ${pageNavigater}
                            </div>
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
        $(document).prop('title', '${pageTitle} ${localPageTitle}');
        $("#menuplace").attr('class','current');

        $("a[name = 'pageMove']").unbind("click").click(function(e) {
            var comSubmit = new ComSubmit("searchForm");

            comSubmit.setUrl("/supply/listPlace");
            $("#nCurrpage").val($(this).attr("content_id"));
            comSubmit.submit();

        });

        $('#searchBtn').on("click", function(e){
            $("#pagemode").val("search");
            var comSubmit = new ComSubmit("searchForm");
            comSubmit.setUrl("/supply/listPlace");
            comSubmit.submit();
        });

        $("#tbllist tr").click(function() {
            // 현재 클릭된 Row(<tr>)
            var tr = $(this);
            var td = tr.children();

            // td.eq(index)를 통해 값 호출
            var placeNm = td.eq(tr.find(".placeNm").index()).text();
            var placeAddr = td.eq(tr.find(".placeAddr").index()).text();
            var useFl = td.eq(tr.find(".useFl").index()).text();
            var placeKey = td.eq(tr.find(".placeKey").index()).text();
            $("#place_key").val(placeKey);
            $("#place_nm").val(placeNm);
            $("#place_addr").val(placeAddr);

            $("#use_fl").val(useFl).prop("selected", true);
            $("#place_nm").attr("disabled",true);

            $('#btnRegister').attr('style', "display:none;");
            $('#btnModify').attr('style', "display:;");
            $('#btnCancle').attr('style', "display:;");
        });

        $("#btnCancle").click(function() {
            $("#use_fl option:eq(0)").prop("selected", true);
            $("#place_key").val("");
            $("#place_nm").val("");
            $("#place_addr").val("");
            $("#place_nm").removeAttr("disabled");
            $('#btnRegister').attr('style', "display:;");
            $('#btnModify').attr('style', "display:none;");
            $('#btnCancle').attr('style', "display:none;");
        });

        $("#btnRegister").click(function() {
            if ($("#place_nm").val().length < 1)
            {
                alert("납품처를 입력해주세요.");
                $("#place_nm").focus();
                return;
            }
            if ($("#place_addr").val().length < 1)
            {
                alert("납품처 주소를 입력해주세요.");
                $("#place_addr").focus();
                return;
            }

            if (window.confirm("납품처를 등록하시겠습니까?")) {
                $("#registerForm").ajaxForm({
                    type: 'POST',
                    url: "/supply/insertPlace",
                    dataType: "json",
                    enctype: "multipart/form-data",
                    contentType: false,
                    processData: false,
                    timeout: 30000,
                    success: function(result) {
                        if (result.status == 1) {
                            alert(result.msg);
                            var comSubmit = new ComSubmit("searchForm");
                            comSubmit.setUrl("/supply/listPlace");
                            comSubmit.submit();

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

        $("#btnModify").click(function() {
            if ($("#place_addr").val().length < 1)
            {
                alert("납품처 주소를 입력해주세요.");
                $("#place_addr").focus();
                return;
            }
            if (window.confirm("납품처를 수정하시겠습니까?")) {
                $("#registerForm").ajaxForm({
                    type: 'POST',
                    url: "/supply/updatePlace",
                    dataType: "json",
                    enctype: "multipart/form-data",
                    contentType: false,
                    processData: false,
                    timeout: 30000,
                    success: function(result) {
                        if (result.status == 1) {
                            alert(result.msg);
                            var comSubmit = new ComSubmit("searchForm");
                            comSubmit.setUrl("/supply/listPlace");
                            comSubmit.submit();
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
    });

</script>
</body>
</html>