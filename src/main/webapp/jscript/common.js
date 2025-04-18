/*
 * Copyright (c) 2019.
 *   ------------------------------------------------------------------------------
 *   @Project       : RPA Web Project
 *   @Source        : common.js
 *   @Description   :
 *   @Author        : GACHINOEL
 *   @Version       : v1.0
 *   Copyright(c) 2019 WOOJIN All rights reserved
 *   ------------------------------------------------------------------------------
 *                    변         경         사         항
 *   ------------------------------------------------------------------------------
 *      DATE           AUTHOR                       DESCRIPTION
 *   ---------------  ----------    ------------------------------------------------
 *   2019.10.30       gachinoel     신규생성
 *   ------------------------------------------------------------------------------
 */

function gfn_isNull(str) {
    if (str == null) return true;
    if (str == "NaN") return true;
    if (new String(str).valueOf() == "undefined") return true;

    var chkStr = new String(str);
    if( chkStr.valueOf() == "undefined" ) return true;
    if (chkStr == null) return true;
    if (chkStr.toString().length == 0 ) return true;
    return false;
}

function ComSubmit(opt_formId) {
    this.formId = gfn_isNull(opt_formId) == true ? "commonForm" : opt_formId;
    this.url = "";

    if(this.formId == "commonForm"){
        $("#commonForm")[0].reset();
    }

    this.setUrl = function setUrl(url){
        this.url = url;
    };

    this.addParam = function addParam(key, value){
        $("#" + this.formId).append( $( "<input type='hidden' name='"+key+"' id='"+key+"' value='"+value+"' >" ) );
    };

    this.submit = function submit(){
        var frm = $("#" + this.formId)[0];
        frm.action = this.url;
        frm.method = "post";
        frm.submit();
    };
}
