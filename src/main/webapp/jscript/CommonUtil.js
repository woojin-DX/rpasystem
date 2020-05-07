/*===================================================================*/
// 설명	: 공통으로 사용하는 클래스
/*===================================================================*/
commonUtil = {};

//===================================================================
//설명	: Ajax 공통 통신 처리 함수
//		: 세션처리없이 JsonArray일 경우
//인자	: var option = {
//				type: POST,GET 결정(String형 default:POST)
//				url: 액션명(String형),
//				callbackFn : 콜백함수명(함수),
//				data: 파라메타값(String형, 배열형)
//				async: true,false(boolean형 default:false)
//				cache: true,false(boolean형 default:false)
//				dataType: json,xml,html(String형 default:json)
//				error: 에러처리 코드 및 호출 Div
//				repeat: 로그인처리시 호출 함수 및 코드값(배열)
//	error 에러처리
//	-. errorCd		: 구분(A,B)(String형)
//	-. errorDv		: append 될 Div 명(String형)
//	arrRepeat 로그인팝업창 호출
//	-. sFn			: 로그인처리후 실행할함수
//	-. isDisplay	: 로그인 후 로그인 창 유지여부(true:유지, false:사라짐)
//	-. lType		: 로그인 창 종류(1:default[팝업,center])
//	-. lPos			: 로그인위치구분(1:SOI브라우저, 2:블로그, 3:가맹점, 4.광장, 5:회원가입/고객센터)
//
//기능	: Ajax 공통 통신 처리 함수로 에러 및 결과 콜백 함수등을 처리한다.
//
//[사용예] 단, 사용시 필요한 option만을 정리해서 사용하면 한다.
//var sData = "title="+$("#input_plf_reg_title").val()+"&text="+$("#textarea_plf_reg_text").val(); // String으로 data를 넘길 경우 사용
//	var title = $("#input_plf_reg_title").val();
//	var text = $("#textarea_plf_reg_text").val();
//	var arrData = {"title":title,"text":text}; // 배열로 data를 넘길 경우 사용
//	var arrRepeat = {"sFn":"plfFnBoardList","isDisplay":false,"lType":1,"lPos":2}; // 세션이 있을 경우
//	
//	var option = {
//			type : "POST",
//			url : "ar.plf.insertBoard.podo",
//			callbackFn : plfFnInitBoardList,
//			data : arrData,
//			async: false,
//			cache: false,
//			dataType:"json",
//			error: {errorCd:"B",errorDv:"dv_plf_board"},
//			repeat: arrRepeat
//		};
//	commonUtil.fnAjaxCall(option);

commonUtil.fnAjaxCall = function(option) {
    if(option.async == undefined || option.async == null) option.async = true;
    if(option.cache == undefined || option.cache == null) option.cache = false;
    $.ajax({
        url: option.url,
        type: option.type || "POST",
        data : option.data || "",
        async: option.async,
        cache: option.cache,
        dataType: option.dataType || "json",
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            // 공통 에러 출력
            //commonError.fnAjaxErrorView(option.error.errorCd||"B",option.error.errorDv||"");
        },
        success: function(data){
            if(commonError.fnErrorSuccessFlagCheck(data)){
                // 로그인 실행
                if(!commonError.fnLoginSuccessFlagCheck(data)){
                    // 로그인 레이어 팝업
                    //comLogin.fnLoginLayerPopup(option.repeat.sFn,option.repeat.isDisplay,option.repeat.lType,option.repeat.lPos,option.repeat.cType);
                    commonUtil.fnLoginCall();
                }
                else {
                    option.callbackFn(data);
                }

                //option.callbackFn(data);
            }else{
                // 공통 에러 출력 (레이어 디자인이 나오면 구성
                // 에러코드는 레이어 위치가 있는 일단적인 에러코드이고 내부코드는 특정한에러일경우(현재 디비에러를 종류별로 잡음)
                // 에 나타나는 것으로 내부코드는 로그시스템에서 사용한다.
                //alert("에러코드:"+data.error_loc_code+" 내부코드:"+data.error_detail_code);

                if(data.error_loc_code == "0000200001"){
                    alert(data.error_detail_msg);
                }else if(data.error_detail_code == "0000100009"){
                    alert("중복데이터 에러가 발생했습니다.");

                }else {
                    alert("에러가 발생했습니다.");
                }
                //alert("에러가 발생했습니다.");
                //commonError.fnBusinessErrorView(option.error.errorCd||"B",option.error.errorDv||"");
            }
        }
    });
};

