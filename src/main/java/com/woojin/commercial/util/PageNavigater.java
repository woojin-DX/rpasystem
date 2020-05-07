/*
 * Copyright (c) 2019.
 *   ------------------------------------------------------------------------------
 *   @Project       : RPA Web Project
 *   @Source        : PageNavigater
 *   @Description   :
 *   @Author        : GACHINOEL
 *   @Version       : v1.0
 *   Copyright(c) 2019 WOOJIN All rights reserved
 *   ------------------------------------------------------------------------------
 *                    변         경         사         항
 *   ------------------------------------------------------------------------------
 *      DATE           AUTHOR                       DESCRIPTION
 *   ---------------  ----------    ------------------------------------------------
 *   2019.10.28       gachinoel     신규생성
 *   ------------------------------------------------------------------------------
 */

package com.woojin.commercial.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.HashMap;

public class PageNavigater {
    /**
     * 페이지 가져오기
     * @since 2017. 3. 3.
     * @paramater sUrl 이동주소
     * @paramater sParam 이동주소 변수
     * @paramater iPageTerm 게시물 출력목록 하단에 링크를 걸 페이지의 개수
     * @paramater iPageNum 한 페이지당 출력할 게시물의 수
     * @paramater iTotRecord 총레코드수
     * @paramater iCurPage 현재페이지
     * @paramater sPreImg 이전페이지 이미지
     * @paramater sNextImg 다음페이지 이미지
     * @paramater sFirstImg 첫페이지 이미지
     * @paramater sLastImg 마지막페이지 이미지
     * @return
     * @throws Exception
     */
    public static String getPageForm(HashMap<String, Object> map) throws Exception {

        //------------------------------------------------------------------------------------
        // 상수 선언
        //------------------------------------------------------------------------------------
        int iPageTerm       = (map.get("iPageTerm")!=null?Integer.parseInt(map.get("iPageTerm").toString()):10);  //게시물 출력목록 하단에 링크를 걸 페이지의 개수
        int iPageRecord     = (map.get("iPageRecord")!=null?Integer.parseInt(map.get("iPageRecord").toString()):20);  //한 페이지당 출력할 게시물의 수
        int iTotRecord      = (map.get("iTotRecord")!=null?Integer.parseInt(map.get("iTotRecord").toString()):0);  //총레코드수
        int iCurPage        = (map.get("iCurPage")!=null?Integer.parseInt(map.get("iCurPage").toString()):1);  //현재페이지
        boolean bFirstLast  = (map.get("bFirstLast")!=null?Boolean.parseBoolean(map.get("bFirstLast").toString()):false);  //현재페이지

        int i = 1;

        //리턴할 변수값
        StringBuffer sReturnValue = new StringBuffer();

        try {

            //-------------------------------------------------------------------
            // 페이지 설정 구하기
            //-------------------------------------------------------------------

            int iPREV	   = ((int)((iCurPage-1)/iPageTerm)-1)*iPageTerm+1 ; 			//이전페이지
            int iCUR       = ((int)((iCurPage-1)/iPageTerm))*iPageTerm+1;              //현재구간 시작페이지
            int iNEXT	   = ((int)((iCurPage-1)/iPageTerm)+1)*iPageTerm+1 ;       //다음페이지

            int iTotalPage = 0; //총페이지수

            if (iTotRecord == 0){
                iTotalPage = 0;
            }
            else{
                iTotalPage		= (iTotRecord/iPageRecord)*iPageRecord;

                if (iTotRecord % iPageRecord != 0){
                    iTotalPage = (int)(iTotalPage/iPageRecord) + 1;
                }
                else{
                    iTotalPage = (int)(iTotalPage/iPageRecord);
                }
            }
            //-------------------------------------------------------------------
            // 페이지 보여주기
            //-------------------------------------------------------------------

            if (iTotalPage > 0){
                if (bFirstLast){
                    sReturnValue.append("<a href=\"javascript:void(0)\" name=\"pageMove\" id=\"pageFirst\" content_id=\"");
                    sReturnValue.append("1");
                    sReturnValue.append("\" title=\"맨처음 페이지 이동\" class=\"pnavi first\"></a>");
                }
                if (iPREV > 0){
                    sReturnValue.append("<a href=\"javascript:void(0)\" name=\"pageMove\" id=\"pagePrev\" content_id=\"");
                    sReturnValue.append(iPREV);
                    sReturnValue.append("\" title=\"이전 페이지 이동\" class=\"pnavi back\"></a>");
                }
                else{
                    sReturnValue.append("<a href=\"javascript:void(0)\" name=\"pageMove\" id=\"pagePrev\" content_id=\"");
                    sReturnValue.append("1");
                    sReturnValue.append("\" title=\"이전 페이지 이동\" class=\"pnavi back\"></a>");
                }
                while ((i < iPageTerm + 1) && (iCUR <= iTotalPage)) {
                    if (iCurPage == iCUR) {
                        sReturnValue.append("<a class=\"active\">");
                        sReturnValue.append(iCUR);
                        sReturnValue.append("</a>");
                    }
                    else{
                        sReturnValue.append("<a href=\"javascript:void(0)\" name=\"pageMove\" id=\"pageStep");
                        sReturnValue.append(iCUR);
                        sReturnValue.append("\" content_id=\"");
                        sReturnValue.append(iCUR);
                        sReturnValue.append("\" class=\"pageCur\">");
                        sReturnValue.append(iCUR);
                        sReturnValue.append("</a>");
                    }
                    if ((i != iPageTerm) && (iCUR != iTotalPage)) sReturnValue.append("");

                    iCUR++;
                    i++;
                }

                if (iNEXT <= iTotalPage){
                    sReturnValue.append("<a href=\"javascript:void(0)\" name=\"pageMove\" id=\"pageNext\" content_id=\"");
                    sReturnValue.append(iNEXT);
                    sReturnValue.append("\" title=\"다음 페이지 이동\" name=\"pageMove\" class=\"pnavi next\"></a>");
                }
                else{
                    sReturnValue.append("<a href=\"javascript:void(0)\" name=\"pageMove\" id=\"pageNext\" content_id=\"");
                    sReturnValue.append(iTotalPage);
                    sReturnValue.append("\" title=\"다음 페이지 이동\" class=\"pnavi next\"></a>");
                }
                if (bFirstLast){
                    sReturnValue.append("<a href=\"javascript:void(0)\" name=\"pageMove\" id=\"pageLast\" content_id=\"");
                    sReturnValue.append(iTotalPage);
                    sReturnValue.append("\" title=\"맨 마지막 페이지 이동\" class=\"pnavi last\"></a>");
                }

            }

        }catch(Exception e){

        }

        return sReturnValue.toString();
    }

