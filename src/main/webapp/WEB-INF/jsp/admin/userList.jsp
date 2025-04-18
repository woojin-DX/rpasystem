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
    String localPageTitle = " > 회원정보관리";
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
                    <h3>회원정보관리 </h3>
                </div>
                <div id="content_start">
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
                                        <label for="coperation_cd" class="blind1">업체</label>
                                        <select name="coperation_cd" id="coperation_cd" class="select_r1">
                                            <option value="">전체 </option>
                                            <c:forEach var="companyList" items="${companyList}" varStatus="status">
                                            <option value="${companyList.company_cd}" <c:if test="${pageParam.company_cd == companyList.company_cd}">selected="selected"</c:if>>${companyList.company_nm}</option>
                                            </c:forEach>
                                        </select>
                                        <label for="schword" class="blind">검색어 입력</label>
                                        <input type="text" name="schword" id="schword" title="검색어를 입력해주세요" class="input_sch" style="width:150px" placeholder="검색어를 입력해주세요" value="${pageParam.schword}" />
                                        <input type="image" src="/images/board/btn_schok.gif"  id="searchBtn" alt="검색" title="검색" class="btn_schok" />

                                    </fieldset>
                                </form>
                            </div>
                            <!-- //검색 / 상단 -->

                            <!-- 목록 -->
                            <div id="tabledata">
                                <table cellspacing="0" border="1" summary="목록정보" cellpadding="0" class="bbsL">
                                    <caption>게시판 목록</caption>
                                    <colgroup>
                                        <col width="100px" />
                                        <!--col width="100px" /-->
                                        <col width="100px" />
                                        <col width="250px" />
                                        <col width="150px" />
                                        <col width="100px" />
                                        <col width="150px" />
                                    </colgroup>
                                    <thead>
                                    <tr>
                                        <th scope="col">아이디</th>
                                        <!--th scope="col">이름</th-->
                                        <th scope="col">업체코드</th>
                                        <th scope="col">업체명</th>
                                        <th scope="col">권한</th>
                                        <th scope="col">사용여부</th>
                                        <th scope="col">최종접속일자</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="result" items="${userInfoList}" varStatus="status">
                                    <tr class="row">
                                        <td><c:out value="${result.user_id}"  default=""/></td>
                                        <!--td>관리자</td -->
                                        <td><c:out value="${result.company_cd}"  default=""/></td>
                                        <td><c:out value="${result.company_nm}"  default=""/></td>
                                        <td>
                                            <select name="auth_cd" id="auth_cd" class="select_r1">
                                                <c:forEach var="authorityList" items="${authorityList}" varStatus="status">
                                                    <option value="${authorityList.auth_cd}" <c:if test="${result.auth_cd == authorityList.auth_cd}">selected="selected"</c:if>>${authorityList.auth_nm}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <select name="del_fl" id="del_fl" class="select_r1">
                                                <option value="N" <c:if test="${result.del_fl == 'N'}">selected="selected"</c:if>>사용</option>
                                                <option value="Y" <c:if test="${result.del_fl == 'Y'}">selected="selected"</c:if>>미사용</option>
                                            </select>
                                        </td>
                                        <td><c:out value="${result.lastjoin_dt}"  default=""/></td>
                                    </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <!-- 버튼 -->
                                <div class="bbsB ta_r mt_10">
                                    <ul class="btn_right">
                                        <li><span class="button medium"><a href="#this" id="btnUserCall">회원정보 호출</a></span></li>
                                    </ul>
                                </div>
                                <!-- 페이징 -->
                                <div  id="PAGE_NAVI" class="col_paging">
                                    ${pageNavigater}
                                </div>
                            </div>
                            <!-- // 페이징 -->


                            <!-- //목록 -->
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

        $("#userinfo").attr('class','current');

        $("a[name = 'pageMove']").unbind("click").click(function(e) {
            var comSubmit = new ComSubmit("searchForm");

            comSubmit.setUrl("/admin/userinfo");
            $("#nCurrpage").val($(this).attr("content_id"));
            comSubmit.submit();

        });

        $('#searchBtn').on("click", function(e){
            $("#pagemode").val("search");

            var comSubmit = new ComSubmit("searchForm");
            comSubmit.setUrl("/admin/userinfo");
            comSubmit.submit();
        });

        $('#btnUserCall').on("click", function(e){
            var comSubmit = new ComSubmit("searchForm");
            comSubmit.setUrl("/admin/calluser");
            comSubmit.submit();
        });

        $("select[name='auth_cd']").on( "change", function() {
            var tr = $(this).parent().parent();
            var td = tr.children();

            $.ajax({
                type : "POST",
                url : "/admin/updateUserInfo",
                data : {"user_id": td.eq(0).text(),"auth_cd":$(this).val()},
                dataType : "json",
                error : function(error) {
                    alert("서버가 응답하지 않습니다. \n다시 시도해주시기 바랍니다.");
                },
                success : function(result) {
                    if (result == 1)
                    {
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
            });
        });

        $("select[name='del_fl']").on( "change", function() {
            var tr = $(this).parent().parent();
            var td = tr.children();
            $.ajax({
                type : "POST",
                url : "/admin/updateUserInfo",
                data : {"user_id": td.eq(0).text(),"del_fl":$(this).val()},
                dataType : "json",
                error : function(error) {
                    alert("서버가 응답하지 않습니다. \n다시 시도해주시기 바랍니다.");
                },
                success : function(result) {
                    if (result.status == 1)
                    {
                        alert(result.msg);
                    }
                },
                error: function(data, status, err) {
                    alert("데이타 처리에 에러가 발생했습니다." + "\n" + "다시 시도해주시기 바랍니다." + "\n"
                        + "code: " + data.status + "\n"
                        + "message :" + data.responseText + "\n"
                        + "message1 : " + status + "\n"
                        + "error: " + err);
                }
            });
        });

    });


</script>
</body>
</html>