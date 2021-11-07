<%-- 
    Document   : insert
    Created on : Nov 3, 2021, 9:56:32 PM
    Author     : HL2020
--%>
<%@page import="model.Class"%>
<%@page import="model.PTranier"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
            ArrayList<Class> classes = (ArrayList<Class>) request.getAttribute("classes");
            ArrayList<PTranier> PTs = (ArrayList<PTranier>) request.getAttribute("PT");

        %>
        <style>  h1 {
              
                color: black;
             font-family: verdana;
            font-size: 100%;
            }</style>
    </head>
    <body>
        <form action="insert" method="POST">
            <h1>Thêm hội viên mới với mẫu sau:  </h1>
            ID: <input type="text" name="id" /> <br/>
            Name: <input type="text" name="name" /> <br/>
            Age: <input type="text" name="age" /> <br/>
            Gender: <input type="radio" checked="checked" name="gender" value="male" /> Male
            <input type="radio" name="gender" value="female" /> Female <br/>
            Test Date: <input type="date" name="dob"/> <br/>
            Weight: <input type="text" name="weight" /> <br/>
            SMM (): <input type="text" name="smm" /> <br/>
            Body Fat: <input type="text" name="bodyfat" /> <br/>
            Department: <select name="classid">
                <% for (Class d : classes) {
                %>
                <option value="<%=d.getId()%>" ><%=d.getName()%></option>
                <%}%>
            </select>
            <br/>

            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