//===================================================================
//설명	: Form의 Object를  key, value로 담는다. 구분인자 "&"
//인자	: objForm :: [Object]
//기능	: Form의 Object를  key, value로 담는다.
//등록, 수정, 삭제 시 Ajax 통신을 하며 이때, HTML의 Form Ojbect를 파라미터로 넘길 경우 사용한다.
//===================================================================
commonUtil.setQueryString = function(objForm) {
    queryString = "";
    var numberElements = objForm.elements.length; // check frm.elements.length - 1 로 하는 경우가 있음.
    for(var i = 0; i < numberElements; i++)
    {
        input = objForm.elements[i];

        if(i < numberElements - 1){
            queryString += input.name + "=" + encodeURIComponent(input.value) + "&";
        } else {
            queryString += input.name + "=" + encodeURIComponent(input.value);
        }
    }
    return queryString;
};

//===================================================================
//설명	: Form의 Object를  key, value로 담는다. 구분인자 "&"
//인자	: objForm :: [Object]
//기능	: Form의 Object를  key, value로 담는다.
//등록, 수정, 삭제 시 Ajax 통신을 하며 이때, HTML의 Form Ojbect를 파라미터로 넘길 경우 사용한다.
//===================================================================
commonUtil.setFormString = function(objForm,rownm) {
    queryString = "";
    var numberElements = objForm.elements.length; // check frm.elements.length - 1 로 하는 경우가 있음.
    for(var i = 0; i < numberElements; i++)
    {
        input = objForm.elements[i];

        queryString += input.name + "=" + encodeURIComponent(input.value) + "&";
    }

    var gridData = $(rownm).jqGrid('getRowData');
    var gridCell = $(rownm).jqGrid("getGridParam", "colModel");

    var cell;

    for (var i = 0; i < gridData.length; i++) {
        for(var j=0;j<gridCell.length;j++){
            cell = eval("gridData[i]."+gridCell[j].name);

            queryString += gridCell[j].name + "=" + encodeURIComponent(cell) + "&";
        }

    }
    return queryString;
};

