<%-- 
    Document   : recentAxies
    Created on : 4 de dez. de 2021, 13:04:54
    Author     : 55229
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Favorites</title>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
            <style>
                body  {
                    font-weight: 500 !important;
                    color: white !important;
                    background-color: #242735 !important;

                }
                img{
                    max-width: 64px; transform: scale(1.9);
                }
                table{
                    color: #FFFFFF !important;
                }
                

            </style>
            
            <script>
                function saveA(id) {
                    window.addEventListener('DOMContentLoaded', function () {
                        callFUnction()
                    });
                    var id = "#" + id;
                    $.post("/delete", $(id).serialize());
                }
            </script>
        </head>
        <body>
            <%@include file="header.jsp" %>
            <h1>Axie ${axie.getId()}</h1>
            <table class="table">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Img</th>
                        <th>Name</th>
                        <th>Class</th>
                        <th>Price</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>

                        <tr>
                            <td>${axie.getId()}</td>
                            <td><img src="${axie.getImage()}"></td>
                            <td>${axie.getName()}</td>
                            <td>${axie.getClass1()}</td>
                            <td>${axie.getPrice()}</td>
                        </tr>
                </tbody>
            </table>

        </body>
    </html>
</f:view>
