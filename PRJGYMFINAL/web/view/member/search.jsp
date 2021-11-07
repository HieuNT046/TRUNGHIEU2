<%-- 
    Document   : search
    Created on : Nov 4, 2021, 10:00:18 PM
    Author     : HL2020
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script> 
            function doUpdate(id)
            {
                window.location.href = "update?id=" + id;
            }

        </script>
        <style>
        table, td {
  border:1px solid red;
}
</style>
    </head>
    <body>
        <form action="search">
            <h1> Nhập mã số hội viên của bạn tại đây</h1> <input type="text" name="id" value="${param.id}"/> <br/> 
            <input type="submit" value="Search"/>
        </form>
            <table border="5px" >
            <tr>
                <td>Id</td>
                <td>Name</td>
                <td>Age</td>
                <td>Gender</td>
                <td>Test Date</td>
                <td>Weight</td>
                <td>SMM</td>
                <td>Body Fat</td>
                <td>Class</td>
                <td>UPDATE </td>
            </tr>
            <c:forEach items="${requestScope.members}" var="s">
            <tr>
                <td>${s.id}</td>
                <td>${s.name}</td>
                <td>${s.age}</td>
                <td><c:if test="${s.gender}">
                    <img src="../img/male-icon.png" alt=""/>
                    </c:if>
                    <c:if test="${!s.gender}">
                    <img src="../img/female-icon.png" alt=""/>
                    </c:if>
                </td>
                <td>${s.dob}</td>
                <td>${s.weight}</td>
                <td>${s.smm}</td>
                <td>${s.bodyfat}</td>
                <td>${s.clas.name}</td>
               <td>
                    <input type="button" onclick="doUpdate(${s.id});" value="Update"/>
                </td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
