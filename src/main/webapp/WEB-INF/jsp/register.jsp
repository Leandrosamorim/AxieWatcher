<%-- 
    Document   : signin
    Created on : 3 de dez. de 2021, 19:09:58
    Author     : 55229
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Register</title>
        <style>
            body  {
                    font-weight: 500 !important;
                    color: white !important;
                    background-color: #242735 !important;

                }
        </style>
    </head>
    <body>
        <h1>Login</h1>
        <form action="/register" method="POST" modelAttribute="userAddCmd" enctype="multipart/form-data">
            <p>Email: <input type="username" name="newUser.username"/></p>
            <p>Senha: <input type="password" name="newUser.password"/></p>
            <p><input type="file" name="newFile"/></p>
            <input type="submit"/>
        </form>
    </body>
</html>
