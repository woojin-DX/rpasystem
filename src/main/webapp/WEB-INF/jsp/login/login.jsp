<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/include/include-header.jsp" %>
</head>
<body style="top-margin:0;height:100%;">
<div class="mainWrap2">
    <div id="wrapLogin" class="mainWrap">
        <div id="headerLogin">
            <div class="top_menu">
                <h1 class="logo"><img src="/images/roling/logo.gif" alt="우진공업주식회사" /></h1>
                <p class="promise"><img src="/images/roling/promise1.gif" alt="끊임없이 성장하는 우진공업이 되겠습니다." /></p>
                <h1 class="member">WEB발주시스템</h1>
            </div>
        </div>
        <div id="container">
            <div class="tabmenu">
                <div class="tab_visual">
                    <img src="/images/roling/img_maintab1.gif" alt="롤이미지" class="banner" />
                    <img src="/images/roling/img_maintab2.gif" alt="롤이미지" class="banner" />
                    <img src="/images/roling/img_maintab3.gif" alt="롤이미지" class="banner" />
                    <img src="/images/roling/img_maintab4.gif" alt="롤이미지" class="banner" />
                    <img src="/images/roling/img_maintab5.gif" alt="롤이미지" class="banner" />
                </div>
                <ul class="tab_list">
                    <li><img src="/images/roling/tab1.gif" alt="스파크플러그" onMouseOver="mover(1);tstop();" onMouseOut="tm()" /></li>
                    <li><img src="/images/roling/tab2.gif" alt="글로우플러그" onMouseOver="mover(2);tstop();" onMouseOut="tm()" /></li>
                    <li><img src="/images/roling/tab3.gif" alt="산소센서" onMouseOver="mover(3);tstop();" onMouseOut="tm()" /></li>
                    <li><img src="/images/roling/tab4.gif" alt="AIR HEATER" onMouseOver="mover(4);tstop();" onMouseOut="tm()" /></li>
                    <li><img src="/images/roling/tab5.gif" alt="기타" onMouseOver="mover(5);tstop();" onMouseOut="tm()" /></li>
                </ul>
            </div>

            <div class="form">
                <div class="form2">
                    <div class="form3">
                        <label for="user_id">아이디</label><input type="text" id="user_id"  name="user_id" class="form-control"  placeholder="아이디" autofocus="autofocus" />
                        <div class="clear"></div>
                        <label for="user_pwd">비밀번호</label><input type="password" id="user_pwd" name="user_pwd" class="form-control" placeholder="비밀번호" />
                    </div>
                    <input type="button" class="btn_submit" id="login_btn" value="로그인하기">
                    <div class="clear"></div>
                    <div class="form4">
                        <input type="checkbox" id="idSaveCheck" name="isIdSave" value="Y" class="checkbox_css" /><label class="checkbox_label" for="idSaveCheck">아이디저장</label>
                        <input type="checkbox" id="chk_cookie" name="isUseCookie" value="Y" class="checkbox_css" /><label class="checkbox_label" for="chk_cookie">로그인 상태 유지</label>
                        <div class="clear"></div>
                        <!--label><input type="button" class="btn_public" id="signUpBtn" value="회원가입"></label>
                        <label><input type="button" class="btn_public" id="idpwd_btn" value="아이디/비밀번호 찾기"></label-->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--div id="footerLogin">
        <p class="address"><img src="/images/roling/text_address.gif" alt="" /></p>
    </div-->
