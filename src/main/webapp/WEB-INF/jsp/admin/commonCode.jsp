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
    String localPageTitle = " > 공통코드관리";
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
                    <h3>공통코드관리 </h3>
                </div>
                <div id="content_start">
                    <div class="formleft">
                        <!-- 내용 -->
                        <div class="content_subtitle">
                            <h3> &nbsp;&nbsp;▶ 코드등록 </h3>
                        </div>
                        <!-- 글쓰기 -->
                        <fieldset>
                            <form id="registerForm" name="registerForm" enctype="multipart/form-data">
                                <input type="hidden" id="common_cd" name="common_cd" />
                                <input type="hidden" id="division_nm" name="division_nm" />
                                <input type="hidden" id="old_seq" name="old_seq" />
                                <div class="bbsL_WRITE_box">
                                    <table class="bbsL_WRITE">
                                        <!-- 헤드부 -->
                                        <thead>
                                        <caption> 공통코드 등록 </caption>
                                        <colgroup>
                                            <col width="110" />
                                            <col width="200" />
                                        </colgroup>
                                        </thead>
                                        <!-- 바디부 -->
                                        <tbody>
                                        <tr>
                                            <th scope="row">구분</th>
                                            <td scope="row">
                                                <select name="division_cd" id="division_cd" class="select_r1">
                                                    <option value="">전체</option>
                                                    <option value="ST">진행상태</option>
                                                    <option value="OM">포장방법</option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">세부코드</th>
                                            <td><input maxlength="3" type="text" id="status_cd" name="status_cd" size="20" class="input_s1" value='' ></td>
                                        </tr>
                                        <tr>
                                            <th scope="row">세부코드명</th>
                                            <td><input maxlength="20" type="text" id="status_nm" name="status_nm" size="20" class="input_s1" value='' ></td>
                                        </tr>
                                        <tr id="status" style="display: none;">
                                            <th scope="row">정렬순서</th>
                                            <td><input maxlength="20" type="text" id="status_seq" name="status_seq" size="20" class="input_s1 ta_r" value='' ></td>
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
                                    <li id="btnDelete" style="display:none;"><span class="button medium"><a href="javascript:void(0)" id="commonDelete">삭제</a></span></li>
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
                                        <label for="srhDivision_cd" class="blind1">코드그룹구분</label>
                                        <select name="srhDivision_cd" id="srhDivision_cd" class="select_r1">
                                            <option value="">전체</option>
                                            <option value="ST" <c:if test="${pageParam.srhDivision_cd == 'ST'}">selected="selected"</c:if>>진행상태</option>
                                            <option value="OM" <c:if test="${pageParam.srhDivision_cd == 'OM'}">selected="selected"</c:if>>포장방법</option>
                                        </select>
                                        <label for="schword" class="blind">검색어 입력</label>
                                        <input type="text" name="schword" id="schword" title="검색어를 입력해주세요" class="input_sch" style="width:150px" placeholder="검색어를 입력해주세요" value="${pageParam.schword}" />
                                        <input type="image" src="/images/board/btn_schok.gif"  id="searchBtn" alt="검색" title="검색" class="btn_schok" />

                                    </fieldset>
                                </form>
                            </div>
                            <!-- //검색 / 상단 -->

                            <!-- 목록 -->
                            <table cellspacing="0" border="1" summary="목록정보"  cellpadding="0" id="tbllist" class="bbsL">
                                <caption>게시판 목록</caption>
                                <colgroup>
                                    <col width="100px" />
                                    <col width="100px" />
                                    <col width="150px" />
                                    <col width="150px" />
                                    <col width="100px" />
                                    <col width="200px" />
                                    <col width="0" />
                                </colgroup>
                                <thead>
                                <tr>
                                    <th scope="col">구분코드</th>
                                    <th scope="col">구분명</th>
                                    <th scope="col">세부코드</th>
                                    <th scope="col">코드명</th>
                                    <th scope="col">정렬순서</th>
                                    <th scope="col">등록일자</th>
                                    <th scope="col" style="display:none">공통코드</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="result" items="${commonCodeList}" varStatus="status">
                                <tr class="row">
                                    <td><c:out value="${result.division_cd}"  default=""/></td>
                                    <td><c:out value="${result.division_nm}"  default=""/></td>
                                    <td><c:out value="${result.status_cd}"  default=""/></td>
                                    <td><c:out value="${result.status_nm}"  default=""/></td>
                                    <td><c:out value="${result.status_seq}"  default=""/></td>
                                    <td><c:out value="${result.modify_dt}"  default=""/></td>
                                    <td scope="col" style="display:none"><c:out value="${result.common_cd}"  default=""/></td>
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

        $("#commoncode").attr('class','current');

        $("a[name = 'pageMove']").unbind("click").click(function(e) {
            var comSubmit = new ComSubmit("searchForm");

            comSubmit.setUrl("/admin/commoncode");
            $("#nCurrpage").val($(this).attr("content_id"));
            comSubmit.submit();

        });

        $('#searchBtn').on("click", function(e){
            $("#pagemode").val("search");
            var comSubmit = new ComSubmit("searchForm");
            comSubmit.setUrl("/admin/commoncode");
            comSubmit.submit();
        });

        $("#tbllist tr").click(function() {
            // 현재 클릭된 Row(<tr>)
            var tr = $(this);
            var td = tr.children();

            // td.eq(index)를 통해 값 호출
            var common_cd = td.eq(6).text();
            var division_cd = td.eq(0).text();
            var status_cd = td.eq(2).text();
            var status_nm = td.eq(3).text();
            var status_seq = td.eq(4).text();
            $("#common_cd").val(common_cd);
            $("#status_cd").val(status_cd);
            $("#status_nm").val(status_nm);
            $("#status_seq").val(status_seq);
            $("#old_seq").val(status_seq);

            $("#division_cd").val(division_cd).prop("selected", true);
            $("#status_cd").attr("disabled",true);
            $("#division_cd").attr("disabled",true);

            $('#status').attr('style', "display:;");

            $('#btnRegister').attr('style', "display:none;");
            $('#btnModify').attr('style', "display:;");
            $('#btnDelete').attr('style', "display:;");
            $('#btnCancle').attr('style', "display:;");
        });

        $("#btnCancle").click(function() {
            $("#division_cd option:eq(0)").prop("selected", true);
            $("#common_cd").val("");
            $("#status_cd").val("");
            $("#status_nm").val("");
            $("#status_cd").removeAttr("disabled");
            $("#division_cd").removeAttr("disabled");
            $('#status').attr('style', "display:none;");
            $('#btnRegister').attr('style', "display:;");
            $('#btnModify').attr('style', "display:none;");
            $('#btnDelete').attr('style', "display:none;");
            $('#btnCancle').attr('style', "display:none;");
        });

        $("#btnRegister").click(function() {
            if(!$('#division_cd > option:selected').val()) {
                alert("구분을 선택해주세요");
                $("#division_cd").focus();
                return;
            }
            if ($("#status_cd").val().length < 1)
            {
                alert("세부코드를 입력해주세요.");
                $("#status_cd").focus();
                return;
            }
            if ($("#status_nm").val().length < 1)
            {
                alert("세부코드명을 입력해주세요.");
                $("#status_nm").focus();
                return;
            }
            if (window.confirm("공통코드를 등록하시겠습니까?")) {
                $("#division_nm").val($("#division_cd option:checked").text());
                $("#registerForm").ajaxForm({
                    type: 'POST',
                    url: "/admin/insertCommonCode",
                    dataType: "json",
                    enctype: "multipart/form-data",
                    contentType: false,
                    processData: false,
                    timeout: 30000,
                    success: function(result) {
                        if (result.status == 0) {
                            alert(result.msg);
                            var comSubmit = new ComSubmit("searchForm");
                            comSubmit.setUrl("/admin/commoncode");
                            $("#nCurrpage").val("1");
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

        $("#btnModify").click(function() {
            if ($("#status_nm").val().length < 1)
            {
                alert("세부코드명을 입력해주세요.");
                $("#status_nm").focus();
                return;
            }
            if ($("#status_seq").val().length < 1)
            {
                alert("정렬순서를 입력해주세요.");
                $("#status_seq").focus();
                return;
            }
            if (window.confirm("공통코드를 수정하시겠습니까?")) {
                $("#status_cd").removeAttr("disabled");
                $("#division_cd").removeAttr("disabled");
                $("#division_nm").val($("#division_cd option:checked").text());
                $("#registerForm").ajaxForm({
                    type: 'POST',
                    url: "/admin/updateCommonCode",
                    dataType: "json",
                    enctype: "multipart/form-data",
                    contentType: false,
                    processData: false,
                    timeout: 30000,
                    success: function(result) {
                        if (result.status == 0) {
                            alert(result.msg);
                            //window.location = window.location.pathname;

                            var comSubmit = new ComSubmit("searchForm");
                            comSubmit.setUrl("/admin/commoncode");
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

        $("#btnDelete").click(function() {
            if (window.confirm("공통코드를 삭제하시겠습니까?")) {
                $("#status_cd").removeAttr("disabled");
                $("#division_cd").removeAttr("disabled");
                $("#registerForm").ajaxForm({
                    type: 'POST',
                    url: "/admin/deleteCommonCode",
                    dataType: "json",
                    enctype: "multipart/form-data",
                    contentType: false,
                    processData: false,
                    timeout: 30000,
                    success: function(result) {
                        if (result.status == 0) {
                            alert(result.msg);
                            var comSubmit = new ComSubmit("searchForm");
                            comSubmit.setUrl("/admin/commoncode");
                            $("#nCurrpage").val("1");
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