//##################################################################################
//폼전송시 입력 여부를 체크할때
//호출 방법    :  setCheckForm(객체)
//################################################################################## ]
commonUtil.fnFormVaildCheck = function(srcEl){
    var chkStr,strLen,strMsg,gubun,srcStyleDisplay,objnm,objid,typenm,selectIndex,ocjclass;
    var editorflag = false;
    var stateFalg = true;
    var pattern = /10창|10탱|10새|10새기|10새리|10세리|10쎄|10쉐이|10쉑|10스|10쌔|10쌔기|10알|18것|18년|18놈|18노|18넘|18뇬|18롬|18럼|18새|18새끼|18색|18세끼|18세리|18섹|18쉑|18스|18아|좇이|잡것|좇같|잡년|잡놈|잡넘|쥐랄|쥐롤|좌식|쪼다|쥬디|쪽발이|이년|찌랄|이새키|이새끼|쫍빱|이스키|이스끼|임마|asshole|저년|저미친년|저새끼|자슥|쳐박혀서|쳐발랐|주접|주접떨|주글|주글래|주둥이|주둥아리|주데이|주뎅|주뎅이|지랄|지롤|지럴|지미랄|접년|창녀|젖밥|짜식|짜아식|제길|젠장|젬병|짱깨이|육갑|육시랄|육시랄놈|육시럴|육시럴놈|처죽일|처먹어|조찐|조쟁이|조질래|조지냐|조진다|조까|조까치|조낸|조또|조랭|조빠|조옷|존나|존나게|존니|존만|존만한|졸라|은년|을년|좀물|좁년|좁밥|좃찐|좃이|좃까|좃또|좃만|좃밥|좆찐|좆이|좆같|좆까|좆나|좆또|좆만|좆밥|죽을래|죽통|죽고잡|추천인|bitch|cock|할망탱이|할망구|할망구탱이|할마시|cunt|damn|dick|후라들|후라들년|후라들넘|후려쳤|후뢰|후래자식|후레|후레아들|호로자식|호로놈|호로새끼|호로색|호로쉑|호로시키|호로스키|호로스까이|화냥년|fuck|fucker|penis|pussy|sex|sexy|shit|suck|sucker|sux|탱구|팔럼|퍽큐|친구새끼|칠뜨기|카지노|캐년|캐놈|캐시키|캐스키|캐스끼|ㄱㅐ|ㄲㅑ|ㄲㅅㅂㄹㅁ|ㄲㅏ|ㅅㅐ|ㅆㅣ|ㅆㅂㄹㅁ|ㅆㅍ|ㅆ앙|ㅍㅏ가시내|걔새|걔수작|걔시키|걔시끼|걸레|고스톱|골로가|게색기|게색끼|가시나|갈보갈보년|갈아버려|갈아버려라|갈아버린다|강아지|같은년|같은뇬|개좌식|개자지|개자슥|개접|개좆|개차반|개허접|개같은|개구라|개년|개놈|개나발|개대중|개뇬|개돼중|개독|개랄|개망신|개보지|개뿔|개새|개새키|개새기|개새끼|개색히|개색키|개색기|개색끼|개뻥|개쇳기|개소리|개수작|개섀끼|개세|개세이|개세끼|개시키|개십새기|개십새끼|개쉐|개쉐이|개쉐리|개쉑|개쉽|개스끼|개씹|개아들|개쑈|광뇬|구녕|구멍|그년|그새끼|깡패새끼|꼴아보|꺼벙이|꺼벙아|놈현|나쁜새끼|냄비|담보|대출|대가리|니기미|니귀미|니미|니미랄|니미럴|니미씹|니어매|니어메|니어미|니아배|니아비|니아베|닥쳐|뇬|눈깔|뉘미럴|닝기리|닝기미|되질래|디질래|디져라|디진다|등신|등신|뒤질래|딩시|따식|뎡신|도라이|돈놈|돌은놈|돌아이|뒈질|뒈져|뒈져라|뒈진|뒈진다|또라이|뙨놈|뙨넘|똘아이|띠팔|뙈놈|뙤놈|띨띨이|띠바|띠발|띠불|뚜쟁|때놈|망할년놈|모리배|멍청이|멍청아|멍텅구리|매춘부|메친놈|메친넘|뱅마|뱅신|미췬|미친|미친짓|미친년|미친놈|미친넘|미친새끼|미친스까이|미틴|미틴년|미틴놈|미틴넘|바랄년|바보|바보새끼|발작하네|븅|븅신|부랄|부럴|불할|불알|빌어먹|빙시|빙신|붙어먹|빠큐|빠가|빠구리|빠굴|뷰웅|벼엉신|병자|병신|병쉰|붕가|사이비|뽁큐|삿대질|새키|새갸|새꺄|상놈의|상놈이|상놈을|상넘이|새끼|새새끼|생쑈|색끼|뻐큐|뻑큐|쇼하네|세갸|세꺄|세끼|섹스|썅|썩을년|썩을놈|시팔|시팍|시펄|시궁창|썅년|썅놈|시끼|시댕|시뎅|시랄|시벌|써벌|시발|시브랄|시부랄|시부럴|시부리|시불|십8|십창|심탱|십탱|쎄꺄|십라|심발끈|십새|십새끼|십새끼|십세|십쉐|십쉐이|십스키|십쌔|쎄엑|싸가지|싸대기|싶알|싹아지|쌉년|쌕|신발끈|쌍년|쌍놈|쌍넘|쌍뇬|쉐|쉐키|쉐기|쉐끼|쉐리|쉐에기|쉑|쉬펄|쉬밸|쉬벌|쉬발|쉬뻘|쉽알|스패킹|스팽|쌔끼|쌩쑈|씹이|씹질|씹자지|씹창|씹할|씹헐|씹탱|씹팔|씹같|씸년|씹년|씸뇬|씹뇬|씹보지|씹새|씹새기|씸새끼|씹새끼|씹새리|씹세|씹쉐|씹스키|씹쌔|양년|양놈|양넘|양아치|얼간이|얼간아|아가리|아갈|아갈이|아갈통|아구창|아구통|아굴|얌마|엄창|쒸팔|쒸펄|쒸벌|쒸뻘|쓰팔|쓰바|쓰박|쓰벌|쓰발|씁새|씁얼|씌파|씨8|씨팔|씨팍|씨파|씨펄|씨끼|씨댕|씨뎅|씨뱅|씨밸|씨바|씨박|씨벌|씨바랄|씨방|씨방새|씨방세|씨발|씨발놈|씨봉|씨봉알|씨브랄|씨부랄|씨부럴|씨부렁|씨부리|씨불|씨빠|씨벨|씨붕|씨뽀랄|씨빨|씨알머리|씨앙|오입|욤병|우라질|욕쟁이|욕먹|왜년|왜놈|엠병|여물통|열라|염병|엿같|엿먹어|옘빙|옘병|;--|@@|--, #|-1 or|char\(|varchar\(|alter table|alter column|delete from|drop table|drop column|declare @|exec\(|on error resume|set @/i;

    $.each(srcEl[0].elements, function(index,elem)
    {
        var _this=$(elem);

        objnm = _this.attr('name').toUpperCase();
        objid = _this.attr('id').toUpperCase();
        typenm = _this.prop("type").toUpperCase();
        if (objnm.length > 2){
            ocjclass = objnm.substring(0,3);
        }
        else{
            ocjclass = "";
        }

        if ((typenm != "FIELDSET") && (typenm != "OBJECT") ) {
            chkStr = _this.val();
            if (chkStr == null) chkStr = "";
            chkStr = commonUtil.getCheckstring(chkStr);
            strLen = chkStr.length;
            strMsg= _this.attr('title');
            if (strMsg == null) strMsg = "";
            srcStyleDisplay = _this.css("display");
            srcStyleVisible = _this.is(':visible');

            if (strMsg.length > 2){
                gubun = strMsg.substring(0,1);
                strMsg = strMsg.substring(1);
            }
            else{
                gubun = "";
            }

            if (gubun == "*" && srcStyleDisplay != "none" && srcStyleVisible != false){

                if (typenm == "SELECT-ONE"){
                    selectIndex = _this.children("option:selected").index();
                    if(selectIndex == 0) {
                        alert(strMsg);
                        _this.focus();
                        stateFalg = false;
                        return false;
                    }
                }
                else if (typenm == "SELECT-MULTIPLE"){
                    selectIndex = _this.children("option:selected").index();
                    var nIdx = _this.find("option:selected").length;
                    if(nIdx < 1) {
                        alert(strMsg);
                        _this.focus();
                        stateFalg = false;
                        return false;
                    }
                }
                else if(typenm == "TEXT"){
                    if(strLen < 1) {
                        alert(strMsg);
                        if (typenm != "HIDDEN") {
                            _this.focus();
                        }
                        stateFalg = false;
                        return false;
                    }
                    else{

                        if (ocjclass == "DT_"){
                            if (!_isValidDate(chkStr)){
                                alert("날짜 형식이 맞지 않습니다.");
                                _this.focus();
                                stateFalg = false;
                                return false;
                            }
                        }
                    }
                }
                else if(typenm == "TEXTAREA"){

                    if(strLen < 1) {
                        alert(strMsg);
                        if (typenm != "HIDDEN") {
                            if (editorflag){
                                CKEDITOR.instances[_this.attr('id')].focus();
                            }
                            else{
                                _this.focus();
                            }
                        }
                        stateFalg = false;
                        return false;
                    }
                }
                else if(typenm == "CHECKBOX"){
                    var chkFlag = true;
                    _this.each(function() {
                        nam = $(this).attr('name'); // get the name of its set
                        if (chkFlag && !$(':checkbox[name="'+nam+'"]:checked').length) {
                            chkFlag = false;
                        }
                    });

                    if (!chkFlag){
                        alert(strMsg);
                        _this.focus();
                        stateFalg = false;
                        return false;
                    }
                }
                else if(typenm == "RADIO"){
                    var chkFlag = true;
                    _this.each(function() {
                        nam = $(this).attr('name'); // get the name of its set
                        if (chkFlag && !$(':radio[name="'+nam+'"]:checked').length) {
                            chkFlag = false;
                        }
                    });

                    if (!chkFlag){
                        alert(strMsg);
                        _this.focus();
                        stateFalg = false;
                        return false;
                    }
                }
                else if(typenm == "FILE"){
                    if(strLen < 1) {
                        alert(strMsg);
                        if (typenm != "HIDDEN") {
                            _this.focus();
                        }
                        stateFalg = false;
                        return false;
                    }
                    else{
                        if (pattern.test(chkStr)) {
                            alert("금지어를 사용하셨습니다. 금칙어("+pattern.exec(chkStr)+")를 제거해주세요.");
                            _this.focus();
                            stateFalg = false;
                            return false;
                        }
                    }
                }
                else if(typenm=="HIDDEN"){
                    if(strLen < 1) {
                        alert(strMsg);
                        if (typenm != "HIDDEN") {
                            _this.focus();
                        }
                        stateFalg = false;
                        return false;
                    }
                }
                else if(typenm=="PASSWORD"){
                    if(strLen < 1) {
                        alert(strMsg);
                        if (typenm != "HIDDEN") {
                            _this.focus();
                        }
                        stateFalg = false;
                        return false;
                    }
                }
            }
            if (typenm == "SELECT-MULTIPLE"){
                var multi;
                if (objid.indexOf("ALL") > -1){
                    _this.each(function () {
                        multi = _this.find("option");
                        multi.attr("selected", "selected");

                    });
                }
            }

        }
    });

    return stateFalg;
};