</div>
<%@ include file="/WEB-INF/include/include-body.jsp" %>
<script>
    var tmpidx=0;
    var nowidx=0;
    var t=0;
    var times;

    $(document).ready(function() {
        $(document).prop('title', '${pageTitle} > 로그인');
        $('.banner').first().css('z-index','20');
        $('.tab_list img').fadeTo('fast', 0.7);
        $('.tab_list img').first().fadeTo('fast', 1);
        tm();

        // 저장된 쿠키값을 가져와서 ID 칸에 넣어준다. 없으면 공백으로 들어감.
        var key = getCookie("key");
        $("#user_id").val(key);

        if($("#user_id").val() != ""){ // 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
            $("#idSaveCheck").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
            $("#user_pwd").focus();
        }

        $("#idSaveCheck").change(function(){ // 체크박스에 변화가 있다면,
            if($("#idSaveCheck").is(":checked")){ // ID 저장하기 체크했을 때,
                setCookie("key", $("#user_id").val(), 7); // 7일 동안 쿠키 보관
            }else{ // ID 저장하기 체크 해제 시,
                deleteCookie("key");
            }
        });

        // ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
        $("#user_id").keyup(function(){ // ID 입력 칸에 ID를 입력할 때,
            if($("#idSaveCheck").is(":checked")){ // ID 저장하기를 체크한 상태라면,
                setCookie("key", $("#user_id").val(), 7); // 7일 동안 쿠키 보관
            }
        });

        $("#login_btn").unbind("click").click(function(e) {
            e.preventDefault();
            fn_login();
        });

        $("#signUpBtn").unbind("click").click(function(e) {
            e.preventDefault();
            fn_moveToSignUp();
        });

        $("#user_pwd").keydown(function(e) {
            if (e.keyCode == 13) {
                fn_login();
            }
        });
    });

    function mover(i){
        $('.banner').css({'z-index':'10'});
        $('.banner').eq(nowidx).css({'z-index':'15'});
        tmpidx=i-1;
        $('.banner').eq(tmpidx).css({'opacity':0,'z-index':'20'});
        $('.banner').eq(tmpidx).animate({'opacity': 1},500);
        nowidx=tmpidx;
        $('.tab_list img').fadeTo('fast', 0.7);
        $('.tab_list img').eq(tmpidx).fadeTo('fast', 1);
    }

    function tm(){
        times=setInterval("action()",3000);
    }

    function tstop(){
        clearTimeout(times);
    }

    function action(){
        t=t+1;
        if (t>5){t=1;}
        mover(t);
    }

    function fn_login() {
        if($("#user_id").val().length < 1)
        {
            alert("아이디를 입력해주세요.");
        }
        else if($("#user_pwd").val().length < 1)
        {
            alert("비밀번호를 입력해주세요.");
        }
        else
        {
            var userData = {
                "user_id"            : $("#user_id").val(),
                "user_pwd"        : $("#user_pwd").val(),
                "isUseCookie"    : $("#chk_cookie").prop("checked") == true ? "Y" : "N"
            };

            $.ajax({
                type: "post",
                global: true,
                async: true,
                url: "/loginProcess",
                dataType : "json",
                timeout: 30000,
                cache: true,
                data: userData,
                contentType : "application/x-www-form-urlencoded; charset=utf-8",
                error: function (jqXHR, textStatus, errorThrown) {
                    // 통신에 에러가 있을경우 처리할 내용(생략가능)
                    alert("서버가 응답하지 않습니다." + "\n" + "다시 시도해주시기 바랍니다." + "\n"
                        + "code: " + jqXHR.status + "\n"
                        + "message :"+jqXHR.responseText + "\n"
                        + "message1 : " + textStatus + "\n"
                        + "error: " + errorThrown);
                },
                success: function (result, textStatus, jqXHR) {
                    // 통신이 정상적으로 완료되면 처리할 내용
                    if(result.status == 0)
                    {
                        alert(result.msg);
                        window.location.href="/";
                    }
                    else if(result.status == 1)
                    {
                        alert(result.msg);
                    }
                }
            });

        }
    }

    function setCookie(cookieName, value, exdays){
        var exdate = new Date();
        exdate.setDate(exdate.getDate() + exdays);
        var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
        document.cookie = cookieName + "=" + cookieValue;
    }

    function deleteCookie(cookieName){
        var expireDate = new Date();
        expireDate.setDate(expireDate.getDate() - 1);
        document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
    }

    function getCookie(cookieName) {
        cookieName = cookieName + '=';
        var cookieData = document.cookie;
        var start = cookieData.indexOf(cookieName);
        var cookieValue = '';
        if(start != -1){
            start += cookieName.length;
            var end = cookieData.indexOf(';', start);
            if(end == -1)end = cookieData.length;
            cookieValue = cookieData.substring(start, end);
        }
        return unescape(cookieValue);
    }


</script>

</body>
</html>