<%-- 
    Document   : list
    Created on : Nov 2, 2021, 9:31:56 PM
    Author     : HL2020
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Member"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <%
            ArrayList<Member> member = (ArrayList<Member>)request.getAttribute("member");
        
        %>
        <script> 
            function doUpdate(id)
            {
                window.location.href = "update?id=" + id;
            }
            function doDelete(id)
            {
                var c = confirm("are you sure?");
                if(c)
                {
                    window.location.href = "delete?id=" + id;
                }
            }
        
        </script>
        <style>
            h1 {
        text-align: center;
        color: black;
             font-family: verdana;
            font-size: 300%;
            }
        table {
    width: 100%;        
    font-family: arial, sans-serif;
    border-collapse: collapse;}
}
t     table,tr,td {
    padding: 8px;
    text-align: left;
    border-top: 4px brown}
}
  
}
        </style>
    </head>
   
    <body>
        <h1> Danh sách hội viên </h1>
        <table border="5" >
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
                <td>DETAIL</td>
                <td>UPDATE </td>
                <td>DELETE</td>
            </tr>
            <%
                for (Member s : member) {
            %>
            <tr>
                <td><%=s.getId() %></td>
                <td><%=s.getName()%></td>
                <td><%=s.getAge()%></td>
                <td><% if(s.isGender()){%>
                    <img src="../img/male-icon.png" alt=""/>
                    <%} else {%>
                    <img src="../img/female-icon.png" alt=""/>
                    <%}%>
                </td>
                <td><%=s.getDob()%></td>
                <td><%=s.getWeight()%></td>
                <td><%=s.getSmm()%></td>
                <td><%=s.getBodyfat()%></td>
                <td><%=s.getClas().getName() %></td>
                <td><a href="detail?id=<%=s.getId() %>">Detail</a></td>
                <td>
                   <input type="button" onclick="doUpdate(<%=s.getId()%>);" value="Update"/>
                </td>
                <td>
                    <input type="button" onclick="doDelete(<%=s.getId()%>);" value="Delete"/>
                </td>
            </tr>
            <%}%>
        </table>
        </br></br>
        <h2>
            Thêm hội viên vào danh sách --> <img src="../img/insert.png" alt=""/> <a href="insert">Click Here</a></br>
        </h2>
          <h2>
            Quản lý huấn luyện viên  ---> <a href="pts">Click Here</a></br>
        </h2>  
       
        
    </body>
</html>

