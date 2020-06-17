/*
 * Copyright (c) 2019.
 *   ------------------------------------------------------------------------------
 *   @Project       : RPA Web Project
 *   @Source        : StringUtil
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class StringUtil {
    /**
     * 입력받은 문자열에서 구분자 기준으로 데이터가 없는 경우 <br />
     * 해당 데이터는 무시하고 실제 값이 존재하는 부분만 값을 스트링배열에 담아서 반환.
     * <pre>
     * String[]sReturn = StringUtil.sGetSplit("1-2-3-4-5-6-7-8-9","-");
     * sReturn 결과: [1,2,3,4,5,6,7,8,9]
     * </pre>
     * @since 2010. 3. 3.
     * @param str 입력받은 문자열
     * @param delimeter 구분자(경계부호)
     * @return 구분자 기준으로 나눈 부분을 배열에 담은 문자열
     */
    public static String[] sGetSplit(String str, String delimeter)
    {
        StringTokenizer st = new StringTokenizer(str, delimeter);
        String[] values = new String[st.countTokens()];
        int pos = 0;
        while (st.hasMoreTokens())
        {
            values[pos++] = st.nextToken();
        }
        return values;
    }

    /**
     * 입력받은 NULL 또는 빈 문자열을 체크 후 반환.
     * <pre>
     * 1: boolean bReturn = StringUtil.isNull("aa");
     *    bReturn 결과: false
     * 2: boolean bReturn = StringUtil.isNull(null);
     *    bReturn 결과: true
     * </pre>
     * @since 2010. 3. 3.
     * @param str 입력받은 문자열
     * @return NULL 또는 빈 문자열일 경우 true, 아닐 경우 false를 리턴한다
     */
    public static boolean isNull(String str)
    {
        if(str == null || str.trim().length() == 0){ return true; } else { return false; }
    }

    /**
     * NULL인 문자열을 스페이스("")로 치환 후 반환.
     * <pre>
     * 1: String sReturn = StringUtil.sGetNullSpace("aa");
     *    sReturn 결과: "aa"
     * 2: String sReturn = StringUtil.sGetNullSpace(null);
     *    sReturn 결과: ""
     * </pre>
     * @since 2010. 3. 3.
     * @param str 입력받은 문자열
     * @return 치환된 문자열
     */
    public static String sGetNullSpace(String str)
    {
        if(str == null || str.length() == 0){ return ""; } else { return str; }
    }

    /**
     * 좌우 공백을 제거 하고 NULL인 경우엔 문자열을 스페이스("")로 치환 후 반환.
     * <pre>
     * 1: String sReturn = StringUtil.sGetullTrimSpace("              aa");
     *    sReturn 결과: "aa"
     * 2: String sReturn = StringUtil.sGetullTrimSpace(null);
     *    sReturn 결과: ""
     * </pre>
     * @since 2010. 3. 3.
     * @param str 입력받은 문자열
     * @return 치환된 문자열
     */
    public static String sGetNullTrimSpace(String str)
    {
        if(str == null || str.length() == 0){ return ""; } else { return str.trim(); }
    }

    /**
     * 좌우 공백을 제거 하고 NULL 또는 빈 문자열을 "&nbsp;"로 변환 후 반환.
     * <pre>
     * 1: String sReturn = StringUtil.sGetNullTrimNbsp("  aa  ");
     *    sReturn 결과: "aa"
     * 2: String sReturn = StringUtil.sGetNullNbsp("        aa      ");
     *    sReturn 결과: "        aa      "
     * 3: String sReturn = StringUtil.sGetNullTrimNbsp(null);
     *    sReturn 결과: &nbsp     *
     * </pre>
     * @since 2010. 3. 3.
     * @param org 입력받은 문자열
     * @return 치환된 문자열
     */
    public static String sGetNullTrimNbsp(String org)
    {
        if(org == null || org.trim().length() == 0){ return "&nbsp;"; } else { return org.trim(); }
    }

    /**
     * true(좌우 공백 제거), false(공백 제거 안함)하고 NULL 또는 빈 문자열을 경우엔 "&nbsp;"로 변환 후 반환.
     * <pre>
     * 1: String sReturn = StringUtil.sGetNullNbsp("", true);
     *    sReturn 결과: "&nbsp;"
     * 2: String sReturn = StringUtil.sGetNullNbsp("        str      ", true);
     *    sReturn 결과: "str"
     * 3: String sReturn = StringUtil.sGetNullNbsp("        str      ", false);
     *    sReturn 결과: "        str      "
     * </pre>
     * @since 2010. 3. 3.
     * @param org 입력받은 문자열
     * @param flag 좌우 공백이 있는 문자열에 대한 true/false 결정
     * @return 치환된 문자열
     */
    public static String sGetNullNbsp(String org, boolean flag)
    {
        if(org == null || org.trim().length() == 0){
            return "&nbsp;";
        } else {
            if(flag){
                return org.trim();
            } else {
                return org;
            }
        }
    }

    /**
     * NULL 또는 스페이스("")일 경우 원하는 문자열로 변환.
     * <pre>
     * 1: String sReturn = StringUtil.sGetnullStr("aaa", "bbb");
     *    sReturn 결과: "aaa"
     * 2: String sReturn = StringUtil.sGetnullStr("", "bbb");
     *    sReturn 결과: "bbb"
     * </pre>
     * @since 2010. 3. 3.
     * @param org 입력받은 문자열
     * @param converted 치환될 문자열
     * @return 치환된 문자열
     */
    public static String sGetNullStr(String org, String converted)
    {
        if(org == null || org.length() == 0){ return converted; } else { return org; }
    }

    /**
     * true(좌우 공백 제거), false(공백 제거 안함)하고 NULL 또는 스페이스("")일 경우 원하는 문자열로 변환.
     * <pre>
     * 1: String sReturn = StringUtil.sGetnullStr("  aaa  ", "bbb", true);
     *    sReturn 결과: "aaa"
     * 2: String sReturn = StringUtil.sGetnullStr("  aaa  ", "bbb", false);
     *    sReturn 결과: "  aaa  "
     * 3: String sReturn = StringUtil.sGetnullStr("", "bbb", true);
     *    sReturn 결과: "bbb"
     * </pre>
     * @since 2010. 3. 3.
     * @param org 입력받은 문자열
     * @param converted 치환될 문자열
     * @param flag 좌우 공백이 있는 문자열에 대한 true/false 결정
     * @return 치환된 문자열
     */
    public static String sGetNullStr(String org, String converted, boolean flag)
    {
        if(org == null || org.length() == 0){ return converted; } else { if(flag){ return org.trim(); } else { return org; }}
    }

    /**
     * 문자열의 좌우 공백을 제거 하고 NULL 또는 빈 문자열을 원하는 문자열로 변환.
     * <pre>
     * 1: String sReturn = StringUtil.sGetNullTrimStr(" aa ", "bb")
     *    sReturn 결과: "aa"
     * 2: String sReturn = StringUtil.sGetNullTrimStr("", " bb ")
     *    sReturn 결과: "bb"
     * </pre>
     * @since 2010. 3. 3.
     * @param org 입력받은 문자열
     * @param converted 치환될 문자열
     * @return 치환된 문자열
     */
    public static String sGetNullTrimStr(String org, String converted)
    {
        if(org == null || org.trim().length() == 0){ return converted; } else { return org.trim(); }
    }

    /**
     * 입력 받은 문자열 중에서 특정문자를 원하는 문자로 치환 후 반환.
     * <pre>
     * String sReturn = StringUtil.sGetReplaceAll("abcd","b","c");
     * sReturn 결과: "accd"
     * </pre>
     * @since 2010. 3. 3.
     * @param str 대상문자열
     * @param src 치환될 문자
     * @param tgt 치환할 문자
     * @return 치환된 문자열
     */
    public static String sGetReplaceAll(String str, String src, String tgt) {

        if(str == null){ return null; }
        if(isNull(src) || str.indexOf(src) < 0){ return str; }

        int i = 0;
        int j = 0;
        int bufLen = str.length();
        int srcLen = src.length();

        StringBuffer ret = new StringBuffer();

        while(i < bufLen){
            j = str.indexOf(src, j);
            if(j >= 0) {
                ret.append(str.substring(i, j));
                ret.append(tgt);
                j += srcLen;
                i = j;
            } else {
                break;
            }
        }
        ret.append(str.substring(i));
        return ret.toString();
    }

    /**
     * 입력 받은 문자열 중에서 특정문자를 원하는 문자로 치환 후 반환.
     * <pre>
     * String sReturn = StringUtil.sGetReplaceAll("abcd","b","c");
     * sReturn 결과: "accd"
     * </pre>
     * @since 2010. 3. 3.
     * @param str 대상문자열
     * @param src 치환당할 문자
     * @param tgt 치환할 문자
     * @return 특정문자를 원하는 문자로 치환하여 리턴한다.
     */
    public static String sGetReplaceString(String str, String src, String tgt) {

        if(str == null){ return null; }
        if(str.equals("")){ return ""; }

        int start = 0;
        int found = str.indexOf(src);

        StringBuffer ret = new StringBuffer();

        while (found >= 0) {
            if(found > start)
                ret.append(str.substring(start, found));

            if(tgt != null){ ret.append(tgt); }

            start = found + src.length();
            found = str.indexOf(src, start);
        }

        if(str.length() > start){ ret.append(str.substring(start, str.length())); }
        return ret.toString();
    }

    /**
     * 배열에서 원하는 위치의 값을 출력 하며 원하는 위치의 값이 NULL이나 빈 문자열이면 "N"으로 반환.
     * <pre>
     * 1: String[] sb = {"a","b","c"};
     *    String sReturn = StringUtil.sGetReplaceNull(sb, 1);
     *    sReturn 결과: "b"
     * 2: String[] sb = {"a","b","c",""};
     *    String sReturn = StringUtil.sGetReplaceNull(sb, 3);
     *    sReturn 결과: "N"
     * </pre>
     * @since 2010. 3. 3.
     * @param paramArr 입력받은 문자배열
     * @param idx 문자배열 위치 값
     * @return 입력받은 배열에서 원하는 위치의 값
     */
    public static String sGetReplaceNull(String[] paramArr, int idx){
        String param = null;
        if(paramArr == null){ param = "N"; } else { param = StringUtil.sGetNullTrimStr(paramArr[idx], "N"); }
        return param.trim();
    }

    /**
     * 입력받은 문자열이 NULL이나 빈 문자열이면 tgt를 반환하고 아니면  tgt2을 반환.
     * <pre>
     * 1: String sReturn = StringUtil.sGetReplaceNull("abcd","1234","super");
     *    sReturn 결과: "super"
     * 2: String sReturn = StringUtil.sGetReplaceNull("","1234","super");
     *    sReturn 결과: "1234"
     * </pre>
     * @since 2010. 3. 3.
     * @param value 입력받은 문자열
     * @param tgt 치환할 문자열(null이나 빈 문자열일 경우)
     * @param tgt2 치환할 문자열(null이나 빈 문자열가 아닐 경우)
     * @return 입력받은 문자열에 따라 치환된 문자열
     */
    public static String sGetReplaceNull(String value, String tgt, String tgt2) {
        if(isNull(value)){ return tgt; }
        return tgt2;
    }

    /**
     * 입력받은 문자열과 구분자를 우편번호형(123456 -> 123-456)으로 변환.
     * <pre>
     * 1: String sReturn = StringUtil.sGetReplaceZip("123456", "-");
     *    sReturn 결과: "123-456"
     * </pre>
     * @since 2010. 3. 3.
     * @param str 입력받은 물자열
     * @param ch 구분자
     * @return 변환된 문자열
     */
    public static String sGetReplaceZip(String str, String ch){
        String result = "";
        String tmp = str.trim();
        tmp = sGetIsNumber(tmp,"");

        if(tmp.length() == 6){ result = tmp.substring(0,3) + ch + tmp.substring(3,6); }
        return result;
    }

    /**
     * 입력받은 문자열에 원하는 길이를 지정하고 지정된 문자를 오른쪽에 채워주고 반환.
     * 단, 주어진 길이보다 크거나 같으면 원본문자열을 그대로 리턴한다
     * <pre>
     * 1: String sReturn = StringUtil.sGetRpad("abcdefghijk",26,'z');
     *    sReturn 결과: "abcdefghijkzzzzzzzzzzzzzzz"
     * 2: String sReturn = StringUtil.sGetRpad("abcdefghijk",5,'z');
     *    sReturn 결과: "abcdefghijk"
     * </pre>
     * @since 2010. 3. 3.
     * @param str 입력받은 문자열
     * @param len 원하는 길이
     * @param pad 지정된 문자
     * @return 입력받은 문자열에 원하는 길이까지 채워준 문자열
     */
    public static String sGetRpad(String str, int len, char pad){
        String result = str;
        int templen = len - result.getBytes().length;

        for(int i = 0; i < templen; i++) {
            result = result + pad;
        }
        return result;
    }

    /**
     * 입력받은 문자열에 원하는 길이를 지정하고 지정된 문자를 왼쪽에 채워주고 반환.
     * 단, 주어진 길이보다 크거나 같으면 원본문자열을 그대로 리턴한다
     * <pre>
     * 1: String sReturn = StringUtil.sGetLpad("abcdefghijk",26,'z');
     *    sReturn 결과: "zzzzzzzzzzzzzzzabcdefghijk"
     * 2: String sReturn = StringUtil.sGetLpad("abcdefghijk",5,'z');
     *    sReturn 결과: "abcdefghijk"
     * </pre>
     * @since 2010. 3. 3.
     * @param str 입력받은 문자열
     * @param len 원하는 길이
     * @param pad 지정된 문자
     * @return 입력받은 문자열에 원하는 길이까지 채워준 문자열
     */
    public static String sGetLpad(String str, int len, char pad){
        String result = str;
        int templen = len - result.getBytes().length;

        for(int i = 0; i < templen; i++){
            result = pad + result;
        }
        return result;
    }

    /**
     * 문자가 길경우에 특정 바이트 단위 길이로 자른 후 짤린 자리에 ',,,'으로 반환.
     * <pre>
     * String sReturn = StringUtil.sGetStrCut("abcdefghijklmnopqrstuvwxyz",10);
     * sReturn 결과: "abcdefghij..."
     * </pre>
     * @since 2010. 3. 3.
     * @param str 입력받은 문자열
     * @param byteSize byte단위로 남길 문자열의 길이
     * @return 자르고 남은 문자열
     * @throws Exception
     */
    public static String sGetStrCut(String str, int byteSize) throws Exception{
        return sGetStrCut(str, byteSize, ",,,");
    }

    /**
     * 문자가 길경우에 특정 바이트 단위 길이로 자른 후 짤린 자리에 원하는 문자로 반환.
     * <pre>
     * String sReturn = StringUtil.sGetStrCut("abcdefghijklmnopqrstuvwxyz",10,"_The End!!");
     * sReturn 결과: "abcdefghij_The End!!"
     * </pre>
     * @since 2010. 3. 3.
     * @param str 입력받은 문자열
     * @param byteSize byte단위로 남길 문자열의 길이
     * @param ch 남길 문자열 뒤에 적어줄 문자열
     * @return 자르고 남은 문자열
     * @throws Exception
     */
    public static String sGetStrCut(String str, int byteSize, String ch) throws Exception{
        int rSize = 0;
        int len = 0;

        if(str.getBytes().length > byteSize){
            for(rSize = 0; rSize < str.length(); rSize++){
                if(str.charAt(rSize) > 0x007F){ len += 2; } else { len++; }
                if(len > byteSize){ break; }
            }
            str = str.substring(0, rSize) + ch;
        }
        return str;
    }

    /**
     * 입력받은 문자열에 원하는 길이를 지정하고 주어진 길이보다 길이가 작은 문자열을 앞에 0을 붙여 반환.
     * 단, 주어진 길이보다 크거나 같으면 원본문자열을 그대로 리턴한다.
     * <pre>
     * String sReturn = StringUtil.sGetPaddingZero("한글이다", 26);
     * sReturn 결과: "0000000000000000000000한글이다"
     * </pre>
     * @since 2010. 3. 3.
     * @param str 입력받은 문자열
     * @param len 주어진 길이
     * @return 주어진 길이까지 '0'으로 패딩된 문자열
     */
    public static String sGetPaddingZeroBytes(String str, int len){
        return sGetLpad(str,len,'0');
    }

    /**
     * 첫문자를 대문자로 반환.
     * <pre>
     * String sReturn = StringUtil.sGetInitCap("string");
     * sReturn 결과: "String"
     * </pre>
     * @since 2010. 3. 3.
     * @param str 입력받은 문자열
     * @return  변환된 문자열을 리턴한다
     */
    public static String sGetInitCap(String str)
    {
        // A: 65, a: 97
        // 소문자일 경우 대문자로 변경
        String second = str.substring(1);
        char h = str.charAt(0);
        if(h >= 'a' && h <= 'z')
        {
            String first = String.valueOf((char) (h - 32));
            return first + second;
        }
        else {
            return str;
        }
    }

    /**
     * 입력 문자열에서 일정한 문자를 찾아 제거한다.
     * <pre>
     * String sReturn = StringUtil.sGetRemoveChar("aaabbbccc", 'b');
     * sReturn 결과: "aaaccc"
     * </pre>
     * @since 2010. 3. 3.
     * @param str 입력받은 문자열
     * @param ch 제거할 문자
     * @return 일정한 문자가 제거된 문자열
     */
    public static String sGetRemoveChar(String str, char ch)
    {
        return sGetRemoveChar(str, String.valueOf(ch));
    }

    /**
     * 입력 문자열에서 문자열을 찾아 제거한다.
     * <pre>
     * String sReturn = StringUtil.sGetRemoveChar("aaabbbccc", "bb");
     * sReturn 결과: "aaaccc"
     * </pre>
     * @since 2010. 3. 3.
     * @param str 입력받은 문자열
     * @param ch 제거할 문자열
     * @return 일정한 문자가 제거된 문자열
     */
    public static String sGetRemoveChar(String str, String ch)
    {
        if(isNull(str)) { return null; }

        StringBuffer buff = new StringBuffer();
        StringTokenizer token = new StringTokenizer(str, ch);
        while (token.hasMoreTokens())
        {
            buff.append(token.nextToken());
        }

        return buff.toString();
    }

    /**
     * 문자열 배열을 주어진 문자열로 연결하여 반환.
     * <pre>
     * String[] sb = {"1","2","3","4","5"};
     * String sReturn = StringUtil.sGetJoin(sb, "+");
     * sReturn 결과: "1+2+3+4+5"
     * </pre>
     * @since 2010. 3. 3.
     * @param list 스트링배열
     * @param separator 배열을 연결 할 문자열
     * @return 합쳐진 문자열을 리턴한다
     */
    public static String sGetJoin(String list[], String separator)
    {
        StringBuffer csv = new StringBuffer();
        for(int i = 0; i < list.length; i++)
        {
            if(i > 0)
                csv.append(separator);
            csv.append(list[i]);
        }
        return csv.toString();
    }

    /**
     * Exception 객체로 에러메시지 문자열을 생성한다.
     * <pre>
     * try {
     *      String[] sb = {"1","2","3","4","5"};
     *      String ss = sb[6];
     *
     *  } catch (Exception e) {
     *      String sReturn = StringUtil.sGetStackTrace(e);
     *      System.out.println(sReturn);
     *  }
     *
     * sReturn 결과: java.lang.ArrayIndexOutOfBoundsException: 6
     *              at com.podo.common.util.test.main(test.java:25)
     * </pre>
     * @since 2010. 3. 3.
     * @param e Throwable 객체(Exception)
     * @return 에러문자열
     */
    public static String sGetStackTrace(Throwable e)
    {
        String str = "";
        ByteArrayOutputStream buff = new ByteArrayOutputStream();
        e.printStackTrace(new PrintWriter(buff, true));
        str = buff.toString();
        return str;
    }

    /**
     * 입력받은 문자열에서 제거 할 문자열(ch)을 찾아 제거한다.
     * 단, 제거할 문자열이 틀리거나 없으면 입력받은 문자열 그대로 출력된다.
     * <pre>
     * String sReturn = StringUtil.sGetRemoveLastChar("abcd", "bc");
     * sReturn 결과: "ad"
     * </pre>
     * @since 2010. 3. 3.
     * @param str 원본문자열
     * @param ch 제거할 문자열
     * @return 제거된문자열을 리턴한다.
     * @throws Exception
     */
    public static String sGetRemoveLastChar(String str, String ch) throws Exception
    {
        int pos = str.lastIndexOf(ch);
        int i;
        for(i=0; i <= ch.length();i++){
        }

        if(pos == -1)
            return str;
        else
            return str.substring(0, pos) + str.substring(i);
    }

    /**
     * text --> html 로 변환.
     * <HTML TAG> <br />
     * 한칸 빈문자열 " " --> &nbsp; <br />
     * & --> &amp; <br />
     * " --> &quot; <br />
     * < --> &lt; <br />
     * > --> &gt; <br />
     * - --> &shy; <br />
     * 10 --> <br/> <br />
     * & --> &amp; <br />
     * </HTML TAG>
     * <pre>
     * String sReturn1 = StringUtil.sGetChangeHtmlToText("&");
     * String sReturn2 = StringUtil.sGetChangeHtmlToText(" ");
     * String sReturn3 = StringUtil.sGetChangeHtmlToText("-");
     * String sReturn4 = StringUtil.sGetChangeHtmlToText("<");
     * String sReturn5 = StringUtil.sGetChangeHtmlToText(">");
     * sReturn1 결과: "&amp;"
     * sReturn2 결과: "&nbsp;"
     * sReturn3 결과: "&shy;"
     * sReturn4 결과: "&lt;"
     * sReturn5 결과: "&gt;"
     * </pre>
     * @since 2010. 3. 3.
     * @param str 데이터베이스에 있는 데이터 문자열이다.
     * @return 바뀌어진 값을 넘겨준다.
     */
    public static String sGetChangeHtmlToText(String str) {
        String strNew = "";

        StringBuffer strTxt = new StringBuffer("");
        char chrBuff;
        int len = 0;
        int i = 0;

        len = str.length();

        for(i=0;i<len;i++) {
            chrBuff = (char)str.charAt(i);
            switch (chrBuff) {
                case '&' :
                    strTxt.append("&amp;");
                    break;
                case '-' :
                    strTxt.append("&shy;");
                    break;
                case '<' :
                    strTxt.append("&lt;");
                    break;
                case '>' :
                    strTxt.append("&gt;");
                    break;
                case '"' :
                    strTxt.append("&quot;");
                    break;
                case 10 :
                    strTxt.append("<br>");
                    break;
                case ' ' :
                    strTxt.append("&nbsp;");
                    break;
                default :
                    strTxt.append(chrBuff);
                    break;
            }//switch
        }//for

        strNew = strTxt.toString();

        return strNew;
    }

    /**
     * html --> text 로 변환.
     * <HTML TAG> <br />
     * &amp; --> & <br />
     * &sky; --> - <br />
     * &lt;  --> < <br />
     * &gt;  --> > <br />
     * &nbsp;-->" "
     * </HTML TAG>
     * <pre>
     * String sReturn1 = StringUtil.sGetChangeHtmlToText("&amp;");
     * String sReturn2 = StringUtil.sGetChangeHtmlToText("&shy;");
     * String sReturn3 = StringUtil.sGetChangeHtmlToText("&lt;");
     * String sReturn4 = StringUtil.sGetChangeHtmlToText("&gt;");
     * String sReturn5 = StringUtil.sGetChangeHtmlToText("&nbsp;");
     * sReturn1 결과: "&"
     * sReturn2 결과: "-"
     * sReturn3 결과: "<"
     * sReturn4 결과: ">"
     * sReturn5 결과: " "
     * </pre>
     * @since 2010. 3. 3.
     * @param str HTML에 있는 데이터 문자열이다.
     * @return 바뀌어진 값을 넘겨준다.
     */
    public static String sGetChangeTextToHtml(String str) {
        String strNew = "";

        StringBuffer strTxt = new StringBuffer("");
        if(str.equals("&amp;")){
            strTxt.append("&");
        }else if(str.equals("&shy;")){
            strTxt.append("-");
        }else if(str.equals("&lt;")){
            strTxt.append("<");
        }else if(str.equals("&gt;")){
            strTxt.append(">");
        }else if(str.equals("&nbsp;")){
            strTxt.append(" ");
        }else{
            strTxt.append(str);
        }

        strNew = strTxt.toString();

        return strNew;
    }

    /**
     * 입력받은 문자열에서 원하는 위치의 문자열을 반환.
     * <pre>
     * String Return = StringUtil.sGetSubString("abcdefghijklm",2,10);
     * Return 결과: "cdefghij"
     * </pre>
     * @since 2010. 3. 3.
     * @param val 입력받은 문자열
     * @param start 원하는 시작위치
     * @param end 원하는 끝나는 위치
     * @return 문자열의 시작위치와 끝나는 위치 사이
     */
    public static String sGetSubString(String val,int start, int end){
        String str = null;
        str = val;
        if(str.length() >= end) {
            str = val.substring(start,end);
        }
        return str;
    }

    /**
     * 숫자로만 이루어 졌는지를 체크하여 숫자로만 이루어였으면 넘겨받은 문자열을 반환 <br />
     * 단, 숫자가 아니면 따로 입력한 문자열로 반환.
     * <pre>
     * 1: String sReturn = StringUtil.sGetIsNumber("123123123","숫자가 아닙니다.");
     *    sReturn 결과: "123123123"
     * 2: String sReturn = StringUtil.sGetIsNumber("가나다라","숫자가 아닙니다.";
     *    sReturn 결과: "숫자가 아닙니다."
     * </pre>
     * @since 2010. 3. 3.
     * @param src 입력받은 문자열
     * @param defaultVal 숫자가 아닐경우 반환할 문자열
     * @return 반환된 문자열
     */
    public static String sGetIsNumber(String src, String defaultVal) {
        String strChar = "0123456789";

        for(int i = 0; i < src.length(); i++){
            if(strChar.indexOf(src.charAt(i)) == -1){ return defaultVal; }
        }
        return src;
    }

    /**
     * 입력받은 문자열(double형)을 금액 형태로 반환.
     * <pre>
     * String sReturn = StringUtil.sGetDigitComma(23232323.0);
     * sReturn 결과: "23,232,323"
     * </pre>
     * @since 2010. 3. 3.
     * @param num 들어오는 숫자형
     * @return 금액 형태로 바뀐 문자열
     */
    public static String sGetDigitComma(double num) {
        String minusStr = "";
        BigDecimal bigA = new BigDecimal(num);
        if(bigA.compareTo(new BigDecimal(0)) < 0){
            minusStr = "-";
            bigA = bigA.abs();
        }

        String paraNum = bigA.toString();

        String retValue = "";
        int numLen = paraNum.length();

        for(int i = 0; i < numLen; i++) {
            if(((numLen - i) % 3 == 1) && ((numLen - i) > 3)) {
                retValue = retValue + paraNum.substring(i, i + 1) + ",";
            } else {
                retValue = retValue + paraNum.substring(i, i + 1);
            }
        }

        return minusStr+retValue;
    }

    /**
     * 입력받은 문자열(String형-소수점 포함)을 금액 형태로 반환.
     * ex)23232323.3 -> 23,232,323.3
     * <pre>
     * String sReturn = StringUtil.sGetDigitCommaDouble("123456789.123");
     * sReturn 결과: "123,456,789.123"
     * </pre>
     * @since 2010. 3. 3.
     * @param num 들어오는 문자열
     * @return 금액 형태로 바뀐 문자열
     */
    public static String sGetDigitComma(String num) {
        String minusStr = "";
        String pointStr = "";

        if(num == null || "".equals(num))
            num = "0";

        BigDecimal bigA = null;

        if(num.indexOf(".") == -1){
            bigA = new BigDecimal(num);
        }else{
            pointStr = "."+num.substring(num.indexOf(".")+1, num.length());
            bigA = new BigDecimal(num.substring(0, num.indexOf(".")));
        }

        if(bigA.compareTo(new BigDecimal(0)) < 0){
            minusStr = "-";
            bigA = bigA.abs();
        }

        String paraNum = bigA.toString();

        String retValue = "";
        int numLen = paraNum.length();

        for(int i = 0; i < numLen; i++) {
            if(((numLen - i) % 3 == 1) && ((numLen - i) > 3)) {
                retValue = retValue + paraNum.substring(i, i + 1) + ",";
            } else {
                retValue = retValue + paraNum.substring(i, i + 1);
            }
        }

        return minusStr+retValue+pointStr;
    }

    /**
     * 입력받은 문자열(String형-소수점 제거)을 금액 형태로 반환.
     * <pre>
     * String sReturn = StringUtil.sGetDigitCommaCut("123456789.123");
     * sReturn 결과: "123,456,789"
     * </pre>
     * @since 2010. 3. 3.
     * @param num 들어오는 문자열
     * @return 금액 형태로 바뀐 문자열
     */
    public static String sGetDigitCommaCut(String num) {
        String minusStr = "";

        if(num == null || "".equals(num))
            num = "0";

        BigDecimal bigA = null;

        if(num.indexOf(".") == -1){
            bigA = new BigDecimal(num);
        }else{
            bigA = new BigDecimal(num.substring(0, num.indexOf(".")));
        }

        if(bigA.compareTo(new BigDecimal(0)) < 0){
            minusStr = "-";
            bigA = bigA.abs();
        }

        String paraNum = bigA.toString();

        String retValue = "";
        int numLen = paraNum.length();

        for(int i = 0; i < numLen; i++) {
            if(((numLen - i) % 3 == 1) && ((numLen - i) > 3)) {
                retValue = retValue + paraNum.substring(i, i + 1) + ",";
            } else {
                retValue = retValue + paraNum.substring(i, i + 1);
            }
        }

        return minusStr+retValue;
    }

    /**
     * 입력받은 정수 형태의 문자열의 수숫점 제거한뒤 반환.
     * <pre>
     * String sReturn = StringUtil.sGetDigitCut("123456789.123");
     * sReturn 결과: "123456789"
     * </pre>
     * @since 2010. 3. 3.
     * @param num 입력받은 문자열
     * @return 소숫점이 절하된 문자열
     */
    public static String sGetDigitCut(String num) {
        String minusStr = "";

        if(num == null || "".equals(num))
            num = "0";

        BigDecimal bigA = null;

        if(num.indexOf(".") == -1){
            bigA = new BigDecimal(num);
        }else{
            bigA = new BigDecimal(num.substring(0, num.indexOf(".")));
        }

        if(bigA.compareTo(new BigDecimal(0)) < 0){
            minusStr = "-";
            bigA = bigA.abs();
        }

        String paraNum = bigA.toString();

        return minusStr+paraNum;
    }

    public static String sGetThreeContent(String str)
    {
        String[] sContent = sGetSplit(str, "<br>");
        int iContent = sContent.length;
        String sReturn = "";

        for(int i=0;i<iContent;i++){

            if(str.indexOf("<br>") == -1){
                str = str.substring(str.indexOf("<br>")+4, str.length());
                sReturn = sReturn + sContent[i];
            }else{
                sReturn = sReturn + sContent[i] + "<br>";
            }
        }

        return sReturn;
    }

    public static String sGetCell(Cell cell)
    {
        String sReturn = null;

        // 셀의 타입 따라 받아서 구분지어 받되 한 행을 하나의 스트링으로 저장
        switch( cell.getCellType()) {
            case Cell.CELL_TYPE_STRING :
                sReturn = cell.getRichStringCellValue().getString();
                break;

            case Cell.CELL_TYPE_NUMERIC :
                if(DateUtil.isCellDateFormatted(cell)){
                    sReturn = cell.getDateCellValue().toString();
                }else{
                    Object obj= cell.getNumericCellValue();

                    if(obj instanceof Integer){
                        sReturn = Integer.toString((int)cell.getNumericCellValue());
                    }else{

                        sReturn = String.format("%.1f", (float)cell.getNumericCellValue());
                    }

                }
                break;

            case Cell.CELL_TYPE_BOOLEAN :
                sReturn = Boolean.toString(cell.getBooleanCellValue());
                break;

            case Cell.CELL_TYPE_FORMULA :
                sReturn = cell.getCellFormula();
                break;

            default: // 값이 없는 열은 포함되지 않게 함.
                sReturn = "";
        }

        if("N/A".equals(sReturn)) sReturn = "";
        return sReturn;
    }

    public static String excelGetCell(Cell cell)
    {
        String sReturn = null;

        // 셀의 타입 따라 받아서 구분지어 받되 한 행을 하나의 스트링으로 저장
        if(cell!=null) {
            switch( cell.getCellType()) {
                case Cell.CELL_TYPE_STRING :
                    sReturn = cell.getRichStringCellValue().getString();
                    break;

                case Cell.CELL_TYPE_NUMERIC :
                    sReturn = Long.toString((long)cell.getNumericCellValue());
                    break;

                case Cell.CELL_TYPE_BOOLEAN :
                    sReturn = Boolean.toString(cell.getBooleanCellValue());
                    break;

                case Cell.CELL_TYPE_FORMULA :
                    sReturn = cell.getCellFormula();
                    break;

                default: // 값이 없는 열은 포함되지 않게 함.
                    sReturn = "";
            }
        }
        else{
            sReturn="";
        }

        if("N/A".equals(sReturn)) sReturn = "";
        return sReturn;
    }


    // 년, 월, 일이 각각 입력되었을 경우 Date로 변경하는 메서드
    public static Date transformDate(String year, String month, String day)
    {
        String date = year+"-"+month+"-"+day;
        Date d = Date.valueOf(date);

        return d;
    }

    // 날짜가 yyyymmdd 형식으로 입력되었을 경우 Date로 변경하는 메서드
    public static Date transformDate(String date)
    {
        SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyymmdd");

        // Date로 변경하기 위해서는 날짜 형식을 yyyy-mm-dd로 변경해야 한다.
        SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");

        java.util.Date tempDate = null;

        try {
            // 현재 yyyymmdd로된 날짜 형식으로 java.util.Date객체를 만든다.
            tempDate = beforeFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // java.util.Date를 yyyy-mm-dd 형식으로 변경하여 String로 반환한다.
        String transDate = afterFormat.format(tempDate);

        // 반환된 String 값을 Date로 변경한다.
        Date d = Date.valueOf(transDate);

        return d;
    }

    /**
     * Map을 json으로 변환한다.
     *
     * @param map Map<String, Object>.
     * @return JSONObject.
     */
    @SuppressWarnings("unchecked")
	public static JSONObject getJsonStringFromMap(Map<String, Object> map )
    {
        JSONObject jsonObject = new JSONObject();
        for( Map.Entry<String, Object> entry : map.entrySet() ) {
            String key = entry.getKey();
            Object value = entry.getValue();
            jsonObject.put(key, value);
        }

        return jsonObject;
    }

    /**
     * List<Map>을 jsonArray로 변환한다.
     *
     * @param list List<Map<String, Object>>.
     * @return JSONArray.
     */
    @SuppressWarnings("unchecked")
	public static JSONArray getJsonArrayFromList(List<Map<String, Object>> list )
    {
        JSONArray jsonArray = new JSONArray();
        for( Map<String, Object> map : list ) {
            jsonArray.add( getJsonStringFromMap( map ) );
        }

        return jsonArray;
    }

    /**
     * List<Map>을 jsonString으로 변환한다.
     *
     * @param list List<Map<String, Object>>.
     * @return String.
     */
    public static String getJsonStringFromList( List<Map<String, Object>> list )
    {
        JSONArray jsonArray = getJsonArrayFromList( list );
        return jsonArray.toJSONString();
    }

    /**
     * JsonObject를 Map<String, Object>으로 변환한다.
     *
     * @param jsonObj JSONObject.
     * @return Map<String, Object>.
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getMapFromJsonObject( JSONObject jsonObj )
    {
        Map<String, Object> map = null;

        try {

            map = new ObjectMapper().readValue(jsonObj.toJSONString(), Map.class) ;

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    /**
     * JsonArray를 List<Map<String, String>>으로 변환한다.
     *
     * @param jsonArray JSONArray.
     * @return List<Map<String, Object>>.
     */
    public static List<Map<String, Object>> getListMapFromJsonArray( JSONArray jsonArray )
    {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        if( jsonArray != null )
        {
            int jsonSize = jsonArray.size();
            for( int i = 0; i < jsonSize; i++ )
            {
                Map<String, Object> map = getMapFromJsonObject( ( JSONObject ) jsonArray.get(i) );
                list.add( map );
            }
        }

        return list;
    }

    public static Map<String, Object> convertObjectToMap(Object obj){
        Map<String, Object> map = new HashMap<String,Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for(int i=0; i <fields.length; i++){
            fields[i].setAccessible(true);
            try{
                map.put(fields[i].getName(), fields[i].get(obj));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return map;
    }


}
