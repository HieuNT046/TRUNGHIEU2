<%-- 
    Document   : update
    Created on : Nov 3, 2021, 3:26:47 PM
    Author     : HL2020
--%>
<%@page import="model.Member"%>
<%@page import="model.Class"%>
<%@page import="model.PTranier"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <form action="update" method="POST">
            Id: ${requestScope.member.id} <input type="hidden" name="id" value="${requestScope.member.id}" /> <br/>
            Name: <input type="text" value="${requestScope.member.name}" name="name" /> <br/>
            Age: <input type="text" value="${requestScope.member.age}" name="age" /> <br/>
            Gender: <input type="radio" 
                           ${(requestScope.member.gender) ? "checked=\"checked\"" : ""} 
                           name="gender" value="male" /> Male
            <input type="radio" 
                   ${(!requestScope.member.gender) ? "checked=\"checked\"" : ""}
                   name="gender" value="female" /> Female <br/>
            Test Date: <input type="date" value="${requestScope.member.dob}" name="dob"/> <br/>
            Weight : <input type="text" value="${requestScope.member.weight}" name="weight" /> <br/>
            SMM : <input type="text" value="${requestScope.member.smm}" name="smm" /> <br/>
            Body Fat :<input type="text" value="${requestScope.member.bodyfat}" name="bodyfat" /> <br/>
            Class: <select name="classid">
                <c:forEach items="${requestScope.classes}" var="d">
                    <option 
                        <c:if test="${d.id eq requestScope.member.clas.id}">
                            selected="selected"
                        </c:if>
                        value="${d.id}" >${d.name}</option>
                </c:forEach> </select>

                    <br/>
            Personal Trainner:<br/>
            <c:forEach items="${requestScope.PT}" var="c">
                <input type="checkbox"
                       <c:forEach items="${requestScope.member.PT}" var="ch">
                           <c:if test="${ch.id eq c.id}">
                               checked="checked"
                           </c:if>
                       </c:forEach>
                       name="pid" value="${c.id}"/> ${c.name}>
                <br/>
            </c:forEach>   
            </select>
            <br/>
            
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
