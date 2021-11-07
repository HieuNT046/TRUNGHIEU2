<%-- 
    Document   : detail
    Created on : Nov 2, 2021, 10:41:55 PM
    Author     : HL2020
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="model.PTranier"%>
<%@page import="model.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thông tin chi tiết</title>
    </head>
    <body>
        Thông tin chi tiết hội viên  <br/><br/>
        ID:  ${requestScope.member.id} <br/>
        Name:    ${requestScope["member"]["name"]} <br/>
        Age:   ${requestScope.member.age}<br/>
        Gender:  ${requestScope.member.gender?"Male":"Female" } <br/>
        Weight  : ${requestScope.member.weight} <br/>
        SMM (Skeletal Muscle Mass): ${requestScope.member.smm} <br/>
        Body Fat: ${requestScope.member.bodyfat} <br/>
        Class: ${requestScope.member.clas.name} <br/>
        Personal Trannier: <br/>
        <c:forEach items="${requestScope.member.PT}" var="c">
            ${c.name} <br/>
        </c:forEach>
    </body>
</html>
