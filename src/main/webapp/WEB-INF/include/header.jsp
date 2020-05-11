<%--
  ~ Copyright (c) 2019. 
  ~   ------------------------------------------------------------------------------
  ~   @Project       : RPA Web Project
  ~   @Source        : header.jsp
  ~   @Description   : 
  ~   @Author        : GACHINOEL
  ~   @Version       : v1.0
  ~   Copyright(c) 2019 WOOJIN All rights reserved
  ~   ------------------------------------------------------------------------------
  ~                    변         경         사         항                       
  ~   ------------------------------------------------------------------------------
  ~      DATE           AUTHOR                       DESCRIPTION                        
  ~   ---------------  ----------    ------------------------------------------------ 
  ~   2019.10.31       gachinoel     신규생성                                     
  ~   ------------------------------------------------------------------------------
  --%>

<%--
  Created by IntelliJ IDEA.
  User: jhkim
  Date: 2019-10-31
  Time: 오전 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="utf-8"%>

<div id="header">
    <div id="header-inner">
        <ul class="topmenu">
            <li class="title">Web발주시스템(우진공업<small>(주)</small>)</li>
            <c:choose>
                <c:when test="${userRole.equals('ADMIN')}">
                    <li><a href="/admin" id="shipping_admin">출하정보</a></li>
                    <li><a href="/admin/boslist" id="shipping_adminboslist">대기목록정보</a></li>
                    <li><a href="/admin/prelist" id="shipping_adminlist">출하전목록정보</a></li>
                    <li><a href="/admin/cfmlist" id="shippingcfm">확정목록정보</a></li>
                    <li><a href="/admin/psvlist" id="adminpsv">수동출하처리</a></li>
                    <li><a href="/admin/spglist" id="shippingspg">출하목록정보</a></li>
                    <li><a href="/admin/sumlist" id="sumlist">자재코드 합산</a></li>
                    <li><a href="/admin/mtmlist" id="mtmlist_admin">MTM목록</a></li>
                    <li><a href="/admin/commoncode" id="commoncode">공통코드관리</a></li>
                    <li><a href="/admin/userinfo" id="userinfo">회원정보</a></li>
                </c:when>
                <c:when test="${userRole.equals('SHIPPING')}">
                    <li><a href="/shipping" id="shipping">출하정보</a></li>
                    <li><a href="/shipping/psvlist" id="shippingpsv">수동출하처리</a></li>
                    <li><a href="/shipping/mtmlist" id="mtmlist">MTM목록</a></li>
                </c:when>
                <c:when test="${userRole.equals('SUPPLY')}">
                    <li><a href="/supply" id="supply">발주정보</a></li>
                    <!--li><a href="/supply/confirm" id="supplyconfirm">출하정보</a></li-->
                    <li><a href="/supply/resheet" id="supplyresheet">거래명세서 출력</a></li>
                    <li><a href="/supply/listPlace" id="menuplace">납품처 관리</a></li>
                </c:when>
            </c:choose>
            <ul style="float:right; list-style-type:none;">
                <li><a href="/logout">로그아웃</a></li>
            </ul>
        </ul>
    </div>
</div>