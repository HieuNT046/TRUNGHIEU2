<%-- 
    Document   : login
    Created on : Sep 29, 2021, 12:06:25 PM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="login" method="POST"> 
            Username: <input type="text" name="user"/> <br/>
            Password: <input type="password" name="pass" /> <br/>
            <input type="submit" value="Login" />
        </form>
    </body>
</html>