commonUtil.getCheckstring = function (str)
{
    var index, len;

    while(true) {
        index = str.indexOf(" ");
        if (index == -1) break;
        len = String(str).length;
        str = str.substring(0, index) + str.substring((index+1),len);
    }

    str = escape(str);

    while(true) {
        index = str.indexOf("%0D%0A");
        if (index == -1) break;
        len = String(str).length;
        str = str.substring(0, index) + str.substring((index+6),len);
    }
    str = unescape(str);

    return str;
};

commonUtil.fnLoginCall = function() {
    //alert("로그인창으로 이동.");
    location.href = "/login";
};

commonUtil.fnGridHeaderColor = function(){
    return "red";
};

// Mobile Detect
commonUtil.isMobile = {
    Android: function() {
        return navigator.userAgent.match(/Android/i);
    },
    BlackBerry: function() {
        return navigator.userAgent.match(/BlackBerry/i);
    },
    iOS: function() {
        return navigator.userAgent.match(/iPhone|iPad|iPod/i);
    },
    Opera: function() {
        return navigator.userAgent.match(/Opera Mini/i);
    },
    Windows: function() {
        return navigator.userAgent.match(/IEMobile/i);
    },
    any: function() {
        return (commonUtil.isMobile.Android() || commonUtil.isMobile.BlackBerry() || commonUtil.isMobile.iOS() || commonUtil.isMobile.Opera() || commonUtil.isMobile.Windows());
    }
};

// APP의 Title 변경을 위한 호출
commonUtil.setTitle = function(title) {
    try{
        if ( !!commonUtil.isMobile.Android() ) {
            // Android Title 변경
            window.android.setTitle(title);
        }
        else if ( !!commonUtil.isMobile.iOS() ) {
            // iOS Title 변경
            document.title = title;
        }
    }catch(e){
        //alert("웹뷰호출 실패");
    }

}

function ComSubmit(opt_formId) {
    this.formId = gfn_isNull(opt_formId) == true ? "commonForm" : opt_formId;
    this.url = "";
    if (this.formId == "commonForm") {
        $("#commonForm")[0].reset();
    }

    this.setUrl = function setUrl(url) {
        this.url = url;
    };

    this.addParam = function addParam(key, value) {
        if (key != "x" && key != 'y') {
            $("#" + this.formId).append($("<input type='hidden' name='" + key + "' id='" + key + "' value='" + value + "' >"));
        }
    };

    this.submit = function submit() {
        var frm = $("#" + this.formId)[0];
        frm.action = this.url;
        frm.method = "post";
        frm.submit();
    };
}