    /**
     * 페이지 가져오기
     * @since 2013. 8. 18.
     * @param iPage 클릭한 페이지
     * @param iTotRecord 리스트 총수
     * @param iNumPerPage 한 페이지당 출력할 게시물의 수
     * @param iPagePerBlock 게시물 출력목록 하단에 링크를 걸 페이지의 개수
     * @param sScriptFunc 호출할 스크립트 함수명
     * @param sPreImg 이전 이미지경로
     * @param sNextImg 다음 이미지경로
     * @param sSpan span클래스명
     * @return
     * @throws Exception
     */
    public static String PageIndex(int iPage, int iTotRecord, int iNumPerPage, int iPagePerBlock, String sScriptFunc, String sPreImg, String sNextImg, String sSpan) throws Exception {

        //------------------------------------------------------------------------------------
        // 상수 선언
        //------------------------------------------------------------------------------------

        //페이지 관리를 위한 변수
        int iTotPage	= 0 ;
        int iTotalBlock = 0 ;
        int iBlock		= 0 ;
        int iFirstPage	= 0 ;
        int iLastPage	= 0 ;
        int iMyPage	    = 1 ;

        //리턴할 변수값
        StringBuffer sReturnValue = new StringBuffer();

        try {

            //-------------------------------------------------------------------
            // 페이지 설정 구하기
            //-------------------------------------------------------------------

            //--> 현재 블락의 위치
            iBlock = (iPage-1) / iPagePerBlock + 1;
            //--> 총 페이지 수 구하기.
            iTotPage = (iTotRecord -1) / iNumPerPage + 1;
            //--> 현재 페이지의 출력 페이지 범위.
            iFirstPage  = (iBlock -1) * iPagePerBlock + 1;
            iLastPage   = Math.min(iBlock * iPagePerBlock,iTotPage) ;
            //--> 총 블락수 구하기.
            iTotalBlock = (iTotPage-1) / iPagePerBlock + 1;

            //-------------------------------------------------------------------
            // 페이지 보여주기
            //-------------------------------------------------------------------

            //이전페이지블록에 대한 페이지 링크
            if(iBlock > 1) {
                iMyPage = iFirstPage;
                sReturnValue.append("<a href='javascript:");
                sReturnValue.append(sScriptFunc);
                sReturnValue.append("(");
                sReturnValue.append(String.valueOf(iMyPage-1));
                sReturnValue.append(")' class='pnav p_prev'>");
                sReturnValue.append("이전</a> ");

            }

            //현재의 페이지 블럭범위내에서 각 페이지로 바로 이동할 수 있는 하이퍼링크를 출력한다
            for(int i=iFirstPage; i<=iLastPage; i++){

                if (iPage == i){
                    sReturnValue.append("<a href='javascript:void(0);' class=\"on\">");
                    sReturnValue.append(String.valueOf(i));
                    sReturnValue.append("</a> ");
//
                }else{
                    sReturnValue.append("<a href='javascript:");
                    sReturnValue.append(sScriptFunc);
                    sReturnValue.append("(");
                    sReturnValue.append(String.valueOf(i));
                    sReturnValue.append(")'>");
                    sReturnValue.append(String.valueOf(i));
                    sReturnValue.append("</a> ");
                }
            }

            //다음페이지블록에 대한 페이지 링크
            if(iBlock < iTotalBlock) {
                iMyPage = iLastPage+1;

                sReturnValue.append("<a href='javascript:");
                sReturnValue.append(sScriptFunc);
                sReturnValue.append("(");
                sReturnValue.append(String.valueOf(iMyPage));
                sReturnValue.append(")' class='pnav p_next'>");
                sReturnValue.append("다음</a>");
            }

        }catch(Exception e){

        }

        return sReturnValue.toString();
    }

    /**
     * 화면에 표시되는 넘버의 시작점을 가져온다.
     * @since 2013. 8. 18.
     * @param iCurrentpage 한 페이지에 보이는 게시물 수
     * @param iPagePerRows 현재 페이지
     * @param iTotalRowCount 토탈 게시물 수
     * @return
     */
    public static int getNumber(int iCurrentpage, int iPagePerRows, int iTotalRowCount) {

        int iNumber = 0;
        // [Paging] 게시물번호
        if(iTotalRowCount <= iPagePerRows)
            iNumber = iTotalRowCount;
        else
            iNumber = iTotalRowCount - (iCurrentpage - 1) * iPagePerRows;

        return iNumber;
    }

}
