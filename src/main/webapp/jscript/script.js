// ################################################################################## 
//   폼전송시 입력 여부를 체크할때
//   호출 방법    :  setCheckForm(객체)
// ################################################################################## 
function setCheckFormJQuery(srcEl)
{
	var chkStr,strLen,strMsg,gubun,srcStyleDisplay,objnm,objid,typenm,selectIndex,ocjclass;
	var editorflag = false;
	var stateFalg = true;

	$.each(srcEl[0].elements, function(index,elem)
	{
		var _this=$(elem);
		
		objnm = _this.prop('name').toUpperCase();
		if (objnm.length < 1) return false;
		objid = _this.prop('id').toUpperCase();
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
			chkStr = getCheckstring(chkStr);
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
			
			if ( $("#editflag").length > 0 ) {
				if (($("#editflag").val() == "Y") && (typenm == "TEXTAREA") ){
					srcStyleDisplay = "block";
					var values = CKEDITOR.instances[_this.attr('id')].getData();
					chkStr = _this.val();
					if (chkStr == null) values = "";
					chkStr = getCheckstring(chkStr);
					strLen = chkStr.length;
					editorflag = true;
				}
			}
			
 			if (gubun == "*" && srcStyleDisplay != "none"){

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
					else{
						if (pattern.test(chkStr)) {
							alert("금지어를 사용하셨습니다. 금칙어("+pattern.exec(chkStr)+")를 제거해주세요.");
							if (editorflag){
								CKEDITOR.instances[_this.attr('id')].on('instanceReady', function (event) {
									CKEDITOR.instances[_this.attr('id')].focus();
								});
		        			}
		        			else{
		        				_this.focus();
							}
							stateFalg = false;
							return false;
						}
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

function setPostFullWinows(url,params,winnm,winl,wint,nWidth,nHeight,strScroll) {           

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

	var win = window.open("",winnm,"");

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

//################################################################################## 
//ID의 유효성을 체크한다.
//호출 방법    :  getCheckID(아이디오브젝트)
//################################################################################## 
function getCheckID(ObjUserID)
{

	if(!/^[a-zA-Z0-9]{4,12}$/.test(ObjUserID.value))
	{ 
		alert('ID는 숫자와 영문자 조합으로 4~12자리를 사용해야 합니다.');
		ObjUserID.value = "";
		ObjUserID.focus(); 
		return false;
	}
	
	var chk_num = ObjUserID.value.search(/[0-9]/g); 
	var chk_eng = ObjUserID.value.search(/[a-z]/ig); 
	
	//if(chk_num < 0 || chk_eng < 0)
	//{ 
	//	alert('ID는 숫자와 영문자를 혼용하여야 합니다.');
	//	ObjUserID.value = "";
	//	ObjUserID.focus(); 
	//	return false;
	//}
	//
	if(/(\w)\1\1\1/.test(ObjUserID.value))
	{
		alert('ID에 같은 문자를 4번 이상 사용하실 수 없습니다.'); 
		ObjUserID.value = "";
		ObjUserID.focus(); 
		return false;
	}
	
	return true;
}

//################################################################################## 
//비밀번호의 휴효성을 체크한다.
//호출 방법    :  getCehckPassWord(아이디오브젝트, 비밀번호 오브젝트, 비밀번호 확인오브젝트)
//################################################################################## 
function getCehckPassWord(ObjUserID, ObjUserPassWord, objUserPassWordRe)
{

	if(ObjUserPassWord.value != objUserPassWordRe.value)
	{
		alert("입력하신 비밀번호와 비밀번호확인이 일치하지 않습니다");
		ObjUserPassWord.value = "";
		objUserPassWordRe.value = "";
		ObjUserPassWord.focus(); 
		return false;
	}
	
	if(ObjUserPassWord.value.length < 4)
	{
		alert("비밀번호는 문자, 숫자, 특수문자의 조합으로 4~12자리로 입력해주세요.");
		ObjUserPassWord.value = "";
		objUserPassWordRe.value = "";
		ObjUserPassWord.focus(); 
		return false;
	}
	
	//if(!ObjUserPassWord.value.match(/([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9])/))
	//{
	//	alert("비밀번호는 문자, 숫자, 특수문자의 조합으로 8~16자리로 입력해주세요.");
	//	ObjUserPassWord.value = "";
	//	objUserPassWordRe.value = "";
	//	ObjUserPassWord.focus(); 
	//	return false;
	//}
	
	if(ObjUserID.value.indexOf(ObjUserPassWord) > -1)
	{
		alert("비밀번호에 아이디를 사용할 수 없습니다.");
		ObjUserPassWord.value = "";
		objUserPassWordRe.value = "";
		ObjUserPassWord.focus(); 
		return false;
	}
	
	var SamePass_0 = 0; //동일문자 카운트
	var SamePass_1 = 0; //연속성(+) 카운드
	var SamePass_2 = 0; //연속성(-) 카운드
	
	var chr_pass_0;
	var chr_pass_1;
	
	for(var i=0; i < ObjUserPassWord.value.length; i++)
	{
		chr_pass_0 = ObjUserPassWord.value.charAt(i);
		chr_pass_1 = ObjUserPassWord.value.charAt(i+1);
		
		//동일문자 카운트
		if(chr_pass_0 == chr_pass_1)
		{
			SamePass_0 = SamePass_0 + 1
		}
		
		
		//연속성(+) 카운드
		if(chr_pass_0.charCodeAt(0) - chr_pass_1.charCodeAt(0) == 1)
		{
			SamePass_1 = SamePass_1 + 1
		}
		
		//연속성(-) 카운드
		if(chr_pass_0.charCodeAt(0) - chr_pass_1.charCodeAt(0) == -1)
		{
			SamePass_2 = SamePass_2 + 1
		}
	}
	if(SamePass_0 > 1)
	{
		alert("비밀번호는 동일문자를 3번 이상 사용할 수 없습니다.");
		ObjUserPassWord.value = "";
		objUserPassWordRe.value = "";
		ObjUserPassWord.focus(); 
		return false;
	}
	
	if(SamePass_1 > 1 || SamePass_2 > 1 )
	{
		alert("비밀번호는 연속된 문자열(123 또는 321, abc, cba 등)을\n 3자 이상 사용 할 수 없습니다.");
		ObjUserPassWord.value = "";
		objUserPassWordRe.value = "";
		ObjUserPassWord.focus(); 
		return false;
	}
	return true;
}

//################################################################################## 
//사업자등록번호 검사
//호출 방법    :  checkBizID(문자열)
//################################################################################## 
function checkBizID(bizID)
{
	var re = /-/g;
	var bizID = bizID.replace(re,'');
	var checkID = new Array(1, 3, 7, 1, 3, 7, 1, 3, 5, 1);
	var tmpBizID, i, chkSum=0, c2, remander; 
	for (i=0; i<=7; i++){
		chkSum += checkID[i] * bizID.charAt(i);
	}
	
	c2 = "0" + (checkID[8] * bizID.charAt(8));
	c2 = c2.substring(c2.length - 2, c2.length);
	
	chkSum += Math.floor(c2.charAt(0)) + Math.floor(c2.charAt(1));
	
	remander = (10 - (chkSum % 10)) % 10 ;
	
	if (Math.floor(bizID.charAt(9)) == remander){
		return true; // OK!
	}
	return false;
}

//################################################################################## 
//법인번호 검사
//호출 방법    :  isRegNo(문자열)
//################################################################################## 
function isRegNo(sRegNo)
{
	var re = /-/g;
	sRegNo = sRegNo.replace('-','');
	
	if (sRegNo.length != 13){
		return false;
	}
	
	var arr_regno  = sRegNo.split("");
	var arr_wt   = new Array(1,2,1,2,1,2,1,2,1,2,1,2);
	var iSum_regno  = 0;
	var iCheck_digit = 0;
	
	for (i = 0; i < 12; i++){
		iSum_regno +=  eval(arr_regno[i]) * eval(arr_wt[i]);
	}
	
	iCheck_digit = 10 - (iSum_regno % 10);
	
	iCheck_digit = iCheck_digit % 10;
	
	if (iCheck_digit != arr_regno[12]){
		return false;
	}
	return true;
}

//################################################################################## 
//메일주소를 체크한다.
//호출 방법    :  isValidEmail(문자열)
//################################################################################## 
function isValidEmail(email_address)  
{  
	// 이메일 주소를 판별하기 위한 정규식  
	var format = /^[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+)*@[0-9a-zA-Z-]+(\.[0-9a-zA-Z-]+)*$/;  
	 
	// 인자 email_address를 정규식 format 으로 검색  
	if (email_address.search(format) != -1)  
	{  
	   // 정규식과 일치하는 문자가 있으면 true  
	   return true;  
	}  
	else  
	{  
	   // 없으면 false  
	   return false;  
	}  
}  

//################################################################################## 
//입력값에 스페이스 공간을 없애준다.
//호출 방법    :  getCheckstring(문자열)
//################################################################################## 
function getCheckstring(str)
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
}
//################################################################################## 
//글자수를 리턴한다
//호출 방법    :  getCheckLen(문자열)
//################################################################################## 
function getCheckLen(str) {
	var len;
	var temp;
	
	len = str.length;
	var tot_cnt = 0;
	
	for(k=0;k < len;k++){
		temp = str.charAt(k);
		if(escape(temp).length > 4)
			tot_cnt += 2;
		else
			tot_cnt++;
	}
	return tot_cnt;
}

//################################################################################## 
//TextArea 글자수 체크
//호출 방법    :  getCheckStrLen(문자열) 
//################################################################################## 
function getCheckStrLen(srcEl,maxlen) 
{ 
	var temp; //Input String
	var msglen; 
	msglen = maxlen*2;
	var value= srcEl.value; 
	
	l = srcEl.value.length; 
	tmpstr = "" ; 
	
	if (l == 0)	{ 
		value = maxlen*2; 
	}else{ 
		for(k=0;k<l;k++){ 
			temp =value.charAt(k); 
	
			if (escape(temp).length > 4) 
				msglen -= 2; 
			else 
				msglen--; 
	
			if(msglen < 0){ 
				alert("총 영문 "+(maxlen*2)+"자 한글 " + maxlen + "자 까지 보내실수 있습니다."); 
				srcEl.value= tmpstr; 
				break; 
			} 
			else{ 
				tmpstr += temp; 
			} 
		} 
	} 
} 

//################################################################################## 
//시작일과 종료일, 기간에 대한 유효성 검사
//################################################################################## 

function _jsDateCheck(srtDt, endDt, rngDay){

  var arySrtDt = $("#" + srtDt).val().split("-"); // ex) 시작일자(2007-10-09)
  var aryEndDt = $("#" + endDt).val().split("-"); // ex) 종료일자(2007-12-05)

  if( arySrtDt.length != 3 || aryEndDt.length != 3){ 
      alert("날짜 형식이 잘못되었습니다."); 
      return false;
  }

  var startDt = new Date(Number(arySrtDt[0]),Number(arySrtDt[1])-1,Number(arySrtDt[2]));
  var endDt	= new Date(Number(aryEndDt[0]),Number(aryEndDt[1])-1,Number(aryEndDt[2]));
  resultDt	= Math.floor(endDt.valueOf()/(24*60*60*1000)- startDt.valueOf()/(24*60*60*1000));

  if(resultDt < 0 ){ 
  	alert("시작날짜가 더 큽니다.\r\n입력기간을 확인해주세요"); 
  	$("#" + endDt).val("");
  	return false; 
  }
		
		if (rngDay != "undefined"){
  	if(resultDt > rngDay){ 
  		alert("입력가능 기간은 "+ rngDay +"일 입니다.\r\n입력기간을 확인해주세요"); 
  		$("#" + endDt).val("");
  		return false; 
  	}
  }

  return true;
}

//################################################################################## 
//첫번째 자리를 채우면 두번째 입력박스로 넘어감
//################################################################################## 
function setMoveSSN(strEl1, strEl2, num)
{
	if (strEl1.value.length == num)
	{
		strEl2.focus();
	}
}

//################################################################################## 
//영어와 숫자만 입력 받을때
//################################################################################## 

function setIsAlpabat(field)
{
	if((event.keyCode<48)||((event.keyCode>57) && (event.keyCode<65))||((event.keyCode>90) && (event.keyCode<95))||((event.keyCode>95) && (event.keyCode<97))||(event.keyCode>122)){
			alert("영문자와 숫자, 특수기호 '_' 만 입력해 주세요 !!");
			event.returnValue = false;
	}
}

//숫자만 입력받는다. 특수문자('-','.',...)도 허용한다.
function setOnlyNumber() {
	var code = window.event.keyCode; 

  if ((code >= 48 && code <= 57) || (code >= 96 && code <= 105) || code == 110 || code == 190 || code == 8 || code == 9 || code == 13 || code == 46){
  	window.event.returnValue = true;
   	return;
  }
  $(this).on("keyup", "input:text[numberOnly]", function() {$(this).val( $(this).val().replace(/[^0-9]/gi,"") );});

  window.event.returnValue = false;
  event.preventDefault();

}

//숫자만 입력받는다. 특수문자('-','.',...)도 허용한다.
function setOnlyMoney(field) {
	$('#'+field).css('imeMode','disabled').keypress(function(event) {
	    if(event.which && (event.which < 48 || event.which > 57) ) {
	        event.preventDefault();
	    }
	}).keyup(function(){
	    if( $(this).val() != null && $(this).val() != '' ) {
				var tmps = $(this).val().replace(/[^0-9]/g, '');
				var tmps2 = tmps.replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
				$(this).val(tmps2);
		}
	
	});
 
}

//숫자만 입력받는다. 특수문자('-','.',...)도 허용한다.
function setOnlyPhone(field) {
	$('#'+field).css('imeMode','disabled').keypress(function(event) {
	    if(event.which && (event.which < 45 || event.which > 57) ) {
	        event.preventDefault();
	    }
	}).keyup(function(){
	    if( $(this).val() != null && $(this).val() != '' ) {
				var tmps = $(this).val().replace(/-[^0-9]/g, '');
				//var tmps2 = tmps.replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
				$(this).val(tmps);
		}
	
	});
 
}


function setOnlyNumberEtc1() {
	if(((event.keyCode > 31) && (event.keyCode < 45)) || ((event.keyCode > 59) &&(event.keyCode < 96)) || ((event.keyCode > 105) &&(event.keyCode < 186)) || (event.keyCode > 186)) {
		event.returnValue = false;
	}
}


//################################################################################## 
//한글만 입력
//################################################################################## 

function hangul()
{
	 if((event.keyCode < 12592) || (event.keyCode > 12687))
	 event.returnValue = false
}
//################################################################################## 
//Form 생성 후 전송
//getPostToUrl('http://example.com/', {'q':'a'}); 
//path : 전송 URL  
//params : 전송 데이터 {'q':'a','s':'b','c':'d'...}으로 묶어서 배열 입력  
//method : 전송 방식(생략가능)  
//################################################################################## 
function getPostToUrl(path, params,target, method) 
{     
	method = method || "post"; // Set method to post by default, if not specified.     
	target = target || "_self"; // Set method to post by default, if not specified.     
	// The rest of this code assumes you are not using a library.     
	// It can be made less wordy if you use one.
	if (target == "_blank"){
		var nScnHeight = screen.height-150;
		if (path.indexOf("_map") > 0){
			window.open('', 'newWindow','width=1030,height=790,resizable=yes;');
		}
		else{
			window.open('', 'newWindow','width=1200,height='+nScnHeight+',scrollbars=yes,resizable=yes,fullscreen=yes;');
		}
		target = "newWindow";
	}
	
	var arrUrl = path.split("?")
	
	var form = document.createElement("form");     
	form.setAttribute("method", method);     
	form.setAttribute("action", path);     
	form.setAttribute("target", target);     
	for(var key in params) {  
		var hiddenField = document.createElement("input");         
		hiddenField.setAttribute("type", "hidden");         
		hiddenField.setAttribute("name", key);         
		hiddenField.setAttribute("value", params[key]);   
    
		form.appendChild(hiddenField);     
	}     

	if (arrUrl.length > 0){
		var param = unescape(arrUrl[1]);
		param = param.split("%25").join("%");
		param = param.split("%26").join("&");
		param = param.split("%3D").join("=");
		
		var arrParam = param.split("&")
		for(i=0;i<arrParam.length;i++){
			arrUnit = arrParam[i].split("=");
			var hiddenField = document.createElement("input");         
			hiddenField.setAttribute("type", "hidden");         
			hiddenField.setAttribute("name", arrUnit[0]);         
			hiddenField.setAttribute("value", arrUnit[1]);   
	      
			form.appendChild(hiddenField);     
		} 
	}
	document.body.appendChild(form); 
	form.submit(); 
}

function setPostToUrlPop(path, params,target, method,nWidth,nHeight) 
{     
	method = method || "post"; // Set method to post by default, if not specified.     
	target = target || "_self"; // Set method to post by default, if not specified.     
	// The rest of this code assumes you are not using a library.     
	// It can be made less wordy if you use one.
	if (target == "_blank"){
		var nScnHeight = screen.height-150;
		if (path.indexOf("_map") > 0){
			window.open('', 'newWindow','width=1030,height=790,resizable=yes;,scrollbars=yes');
		}
		else{
			window.open('', 'newWindow','width='+nWidth+',height='+nHeight+',resizable=yes;,scrollbars=yes');
		}
		target = "newWindow";
	}
	
	var arrUrl = path.split("?")
	
	var form = document.createElement("form");     
	form.setAttribute("method", method);     
	form.setAttribute("action", path);     
	form.setAttribute("target", target);     
	for(var key in params) {  
		var hiddenField = document.createElement("input");         
		hiddenField.setAttribute("type", "hidden");         
		hiddenField.setAttribute("name", key);         
		hiddenField.setAttribute("value", params[key]);   
    
		form.appendChild(hiddenField);     
	}     

	if (arrUrl.length > 0){
		var param = unescape(arrUrl[1]);
		param = param.split("%25").join("%");
		param = param.split("%26").join("&");
		param = param.split("%3D").join("=");
		
		var arrParam = param.split("&")
		for(i=0;i<arrParam.length;i++){
			arrUnit = arrParam[i].split("=");
			var hiddenField = document.createElement("input");         
			hiddenField.setAttribute("type", "hidden");         
			hiddenField.setAttribute("name", arrUnit[0]);         
			hiddenField.setAttribute("value", arrUnit[1]);   
	      
			form.appendChild(hiddenField);     
		} 
	}
	document.body.appendChild(form); 
	form.submit(); 
} 

function setGoLink(url, param, method,target)	{

	method = method || "post"; // Set method to post by default, if not specified.     
	target = target || "_self"; // Set method to post by default, if not specified. 
	    
	param = unescape(param);
	param = param.split("%25").join("%");
	param = param.split("%26").join("&");
	param = param.split("%3D").join("=");

	subParam = new Object();
	
	var arrParam = param.split("&")
	for(i=0;i<arrParam.length;i++){
		arrUnit = arrParam[i].split("=");
		subParam[arrUnit[0]] = arrUnit[1];
	} 
	
	getPostToUrl(url,subParam, method,target);
}
function sort_select(){
	($('.sort_select>a').parent().hasClass('active')) ? $('.sort_select>a').parent().removeClass('active') : $('.sort_select>a').parent().addClass('active');
	$('.sort_select_list li').on('mouseenter focusin', function(){
		$(this).addClass('active');
	}).on('mouseleave focusout', function(){
		$(this).removeClass('active');
	});
	$('.sort_select_list li>a>.ad').on('mouseenter focusin', function(){
		$(this).parent('a').addClass('active');
	}).on('mouseleave focusout', function(){
		$(this).parent('a').removeClass('active');
	});
}
