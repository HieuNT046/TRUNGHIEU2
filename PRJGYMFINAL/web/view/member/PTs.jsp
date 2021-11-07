<%-- 
    Document   : PTs
    Created on : Nov 6, 2021, 10:43:38 PM
    Author     : HL2020
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="pts" method="POST">
            <table border="1px">
            <tr>

                <td>Id</td>
                <td>Name</td>
                <c:forEach items="${requestScope.all_pt}" var="all">
                    <td> ${all.name}</td> 
                </c:forEach>       
            </tr>
            <c:forEach items="${requestScope.member}" var="s">
                <tr>
                    <td>${s.id}</td>
                    <td>${s.name}</td>
                    <c:forEach items="${requestScope.all_pt}" var="all">
                        <td style="width: 65px">
                            <input type="checkbox" 
                                   <c:forEach items="${s.PT}" var="sc">
                                       ${(all.id eq sc.id)?"checked=\"checked\"":""}
                                   </c:forEach>
                                   name="certid" value="${s.id}_${all.id}" />
                        </td>
                    </c:forEach>    
                </tr>
            </c:forEach>
        </table>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
